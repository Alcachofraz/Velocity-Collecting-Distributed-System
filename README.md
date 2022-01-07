# Sensor Simulation Distributed System

## Scenario

We have been requested to have multiple sensors in multiple cities collecting velocity samples in hihgways. Velocity values may vary between **0 km/h** and **270 km/h**, and each sample comes with the **sensor ID**, the **city** and the **date**. The values collected should be held in a log file, before being published to the servers that will provide a **User Application** with a fair ammount of possible queries, like the highest velocity ever registered or the average velocity in a certain city.

## Implementation

We will use RabbitMQ to create two queues binded to an exchange. One queue will serve the purpose of logging the velocity samples, and the other will make the samples available for a subsequent level of our system. The sensors will publish the sample to the exchange, which, in turn, will redirect them to the queues.

To collect the samples in the **RabbitMQ** queue, we will have multiple consumer applications inside a **Spread Group** (**Event Processing Group**) whose purpose is to analyse the samples and send the ones over **120 km/h** to another Spread Group (**Front-End Group**), where our servers will be operating. These servers will collect these samples and keep them in memory, and use them to answer the queries from the **User Application**.

Inside the **Event Processing Group**, there will be a leader consumer, whose aditional task is to inform the **Front-End** group of a membership event (if a consumer has joined, left, or disconnected from the group). As for the **Front-End Group** there will also be a leader Server, whose aditional taks is to update a new Server (that just joined) with the samples that have been collected over time.

Here is an illustration of the system described:

![CD Final Project drawio](https://user-images.githubusercontent.com/75852333/148568308-33dc4d7a-711d-49cb-96b8-9876b6de591d.png)
