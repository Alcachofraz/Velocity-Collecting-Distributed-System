package configurator;

import com.rabbitmq.client.*;

import java.util.HashMap;

public class RabbitConfigurator {

    static String BROKER_IP = "35.197.247.130";
    static int BROKER_PORT = 5672;

    static final String QUEUE_NAME = "VELOCITY_QUEUE";
    static final String QUEUE_LOG_NAME = "VELOCITY_QUEUE_LOG";
    private static final String EXCHANGE_NAME = "VELOCITY_SAMPLES";
    private static final String ROUTING_KEY = "VELOCITY_SAMPLES";

    static Connection connection = null;
    static Channel channel = null;

    public static void main(String[] args) {
        HashMap<String, String> keyValueArgs = convertToKeyValuePair(args);
        String brokerEndpoint;
        if ((brokerEndpoint = keyValueArgs.get("daemon-endpoint")) != null) {
            BROKER_IP = brokerEndpoint.substring(0, brokerEndpoint.indexOf(":"));
            BROKER_PORT = Integer.parseInt(brokerEndpoint.substring(brokerEndpoint.indexOf(":") + 1));
        }
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(BROKER_IP);
            factory.setPort(BROKER_PORT);

            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT, true);
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            channel.queueDeclare(QUEUE_LOG_NAME, true, false, false, null);
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
            channel.queueBind(QUEUE_LOG_NAME, EXCHANGE_NAME, ROUTING_KEY);

            System.out.println("Queues " + QUEUE_NAME + " and " + QUEUE_LOG_NAME + " bound to Exchange " + EXCHANGE_NAME);

            channel.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
