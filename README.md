# Sensor Simulation Distributed System

## Scenario

We have been requested to have multiple sensors in multiple cities collecting velocity samples in highways. Velocity values may vary between **0 km/h** and **270 km/h**, and each sample comes with the **sensor ID**, the **city** and the **date**. The values collected should be held in a log file, before being published to the servers that will provide a **User Application** with a fair ammount of possible queries, like the highest velocity ever registered or the average velocity in a certain city.

## Implementation

We will use RabbitMQ to create two queues binded to an exchange. One queue will serve the purpose of logging the velocity samples, and the other will make the samples available for a subsequent level of our system. The sensors will publish the sample to the exchange, which, in turn, will redirect them to the queues.

To collect the samples in the **RabbitMQ** queue, we will have multiple logger applications inside a **Spread Group** (**Event Processing Group**) whose purpose is to analyse the samples and send the ones over **120 km/h** to another Spread Group (**Front-End Group**), where our servers will be operating. These servers will collect these samples and keep them in memory, and use them to answer the queries from the **User Application**.

Inside the **Event Processing Group**, there will be a leader logger, whose aditional task is to inform the **Front-End** group of a membership event (if a logger has joined, left, or disconnected from the group). As for the **Front-End Group** there will also be a leader Server, whose aditional taks is to update a new Server (that just joined) with the samples that have been collected over time.

Here is an illustration of the system described:

<img src="https://user-images.githubusercontent.com/75852333/148682857-7369a1d5-39a9-499c-b4b0-a2d0e28daa34.png" width="520">

## Configuration steps

### 1) Install Spread Daemon in VM's:

Follow the instructions in the PDF **Spread Install Instructions in VM GCP CentOS.pdf**.

### 2) Install RabbitMQ

Please, refer to [this](https://docs.docker.com/engine/install/centos/) link. Use the following commands:  
```
sudo yum install -y yum-utils  
sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo  
sudo yum install docker-ce docker-ce-cli containerd.io  
sudo systemctl start docker  
sudo docker run hello-world  
```

To avoid using 'sudo' when interacting with RabbitMQ, use:  
```
sudo usermod -aG docker $USER  
sudo newgrp docker  
```

To run:  
```
docker run fedora cat /etc/os-release  
docker run -d --hostname rabbithost --name rabbitmg -p 5672:5672 -p 15672:15672 rabbitmq:management  
```

You can use:
```
http://<public VM IP>:15672 
```
To use the RabbitMQ interface. Use guest/guest to log in. 

### 3) Start Spread  

Use the following command:  
```
spread -c /usr/local/etc/newspread.conf  
```

### 4) Launch Server.jar  

Use the following command:  
```
java -jar Server.jar --daemon-endpoint=<daemon_ip>:<daemon_port> --port=<server_port>  
```

### 5) Launch Consumer.jar  

Use the following command:  
```
java -jar Consumer.jar --daemon-endpoint=<daemon_ip>:<daemon_port> --broker-endpoint=<broker_ip>:<broker_port>  
```

### 6) Launch Logger.jar  

Use the following command:  
```
java -jar Logger.jar --broker-endpoint=<broker_ip>:<broker_port>  
```

### 7) Launch Sensor.jar  

Use the following command:  
```
java -jar Sensor.jar --broker-endpoint=<broker_ip>:<broker_port> --publish-rate=<publish_rate_in_ms> --sid=<sensor_id> --city=<city_name> --minus-day=<days_to_subtract>  
```

### 8) Launch User.jar  

Use the following command: 
```
java -jar User.jar --endpoint=<server_ip>:<server_port>  
```
