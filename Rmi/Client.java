// package Rmi;
import java.rmi.*;

public class Client {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 3030;
        String service = "affiche";
        try {
            Interf i = (Interf) Naming.lookup("rmi://" + host + ":" + port + "/" + service);
            System.out.println(i.affiche());        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}