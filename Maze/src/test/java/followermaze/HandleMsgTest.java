package followermaze;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by seema on 08/05/16.
 */
public class HandleMsgTest {

    @Test
    public void testMsg()
    {
        assertEquals(1,1);
    }

    @Test
    public void shouldaddFollower()
    {
        String follower = "32";
        String followee = "64";
        HandleMsg handler = new HandleMsg();
        handler.addFollower(follower,followee);

    }

    @Test
    public void shouldaddFollower1()
    {
        String followee = "32";
        String follower1 = "64";
        String follower2 = "74";
        String follower3 = "84";
        HandleMsg handler = new HandleMsg();
        handler.addFollower(follower1,followee);
        handler.addFollower(follower2,followee);
        handler.addFollower(follower3,followee);

    }

    @Test
    public void shoulddeleteFollower1()
    {
        String followee = "32";
        String follower1 = "64";
        String follower2 = "74";
        String follower3 = "84";
        HandleMsg handler = new HandleMsg();
        handler.addFollower(follower1,followee);
        handler.addFollower(follower2,followee);
        handler.addFollower(follower3,followee);
        handler.deleteFollower(follower2,followee);
        handler.deleteFollower(follower1,followee);

    }

//    @Test
//    public void shoulddecodeMsg()
//    {
//        HandleMsg handler = new HandleMsg();
//        String msg1 = "666|F|60|50";
//        String msg2 = "1|U|12|9 ";
//        String msg3 = "542532|B ";
//        String msg4 = "43|P|32|56";
//        String msg5 = "634|S|32";
//        handler.decodeMsg(msg1);
//        handler.decodeMsg(msg2);
//        handler.decodeMsg(msg3);
//        handler.decodeMsg(msg4);
//        handler.decodeMsg(msg5);
//    }
}