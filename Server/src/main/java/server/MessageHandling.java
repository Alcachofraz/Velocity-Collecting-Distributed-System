package server;

import spread.*;

import java.util.ArrayList;
import java.util.Collections;

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
                SpreadGroup[] membersSpreadGroup = info.getMembers();
                ArrayList<String> members = new ArrayList<>();
                for (SpreadGroup member : membersSpreadGroup) {
                    members.add(member.toString());
                }
                Collections.sort(members);
                String leader = members.get(0).split("#")[1]; // Get name between the first two "#"
                Server.member.setLeader(leader.equals(Server.member.getName()));
                if (info.isCausedByJoin()) {
                    System.out.println("JOIN of " + info.getJoined());
                } else if (info.isCausedByLeave()) {
                    System.out.println("LEAVE of " + info.getLeft());

                } else if (info.isCausedByDisconnect()) {
                    System.out.println("DISCONNECT of " + info.getDisconnected());
                }
                if (leader.equals(Server.member.getName())) {
                    System.out.println("I'm the new leader!");
                }
            }
            else if (message.startsWith("CONSUMERS")) {
                Server.consumers = Integer.parseInt(message.replaceFirst("CONSUMERS", ""));
            }
            else if (message.startsWith("REQUEST") && Server.member.isLeader()) {
                Server.member.sendMessage(Server.FRONT_END_GROUP_NAME, "ANSWER" + new String(Server.getHistoryBytes()) + "CONSUMERS" + Server.consumers);
            }
            else if (message.startsWith("ANSWER") && Server.pending) {
                String[] aux = message.replaceFirst("ANSWER", "").split("CONSUMERS");
                Server.history = Server.fromHistoryBytes(aux[0].getBytes());
                Server.consumers = Integer.parseInt(aux[1]);
                Server.pending = false;
            }
            else if (message.startsWith("ALL") && !Server.pending) {
                Server.history.add(VelocitySample.fromBytes(message.replaceFirst("ALL", "").getBytes()));
            }
            if (Server.pending) {
                Server.member.sendMessage(Server.FRONT_END_GROUP_NAME, "REQUEST");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

