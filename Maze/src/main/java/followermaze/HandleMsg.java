package followermaze;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandleMsg {
    Map<String,  List<String>> userFollowers = new HashMap<String, List<String>>();

    void decodeMsg(Map<Integer,String> event_source_msgs)
    {
        for (String msg : event_source_msgs.values())
        {
            String[] result = msg.split("\\|");
            System.out.println("result:" + result[1]);
            switch (result[1])
            {
                case "F":
                    handleFollowMsg(msg);
                    break;
                case "U":
                    handleUnfollowMsg(msg);
                    break;
                case "B":
                    handleBroadcastMsg(msg);
                    break;
                case "P":
                    handlePrivateMsg(msg);
                    break;
                case "S":
                    handleStatusUpdateMsg(msg);
                    break;
                default:
                    break;
            }
        }
    }

    private void handleStatusUpdateMsg(String msg)
    {
        System.out.println("handleStatusUpdateMsg");
        String[] result = msg.split("|");
        List<String> followers = userFollowers.get(result[2]);
        sendFollowerClients(msg, followers);

    }

    private void handlePrivateMsg(String msg)
    {
        System.out.println("handlePrivateMsg");
        String[] result = msg.split("|");
        sendToClient(msg, result[3]);
    }

    public void handleBroadcastMsg(String msg)
    {
        System.out.println("handleBroadcastMsg");
    }

    public void handleUnfollowMsg(String msg)
    {
        System.out.println("handleUnfollowMsg");
        String[] result = msg.split("|");
        deleteFollower(result[2], result[3]);
    }

    public void handleFollowMsg(String msg)
    {
        System.out.println("handleFollowMsg");
        String[] result = msg.split("|");
        addFollower(result[2], result[3]);
        sendToClient(msg, result[3]);

    }

    public void addFollower(String follower, String followee)
    {
        if(!userFollowers.containsKey(followee))
        {
            List<String> followers = new ArrayList<String>();
            followers.add(follower);
            userFollowers.put(followee, followers);
            return;
        }
        List<String> followers = userFollowers.get(followee);
        if(followers == null)
        {
            followers = new ArrayList<String>();
        }
        followers.add(follower);
        userFollowers.replace(followee, followers);
    }

    public void deleteFollower(String follower, String followee)
    {
        if(!userFollowers.containsKey(followee))
        {
            return;
        }
        List<String> followers = userFollowers.get(followee);
        if(followers == null)
        {
            return;
        }
        if(followers.size() == 1)
        {
            userFollowers.remove(followee);
        }
        else
        {
            followers.remove(follower);
            userFollowers.replace(followee, followers);
        }

    }

    public void sendToClient(String msg, String clientId)
    {

    }

    public void broadcastClients(String msg)
    {

    }

    public void sendFollowerClients(String msg, List<String> followers)
    {

    }
}
