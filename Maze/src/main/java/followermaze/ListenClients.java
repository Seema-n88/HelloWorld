package followermaze;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;


public class ListenClients implements Runnable
{
    private int port_number;
    private DataInputStream in;
    private DataOutputStream out;
    Map clientLists = new HashMap();

    public ListenClients(int port)
    {
        port_number = port;
    }

    public class ClientThread implements Runnable {
        String clientId;
        BufferedReader reader;
        Socket sock;
        PrintWriter outputclient;
        Queue<String> msgWriteQ;

        public ClientThread(Socket clientSocket, String inputLine, PrintWriter writer) {
            outputclient = writer;
            clientId = inputLine;
            sock = clientSocket;
        }

        @Override
        public void run() {
            while(true)
            {
                final String msg = msgWriteQ.remove();
                outputclient.println(msg);
                if(outputclient.checkError())
                {
                    clientLists.remove(clientId);
                }
            }
        }
    }

    public void run()  {

        ServerSocket mySocket = null;
        Socket clientSocket = null;
        try {
            mySocket = new ServerSocket(port_number);
        } catch (IOException e) {
            e.printStackTrace();
        }


        String inputLine ;

        while(true)
        {
            try {
                clientSocket = mySocket.accept();
                in = new DataInputStream(clientSocket.getInputStream());
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Client:" + inputLine);
                    clientLists.put(inputLine, clientSocket);
                    Thread client = new Thread(new ClientThread(clientSocket, inputLine, writer));
                    client.start();
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }

        }


    }
    void writeToclient(String msg, String clientId)
    {
        Socket clientSocket = (Socket) clientLists.get(clientId);
        try {
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
