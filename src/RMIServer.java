import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class RMIServer {
    public static void main(String[] args) {
        long startingTime = System.nanoTime();
        String hostName = "localhost";
        int port = 8080;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        try {
            System.setProperty(RMI_HOSTNAME, hostName);

            Random random = new Random();
            Service service = new ServiceImpl();
            for (int i = 66; i < 122; i++) {
//                char randomChar = (char) random.nextInt(65,122);
                service.addElem((char) i);
            }

            String serviceName = "Service";

            System.out.println("Initializing " + serviceName);

            Registry registry = LocateRegistry.createRegistry(port);
            registry.rebind(serviceName, service);

            System.out.println("Start " + serviceName);
            System.out.println("Upgraded New Chars: " + service.getNewChars());
            long endingTime = System.nanoTime();
            System.out.println("Time it took to complete the task: " + (endingTime-startingTime)/1000000000 + " seconds");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}