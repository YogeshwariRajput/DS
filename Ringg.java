
import java.util.Scanner; 
 
public class Ringg { 
 
    public static void main(String[] args) { 
 
        Scanner sc = new Scanner(System.in); 
 
        int p[] = new int[10]; 
 
        System.out.println("Enter number of processes:");        
         int n = sc.nextInt(); 
 
        for (int i = 0; i < n; i++) { 
            System.out.println("Enter process id:");            
             p[i] = sc.nextInt(); 
        } 
 
        int max = p[0]; 
 
        for (int i = 1; i < n; i++) {             
            if (p[i] > max)                 
                max = p[i]; 
        } 
 
        System.out.println("\nProcess " + max + " selected as coordinator"); 
 
        while (true) { 
 
            System.out.println("\n1.Election  2.Quit");             
            int ch = sc.nextInt(); 
 
            if (ch == 1) { 
 
                System.out.println("Enter initiator process index:");                
                int start = sc.nextInt(); 
                 int i = start; 
                 do {                     
                    int next = (i + 1) % n; 
 
                    System.out.println( 
                        "Process " + p[i] + 
                        " sends message to " + p[next]); 
                     i = next; 
 
                } while (i != start); 
 
                System.out.println( 
                    "\nProcess " + max + 
                    " selected as coordinator"); 
            } 
             else { 
                System.out.println("Program terminated...");                
                 break; 
            } 
        } 
    } 
} 
 
//Ring.java

import java.util.Scanner;

public class Ring {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int p[] = new int[10];

        System.out.println("Enter number of processes:");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter process ID:");
            p[i] = sc.nextInt();
        }

        while (true) {

            System.out.println("\n1. Election");
            System.out.println("2. Quit");

            int ch = sc.nextInt();

            if (ch == 1) {

                System.out.println("Enter initiator process index:");
                int start = sc.nextInt();

                int max = p[start];

                int i = start;

                do {

                    int next = (i + 1) % n;

                    System.out.println(
                        "Process " + p[i] +
                        " sends message to " + p[next]);

                    if (p[next] > max)
                        max = p[next];

                    i = next;

                } while (i != start);

                System.out.println(
                    "\nProcess " + max +
                    " selected as Coordinator");
            }

            else {

                System.out.println("Program terminated...");
                break;
            }
        }
    }
}
