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
                    if (leader.equals(Server.member.getName())) {
                        System.out.println("Sending history to newest server...");
                        // TODO: Send history to server that just joined.
                        for (VelocitySample sample : Server.history) {
                            Server.member.sendMessage(Server.FRONT_END_GROUP_NAME, info.getJoined().toString().split("#")[1] + new String(sample.toBytes()));
                        }
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
            else if (message.startsWith(Server.member.getName())) {
                Server.history.add(VelocitySample.fromBytes(message.split(Server.member.getName())[1].getBytes()));
            }
            else {
                Server.history.add(VelocitySample.fromBytes(spreadMessage.getData()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

