package followermaze;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

public class ListenEventSource implements Runnable{

    private int port_number;
    private DataInputStream in;
    private DataOutputStream out;
    HandleMsg handler;

    public ListenEventSource(int port)
    {
        port_number = port;
        handler = new HandleMsg();

    }

    public void run()  {

        ServerSocket mySocket = null;
        Socket clientSocket = null;
        try
        {
            mySocket = new ServerSocket(port_number);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        String inputLine;
        Map<Integer, String> event_source_msgs = new HashMap<Integer, String>();
        while(true)
        {
            try {
                clientSocket = mySocket.accept();
                in = new DataInputStream(clientSocket.getInputStream());
                out = new DataOutputStream(clientSocket.getOutputStream());
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Event:" + inputLine);
                    String[] result = inputLine.split("\\|");
                    event_source_msgs.put(Integer.parseInt(result[0]), inputLine);

                }
                handler.decodeMsg(event_source_msgs);
                event_source_msgs.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
