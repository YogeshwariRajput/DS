//Concat.java (Interface)
import java.rmi.*;
public interface Concat extends Remote {
 public String concat(String x, String y) throws RemoteException;
}
// ConcatRemote.java (Implementation)
import java.rmi.*;
import java.rmi.server.*;
public class ConcatRemote extends UnicastRemoteObject implements Concat {
 ConcatRemote() throws RemoteException {
 super();
 }
 public String concat(String x, String y) {
 return x + y;
 }
}
// MyServer.java
import java.rmi.*;
public class MyServer {
 public static void main(String args[]) {
 try {
 Concat stub = new ConcatRemote();
 Naming.rebind("rmi://localhost/concatService", stub);
 System.out.println("Server is running...");
 } catch (Exception e) {
 System.out.println(e);
 }
 }
}

//MyClient.java
import java.util.Scanner;
import java.rmi.*;
public class MyClient {
 public static void main(String args[]) {
 try {
 Concat stub = (Concat) Naming.lookup("rmi://localhost/concatService");
 Scanner input = new Scanner(System.in);
 System.out.println("Enter String 1:");
 String x = input.nextLine();
 System.out.println("Enter String 2:");
 String y = input.nextLine();
 System.out.println("Result: " + stub.concat(x, y));
 } catch (Exception e) {
 System.out.println(e);
 }
 }
}
Output :