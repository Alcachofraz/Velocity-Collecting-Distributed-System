package consumer;

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
            if (spreadMessage.isMembership()) {
                MembershipInfo info = spreadMessage.getMembershipInfo();
                SpreadGroup[] membersSpreadGroup = info.getMembers();
                ArrayList<String> members = new ArrayList<>();
                for (SpreadGroup member : membersSpreadGroup) {
                    members.add(member.toString());
                }
                Collections.sort(members);
                String leader = members.get(0).split("#")[1];
                Consumer.member.setLeader(leader.equals(Consumer.member.getName()));
                if (info.isCausedByJoin()) {
                    System.out.println("JOIN of " + info.getJoined());
                } else if (info.isCausedByLeave()) {
                    System.out.println("LEAVE of " + info.getLeft());
                } else if (info.isCausedByDisconnect()) {
                    System.out.println("DISCONNECT of " + info.getDisconnected());
                }
                // Change in membership! If leader, warn Front-End Group:
                if (Consumer.member.isLeader()) {
                    System.out.println("Sending current Event-Processing Group membership to Front-End Group...");
                    Consumer.member.sendMessage(Consumer.FRONT_END_GROUP_NAME, "CONSUMERS" + members.size());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

