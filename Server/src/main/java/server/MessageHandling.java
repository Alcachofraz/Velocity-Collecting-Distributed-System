package server;

import spread.*;

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
                Server.member.sendMessage(Server.FRONT_END_GROUP_NAME, "ANSWER" + new String(Server.getHistoryBytes()));
            }
            else if (message.startsWith("ANSWER") && Server.pending) {
                Server.history = Server.fromHistoryBytes(message.replaceFirst("ANSWER", "").getBytes());
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

