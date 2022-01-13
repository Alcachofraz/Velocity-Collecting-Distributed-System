package consumer;

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
                if (info.isCausedByJoin()) {
                    System.out.println("JOIN of " + info.getJoined());
                } else if (info.isCausedByLeave()) {
                    System.out.println("LEAVE of " + info.getLeft());
                } else if (info.isCausedByDisconnect()) {
                    System.out.println("DISCONNECT of " + info.getDisconnected());
                }
                Consumer.member.setLeader(leader.equals(Consumer.member.getName()));
                // Change in membership! If leader, warn Front-End Group:
                if (Consumer.member.isLeader()) {
                    System.out.println("Sending current Event-Processing Group membership to Front-End Group...");
                    Consumer.member.sendMessage(Consumer.FRONT_END_GROUP_NAME, "CONSUMERS" + members.length);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

