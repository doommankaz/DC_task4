import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Implementation of the remote service.
 */
public class ServiceImpl extends UnicastRemoteObject implements Service {
    private boolean isWorking = true;
    private final BlockingQueue<Character> queue;
    private final List<Character> newChars;
    public ServiceImpl() throws RemoteException {
        super();
        this.queue = new LinkedBlockingQueue<>();
        this.newChars = new ArrayList<>();
    }

    public List<Character> getNewChars() throws RemoteException, InterruptedException {
        while (!isEmpty() && isWorking()){
            //does literally nothing
        }
        Thread.sleep(1000);
        return newChars;
    }

    @Override
    public void addNewChar(Character character) throws RemoteException{
        newChars.add(character);
        System.out.println("Added to New Char List: " + character);
    }

    @Override
    public Character pollElem() throws RemoteException {
        return this.queue.poll();
    }

    @Override
    public void addElem(Character chr) throws RemoteException {
        this.queue.add(chr);
        System.out.println("Added: " + chr);
    }

    @Override
    public boolean isEmpty() throws RemoteException {
        return queue.isEmpty();
    }

    @Override
    public List<Character> returnResult() throws RemoteException, InterruptedException {
        return newChars;
    }

    @Override
    public void setWorking(boolean isWorking) throws RemoteException {
        this.isWorking = isWorking;
    }

    @Override
    public boolean isWorking() throws RemoteException {
        return isWorking;
    }

}