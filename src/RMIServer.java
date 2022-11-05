import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class RMIServer {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8080;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        try {
            System.setProperty(RMI_HOSTNAME, hostName);
            long startingTime = System.nanoTime();

            Random random = new Random();
            Service service = new ServiceImpl();
            for (int i = 0; i < 5; i++) {
                service.addElem((char) random.nextInt(65,122));
            }

            String serviceName = "Service";

            System.out.println("Initializing " + serviceName);

            Registry registry = LocateRegistry.createRegistry(port);
            registry.rebind(serviceName, service);

            System.out.println("Start " + serviceName);
            long endingTime = System.nanoTime();
            System.out.println("Upgraded New Chars: " + service.getNewChars());
            System.out.println("Time it took to complete the task: " + (endingTime-startingTime)/1000000000 + " seconds");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}