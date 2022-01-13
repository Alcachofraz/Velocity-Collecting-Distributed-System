package logger;

import com.rabbitmq.client.*;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

public class Logger {

    static String BROKER_IP = "35.197.247.130";
    static int BROKER_PORT = 5672;
    static final String QUEUE_NAME = "VELOCITY_QUEUE_LOG";
    private static final String EXCHANGE_NAME = "VELOCITY_SAMPLES";
    private static final String ROUTING_KEY = "VELOCITY_SAMPLES";

    public static void main(String[] args) {
        HashMap<String, String> keyValueArgs = convertToKeyValuePair(args);
        String brokerEndpoint;
        if ((brokerEndpoint = keyValueArgs.get("broker-endpoint")) != null) {
            BROKER_IP = brokerEndpoint.substring(0, brokerEndpoint.indexOf(":"));
            BROKER_PORT = Integer.parseInt(brokerEndpoint.substring(brokerEndpoint.indexOf(":") + 1));
        }

        try {
            //
            // RABBITMQ:
            //

            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(BROKER_IP);
            factory.setPort(BROKER_PORT);

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            File log = new File("velocity_samples.txt");
            if (log.createNewFile()) {
                System.out.println("Created file: " + log.getName());
            }

            // Handler with AUTO ACK:
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                try {
                    String routingKey = delivery.getEnvelope().getRoutingKey();
                    // Receive VelocitySample in binary and deserialize to Object:
                    VelocitySample velocitySample = VelocitySample.fromBytes(delivery.getBody());
                    System.out.println("Message Received [" + consumerTag + "] ["+ routingKey + "]: " + velocitySample);
                    FileWriter writer = new FileWriter(log);
                    writer.append(velocitySample.toString()).append("\n");
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };

            // Handler without AUTO ACK:
            /*DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                long deliveryTag = delivery.getEnvelope().getDeliveryTag();
                try {
                    String routingKey = delivery.getEnvelope().getRoutingKey();
                    // Receive VelocitySample in binary and deserialize to Object:
                    VelocitySample velocitySample = VelocitySample.fromBytes(delivery.getBody());
                    System.out.println("Message Received [" + consumerTag + "] ["+ routingKey + "]: " + velocitySample);
                    FileWriter writer = new FileWriter(log);
                    writer.append(velocitySample.toString()).append("\n");
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (message.equals("nack")) {
                    System.out.println("NACK");
                    channel.basicNack(deliveryTag, false, true);
                }
                else channel.basicAck(deliveryTag,false);
            };*/

            // Consumer handler to receive cancel receiving messages
            CancelCallback cancelCallback = (consumerTag) -> {
                System.out.println("CANCEL Received: [" + consumerTag + "]");
            };

            String newConsumerTag = channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
            System.out.println("Consumer Tag: [" + newConsumerTag + "]");

            while (!readLine().equals("exit")) {
                System.out.println("Type 'exit' to terminate this Logger.");
            }

            channel.close();
            connection.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String readLine() {
        Scanner scanInput = new Scanner(System.in);
        return scanInput.nextLine();
    }

    private static HashMap<String, String> convertToKeyValuePair(String[] args) {
        HashMap<String, String> params = new HashMap<>();

        for (String arg: args) {
            String[] splitFromEqual = arg.split("=");

            String key = splitFromEqual[0].substring(2);
            String value = splitFromEqual[1];

            params.put(key, value);
        }
        return params;
    }
}



