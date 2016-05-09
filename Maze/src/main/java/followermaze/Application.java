package followermaze;

public class Application {
    public static void main(String[] args)
    {
        System.out.println("Hello world");
        ListenEventSource server = new ListenEventSource(9090);

        Thread t = new Thread(server);
        t.start();

        ListenClients server1 = new ListenClients(9099);
        Thread t1 = new Thread(server1);
        t1.start();
    }
}
