package server;

import io.grpc.ServerBuilder;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Server {

    //private static String DAEMON_IP = "34.89.88.166";
    private static String DAEMON_IP = "35.197.234.138";
    private static int DAEMON_PORT = 4803;
    public static final String EVENT_PROCESSING_GROUP_NAME = "EVENT_PROCESSING_GROUP";
    public static final String FRONT_END_GROUP_NAME = "FRONT_END_GROUP";
    public static GroupMember member;

    public static List<VelocitySample> history = new ArrayList<>();
    public static int consumers = 0;

    private static int SERVER_PORT = 5000;
    public static final Logger logger = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) {
        if (args.length > 0) {
            SERVER_PORT = Integer.parseInt(args[0]);
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
}



