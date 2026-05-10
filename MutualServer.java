import java.io.*;
import java.net.*;

public class MutualServer {
    public static void main(String[] a) throws Exception {

        ServerSocket ss = new ServerSocket(7000);
        System.out.println("Server Started");

        while (true) {
            Socket s = ss.accept();

            BufferedReader in =
            new BufferedReader(
            new InputStreamReader(s.getInputStream()));

            System.out.println(in.readLine());
        }
    }
}

//ClientOne.java

import java.io.*;
import java.net.*;

public class ClientOne {
    public static void main(String[] a) throws Exception {

        Socket s = new Socket("localhost",7000);
        PrintStream out = new PrintStream(s.getOutputStream());

        ServerSocket ss = new ServerSocket(7001);
        Socket s1 = ss.accept();

        BufferedReader in =
        new BufferedReader(
        new InputStreamReader(s1.getInputStream()));

        PrintStream out1 = new PrintStream(s1.getOutputStream());

        BufferedReader br =
        new BufferedReader(
        new InputStreamReader(System.in));

        String t = "Token";

        while(true){

            if(t.equals("Token")){

                System.out.println("Enter Data");
                out.println(br.readLine());
                System.out.println("Token passed to Client 2");

                out1.println("Token");
            }

            t = in.readLine();
        }
    }
}

//ClientTwo.java

import java.io.*;
import java.net.*;

public class ClientTwo { 
    public static void main(String[] a) throws Exception {

        Socket s = new Socket("localhost",7000);
        PrintStream out = new PrintStream(s.getOutputStream());

        Socket s2 = new Socket("localhost",7001);

        BufferedReader in =
        new BufferedReader(
        new InputStreamReader(s2.getInputStream()));

        PrintStream out2 = new PrintStream(s2.getOutputStream());

        BufferedReader br =
        new BufferedReader(
        new InputStreamReader(System.in));

        while(true){

            String t = in.readLine();

            if(t.equals("Token")){

                System.out.println("Enter Data");
                out.println(br.readLine());
                
		        System.out.println("Token passed to Client 1");

                out2.println("Token");
            }
        }
    }
}






//dell@dell-Inspiron-14-5430:~$ javac MutualServer.java
//dell@dell-Inspiron-14-5430:~$ javac ClientOne.java
//dell@dell-Inspiron-14-5430:~$ javac ClientTwo.java
//dell@dell-Inspiron-14-5430:~$ java MutualServer
//Server Started
//pranjal
//yogeshwari

//dell@dell-Inspiron-14-5430:~$ java ClientOne
//Enter Data
//pranjal
//Token passed to Client 2
//Enter Data
//patil


//dell@dell-Inspiron-14-5430:~$ java ClientTwo
//Enter Data
//yogeshwari
//Token passed to Client 1
//Enter Data
//rajput

