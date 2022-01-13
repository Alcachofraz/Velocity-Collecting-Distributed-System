package consumer;

import spread.SpreadConnection;
import spread.SpreadException;
import spread.SpreadGroup;
import spread.SpreadMessage;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupMember {

    private SpreadConnection connection;

    private final Map<String, SpreadGroup> belongingGroups = new HashMap<String,SpreadGroup>();

    private MessageHandling messageHandling;

    private final String name;

    private boolean isLeader = false;

    public GroupMember(String name, String address, int port) {
        this.name = name;
        // Establish the spread connection
        try  {
            connection = new SpreadConnection();
            connection.connect(InetAddress.getByName(address), port, name, false, true);
            messageHandling = new MessageHandling(connection);
            connection.add(messageHandling);
        }
        catch(SpreadException e)  {
            System.err.println("There was an error connecting to the daemon.");
            e.printStackTrace();
            System.exit(1);
        }
        catch(UnknownHostException e) {
            System.err.println("Can't find the daemon " + address + ".");
            System.exit(1);
        }
    }

    public boolean isLeader() {
        return isLeader;
    }

    public void setLeader(boolean leader) {
        isLeader = leader;
    }

    public String getName() {
        return name;
    }

    public List<String> getNamesOfBelongingGroups() {
        return new ArrayList<>(belongingGroups.keySet());
    }

    public void joinGroup(String groupName) throws SpreadException {
        SpreadGroup newGroup = new SpreadGroup();
        newGroup.join(connection, groupName);
        belongingGroups.put(groupName, newGroup);
    }

    public void sendMessage(String groupName, String message) throws SpreadException {
        SpreadMessage spreadMessage = new SpreadMessage();
        spreadMessage.setSafe();
        spreadMessage.addGroup(groupName);
        spreadMessage.setData(message.getBytes());
        connection.multicast(spreadMessage);
    }

    public void sendMessage(String groupName, byte[] bytes) throws SpreadException {
        SpreadMessage spreadMessage = new SpreadMessage();
        spreadMessage.setSafe();
        spreadMessage.addGroup(groupName);
        spreadMessage.setData(bytes);
        connection.multicast(spreadMessage);
    }

    public void leaveGroup(String groupName) throws SpreadException {
        SpreadGroup group = belongingGroups.get(groupName);
        if(group != null) {
            group.leave();
            belongingGroups.remove(groupName);
            System.out.println("Left from " + group.toString() + ".");
        } else  { System.out.println("No group to leave."); }
    }

    public void close() throws SpreadException {
        // Remove listener
        connection.remove(messageHandling);
        // Disconnect
        connection.disconnect();
    }
}
