//AddClient.java
import java.rmi.*;
import java.util.Scanner;

public class AddClient {
    public static void main(String args[]) {
        try {
            AddServerIntf stub = (AddServerIntf) Naming.lookup("rmi://localhost/AddServer");

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter first number: ");
            double a = sc.nextDouble();

            System.out.print("Enter second number: ");
            double b = sc.nextDouble();

            double result = stub.add(a, b);

            System.out.println("Sum = " + result);

        } catch (Exception e) {
            System.out.println("Client Error: " + e);
        }
    }
}

//AddServer.java

import java.rmi.*;
import java.rmi.registry.*;

public class AddServer {
    public static void main(String args[]) {
        try {
            // Start registry automatically
            LocateRegistry.createRegistry(1099);

            AddServerImpl obj = new AddServerImpl();

            Naming.rebind("rmi://localhost/AddServer", obj);

            System.out.println("Server is ready...");
        } catch (Exception e) {
            System.out.println("Server Error: " + e);
        }
    }
}

//AddServerImpl.java

import java.rmi.*;
import java.rmi.server.*;  

public class AddServerImpl extends UnicastRemoteObject implements AddServerIntf {
                                 
    public AddServerImpl() throws RemoteException {
        super();
    }

    public double add(double a, double b) throws RemoteException {
        // Showing thread handling
        System.out.println("Handled by thread: " + Thread.currentThread().getName());

        return a + b;
    }
}

 
//AddServerIntf.java

import java.rmi.*;    
public interface AddServerIntf extends Remote {
    double add(double a, double b) throws RemoteException;   
}









//proof of multithreading
//Multiple client requests were made ✔
//Each request handled by different thread ✔