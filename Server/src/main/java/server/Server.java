package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.grpc.ServerBuilder;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class Server {

    private static String DAEMON_IP = "35.197.234.138";
    private static int DAEMON_PORT = 4803;
    public static final String FRONT_END_GROUP_NAME = "FRONT_END_GROUP";
    public static GroupMember member;

    public static List<VelocitySample> history = new ArrayList<>();
    public static int consumers = 0;

    private static int SERVER_PORT = 5000;
    public static final Logger logger = Logger.getLogger(Server.class.getName());

    public boolean pending = true;

    public static void main(String[] args) {
        HashMap<String, String> keyValueArgs = convertToKeyValuePair(args);
        String daemonEndpoint;
        String port;
        if ((daemonEndpoint = keyValueArgs.get("daemon-endpoint")) != null) {
            DAEMON_IP = daemonEndpoint.substring(0, daemonEndpoint.indexOf(":"));
            DAEMON_PORT = Integer.parseInt(daemonEndpoint.substring(daemonEndpoint.indexOf(":") + 1));
        }
        if ((port = keyValueArgs.get("port")) != null) {
            SERVER_PORT = Integer.parseInt(port);
        }
        try {
            member = new GroupMember(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)), DAEMON_IP, DAEMON_PORT);
            member.joinGroup(FRONT_END_GROUP_NAME);

            io.grpc.Server svc = ServerBuilder.forPort(SERVER_PORT)
                    .addService(new ServerUser())
                    .build()
                    .start();

            logger.info("SERVER: Server started, listening on " + SERVER_PORT + "...");

            svc.awaitTermination();

            member.close();
            System.exit(0);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static byte[] getHistoryBytes() {
        Gson json = new GsonBuilder().create();
        String jsonString = json.toJson(history);
        return jsonString.getBytes(StandardCharsets.UTF_8);
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



