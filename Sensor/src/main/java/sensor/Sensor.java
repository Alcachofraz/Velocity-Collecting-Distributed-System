package sensor;

import com.rabbitmq.client.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Sensor {

    private static final String BROKER_IP = "35.197.234.138";
    private static final int BROKER_PORT = 5672;
    private static final String EXCHANGE_NAME = "VELOCITY_SAMPLES";
    private static final String ROUTING_KEY = "VELOCITY_SAMPLES";

    private static String SID = "1";
    private static String CITY = "New York";
    private static int MINUS_DAYS = 0;

    public static void main(String[] args) {
        if (args.length > 0) {
            SID = args[0];
        }
        if (args.length > 1) {
            CITY = args[1];
        }
        if (args.length > 2) {
            MINUS_DAYS = Integer.parseInt(args[2]);
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
            }, 0, 2500, TimeUnit.MILLISECONDS);

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
}
