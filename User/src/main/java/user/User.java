package user;

import com.google.protobuf.Empty;
import velocity.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {

    public static final Logger logger = Logger.getLogger(User.class.getName());

    public static String serverIP = "localhost";
    public static int serverPort = 5000;

    private static void Menu(VelocityQueriesGrpc.VelocityQueriesBlockingStub blockingStub) {
        String op;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println("MENU");
            System.out.println(" 0 - Highest velocity registered");
            System.out.println(" 1 - Lowest velocity registered");
            System.out.println(" 2 - Velocities in a city");
            System.out.println(" 3 - Velocities in a date");
            System.out.println(" 4 - Average velocity in a city");
            System.out.println(" 5 - Average velocity in a date");
            System.out.println(" 6 - Number of active consumers");
            System.out.println("99 - Exit");
            System.out.println();
            System.out.println("Choose an Option?");
            op = scan.nextLine();
            switch (op) {
                case "0":
                    printSample(blockingStub.queryHighestVelocity(Empty.newBuilder().build()));
                    break;
                case "1":
                    printSample(blockingStub.queryLowestVelocity(Empty.newBuilder().build()));
                    break;
                case "2":
                    System.out.println("What city?");
                    String city1 = scan.nextLine();
                    Answer samplesInCity = blockingStub.queryVelocitiesInCity(City.newBuilder().setCity(city1).build());
                    if (samplesInCity.getSamplesList().isEmpty()) System.out.println("None");
                    else for (Sample sample : samplesInCity.getSamplesList()) {
                        printSample(sample);
                    }
                    break;
                case "3":
                    System.out.println("What date? (DD-MM-YYYY)");
                    String date1 = scan.nextLine();
                    Answer samplesInDate = blockingStub.queryVelocitiesInDate(Date.newBuilder().setDate(date1).build());
                    if (samplesInDate.getSamplesList().isEmpty()) System.out.println("None");
                    else for (Sample sample : samplesInDate.getSamplesList()) {
                        printSample(sample);
                    }
                    break;
                case "4":
                    System.out.println("What city?");
                    String city2 = scan.nextLine();
                    int value1 = blockingStub.queryAverageVelocityInCity(City.newBuilder().setCity(city2).build()).getValue();
                    System.out.println(value1 < 0 ? "None" : value1);
                    break;
                case "5":
                    System.out.println("What date? (DD-MM-YYYY)");
                    String date2 = scan.nextLine();
                    int value2 = blockingStub.queryAverageVelocityInDate(Date.newBuilder().setDate(date2).build()).getValue();
                    System.out.println(value2 < 0 ? "None" : value2);
                    break;
                case "6":
                    System.out.println("There are " + blockingStub.queryNumberOfConsumers(Empty.newBuilder().build()).getValue() + " active consumers.");
                    break;
                default:
                    System.out.println("Invalid input. Try one of the following:");
                    System.out.println();
            }
        } while (!op.equals("99"));
    }

    static void printSample(Sample sample) {
        if (sample.getSid().equals("NONE")) System.out.println("None");
        else System.out.println("SID: " + sample.getSid() + ", CITY: " + sample.getCity() + ", DATE: " + sample.getDate() + ", VELOCITY: " + sample.getVelocity());
    }

    public static void main(String[] args) throws Exception {
        ManagedChannel channel = null;

        HashMap<String, String> keyValueArgs = convertToKeyValuePair(args);
        String endpoint;
        if ((endpoint = keyValueArgs.get("endpoint")) != null) {
            serverIP = endpoint.substring(0, endpoint.indexOf(":"));
            serverPort = Integer.parseInt(endpoint.substring(endpoint.indexOf(":") + 1));
        }

        try {
            // Setup Channel to Server
            channel = ManagedChannelBuilder.forAddress(serverIP, serverPort)
                    // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                    // needing certificates.
                    .usePlaintext()
                    .build();
            VelocityQueriesGrpc.VelocityQueriesBlockingStub blockingStub = VelocityQueriesGrpc.newBlockingStub(channel);

            Menu(blockingStub);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error:" + ex.getMessage());
        }
        if (channel != null) {
            logger.log(Level.INFO, "Shutdown channel to Server.");
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
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
