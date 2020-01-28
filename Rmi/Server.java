// package Rmi;
import java.rmi.*;
import java.rmi.server.*;
// import java.rmi.registry.*;


public class Server extends UnicastRemoteObject implements Interf {
    
    private static final long serialVersionUID = 1L;

    public Server() throws RemoteException {
    }

    public String affiche() throws RemoteException {
        return "something";
    }

    public static void main(String[] args) {
        int port = 3030;
        String host = "127.0.0.1";
        try {
            java.rmi.registry.LocateRegistry.createRegistry(port);
            Server a = new Server();
            Naming.rebind("rmi://" + host + ":" + port + "/affiche", a);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}