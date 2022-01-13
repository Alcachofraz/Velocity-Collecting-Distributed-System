package consumer;

import com.rabbitmq.client.*;
import spread.SpreadException;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Scanner;

public class Consumer {

    static String BROKER_IP = "35.197.247.130";
    static int BROKER_PORT = 5672;
    static final String QUEUE_NAME = "VELOCITY_QUEUE";
    private static final String EXCHANGE_NAME = "VELOCITY_SAMPLES";
    private static final String ROUTING_KEY = "VELOCITY_SAMPLES";

    private static String DAEMON_IP = "35.197.247.130";
    private static int DAEMON_PORT = 4803;
    public static final String EVENT_PROCESSING_GROUP_NAME = "EVENT_PROCESSING_GROUP";
    public static final String FRONT_END_GROUP_NAME = "FRONT_END_GROUP";
    public static GroupMember member;

    public static void main(String[] args) {
        HashMap<String, String> keyValueArgs = convertToKeyValuePair(args);
        String daemonEndpoint;
        String brokerEndpoint;
        if ((daemonEndpoint = keyValueArgs.get("daemon-endpoint")) != null) {
            DAEMON_IP = daemonEndpoint.substring(0, daemonEndpoint.indexOf(":"));
            DAEMON_PORT = Integer.parseInt(daemonEndpoint.substring(daemonEndpoint.indexOf(":") + 1));
        }
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

            // Handler with AUTO ACK:
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                try {
                    String routingKey = delivery.getEnvelope().getRoutingKey();
                    // Receive VelocitySample in binary and deserialize to Object:
                    VelocitySample velocitySample = VelocitySample.fromBytes(delivery.getBody());
                    System.out.println("Message Received [" + consumerTag + "] ["+ routingKey + "]: " + velocitySample);
                    if (velocitySample.getVelocity() > 120) {
                        member.sendMessage(FRONT_END_GROUP_NAME, "ALL" + new String(velocitySample.toBytes()));
                    }
                } catch (SpreadException e) {
                    e.printStackTrace();
                }
            };

            // Handler without AUTO ACK:
            /*DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                long deliveryTag = delivery.getEnvelope().getDeliveryTag();
                if (message.equals("nack")) {
                    System.out.println("NACK");
                    channel.basicNack(deliveryTag, false, true);
                }
                else channel.basicAck(deliveryTag,true);
                try {
                    String routingKey = delivery.getEnvelope().getRoutingKey();
                    // Receive VelocitySample in binary and deserialize to Object:
                    VelocitySample velocitySample = VelocitySample.fromBytes(delivery.getBody());
                    System.out.println("Message Received [" + consumerTag + "] ["+ routingKey + "]: " + velocitySample);
                    if (velocitySample.getVelocity() > 120) {
                        member.sendMessage(FRONT_END_GROUP_NAME, "ALL" + new String(velocitySample.toBytes()));
                    }
                } catch (SpreadException e) {
                    e.printStackTrace();
                }
            };*/

            // Consumer handler to receive cancel receiving messages
            CancelCallback cancelCallback = (consumerTag) -> {
                System.out.println("CANCEL Received: [" + consumerTag + "]");
            };

            String newConsumerTag = channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
            System.out.println("Consumer Tag: [" + newConsumerTag + "]");

            //
            // SPREAD:
            //
            member = new GroupMember(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)), DAEMON_IP, DAEMON_PORT);
            member.joinGroup(EVENT_PROCESSING_GROUP_NAME);

            System.out.println("Type 'exit' to terminate this Consumer.");
            while(!readLine().equals("exit")) {
                System.out.println("Type 'exit' to terminate this Consumer.");
            }

            member.close();
            System.exit(0);
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



