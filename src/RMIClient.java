import java.rmi.Naming;

public class RMIClient {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8080;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        String SERVICE_PATH = "//" + hostName + ":" + port + "/Service";

        try {
            System.setProperty(RMI_HOSTNAME, hostName);
            Service service = (Service) Naming.lookup(SERVICE_PATH);

            while (service.isWorking()) {
                Character chr = service.pollElem();
                System.out.println("Received: " + chr);
                service.addElem(chr);
                System.out.println("New Value: " + changeChar(chr));
                service.addNewChar(chr);
                Thread.sleep(100);

                if (service.isEmpty()) {
                    System.out.println("Queue is empty");
                    service.setWorking(false);
                    return;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    static char changeChar(Character character){
        if (Character.isLowerCase(character)) {
            character = Character.toUpperCase(character);
        } else {
            character = Character.toLowerCase(character);
        }
        return character;
    }
}