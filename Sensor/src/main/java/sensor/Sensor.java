package sensor;

import com.rabbitmq.client.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Sensor {

    private static String BROKER_IP = "35.197.234.138";
    private static int BROKER_PORT = 5672;
    private static final String EXCHANGE_NAME = "VELOCITY_SAMPLES";
    private static final String ROUTING_KEY = "VELOCITY_SAMPLES";

    private static String SID = "1";
    private static String CITY = "Lisbon";
    private static int MINUS_DAYS = 0;
    private static int PUBLISH_RATE = 2500;

    public static void main(String[] args) {
        HashMap<String, String> keyValueArgs = convertToKeyValuePair(args);
        String brokerEndpoint;
        String sid;
        String city;
        String minusDay;
        String publishRate;
        if ((brokerEndpoint = keyValueArgs.get("broker-endpoint")) != null) {
            BROKER_IP = brokerEndpoint.substring(0, brokerEndpoint.indexOf(":"));
            BROKER_PORT = Integer.parseInt(brokerEndpoint.substring(brokerEndpoint.indexOf(":") + 1));
        }
        if ((publishRate = keyValueArgs.get("publish-rate")) != null) {
            PUBLISH_RATE = Integer.parseInt(publishRate);
        }
        if ((sid = keyValueArgs.get("sid")) != null) {
            SID = sid;
        }
        if ((city = keyValueArgs.get("city")) != null) {
            CITY = city;
        }
        if ((minusDay = keyValueArgs.get("minus-day")) != null) {
            MINUS_DAYS = Integer.parseInt(minusDay);
        }
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(BROKER_IP);
            factory.setPort(BROKER_PORT);

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            Random random = new Random();

            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(() -> {
                try {
                    VelocitySample velocitySample = new VelocitySample(SID, CITY, LocalDate.now().minusDays(MINUS_DAYS).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), random.nextInt(270));
                    channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, velocitySample.toBytes());
                    System.out.println(velocitySample);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, 0, PUBLISH_RATE, TimeUnit.MILLISECONDS);

            System.out.println("Type 'exit' to terminate this Sensor.");
            while(!readLine().equals("exit")) {
                System.out.println("Type 'exit' to terminate this Sensor.");
            }

            channel.close();
            connection.close();
            System.exit(0);
        } catch (Exception ex) {
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
