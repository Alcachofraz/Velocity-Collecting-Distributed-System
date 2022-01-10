package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spread.*;

import java.nio.charset.StandardCharsets;

public class MessageHandling implements BasicMessageListener {
    private SpreadConnection connection;

    public MessageHandling(SpreadConnection connection) {
        this.connection = connection;
    }

    @Override
    public void messageReceived(SpreadMessage spreadMessage) {
        try {
            String message = new String(spreadMessage.getData());
            if (spreadMessage.isMembership()) {
                MembershipInfo info = spreadMessage.getMembershipInfo();
                SpreadGroup[] members = info.getMembers();
                String leader = members[0].toString().split("#")[1]; // Get name between the first two "#"
                Server.member.setLeader(leader.equals(Server.member.getName()));
                if (info.isCausedByJoin()) {
                    System.out.println("JOIN of " + info.getJoined());
                    if (leader.equals(Server.member.getName())) {
                        System.out.println("Sending history to newest server...");

                    }
                } else if (info.isCausedByLeave()) {
                    System.out.println("LEAVE of " + info.getLeft());

                } else if (info.isCausedByDisconnect()) {
                    System.out.println("DISCONNECT of " + info.getDisconnected());
                }
            }
            else if (message.startsWith("CONSUMERS") && Server.member.isLeader()) {
                Server.consumers = Integer.parseInt(message.split(" ")[1]);
            }
            else if (message.startsWith("REQUESTING_HISTORY")) {
                if (Server.member.isLeader()) {
                    Server.member.sendMessage(Server.FRONT_END_GROUP_NAME, message.split("REQUESTING_HISTORY")[1] + new String(Server.getHistoryBytes()));
                }
            }
            else if (message.startsWith(Server.member.getName())) {
                Server.history.add(VelocitySample.fromBytes(message.split(Server.member.getName())[1].getBytes()));
            }
            else if (message.startsWith("ALL")) {
                Server.history.add(VelocitySample.fromBytes(message.split("ALL")[1].getBytes()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

