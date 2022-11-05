import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interface for a service which will be accessible remotely
 */
public interface Service extends Remote {
    Character pollElem() throws RemoteException;
    void addElem(Character chr) throws RemoteException;
    boolean isEmpty() throws RemoteException;
    List<Character> getNewChars() throws RemoteException, InterruptedException;
    void addNewChar(Character character) throws RemoteException;
    List<Character> returnResult() throws RemoteException, InterruptedException;
    void setWorking(boolean isWorking) throws RemoteException;
    boolean isWorking() throws RemoteException;
}