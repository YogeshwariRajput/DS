import java.util.*;

public class Berkeley1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Server time
        Calendar c = Calendar.getInstance();
        int sh = c.get(Calendar.HOUR_OF_DAY);
        int sm = c.get(Calendar.MINUTE);
        int ss = c.get(Calendar.SECOND);

        int server = sh * 3600 + sm * 60 + ss;

        System.out.println("Server Time: " + sh + ":" + sm + ":" + ss);

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        int[] nodeTime = new int[n];
        int[] diff = new int[n];

        // Input node times
        for (int i = 0; i < n; i++) {
            System.out.print("Enter h m s for Node " + (i + 1) + ": ");
            int h = sc.nextInt();
            int m = sc.nextInt();
            int s = sc.nextInt();

            nodeTime[i] = h * 3600 + m * 60 + s;
            diff[i] = nodeTime[i] - server;
        }

        // Average difference
        int sum = 0;
        for (int d : diff) sum += d;
        int avg = sum / n;

        System.out.println("\nAverage Difference: " + avg + " sec");

        // Adjust server
        server += avg;
        System.out.println("\nSynchronized Times:");

        System.out.println("Server -> " + 
                (server / 3600 % 24) + ":" +
                (server % 3600 / 60) + ":" + 
                (server % 60));

        // Adjust nodes
        for (int i = 0; i < n; i++) {
            int newTime = nodeTime[i] - diff[i] + avg;

            System.out.println("Node " + (i + 1) + " -> " +
                    (newTime / 3600 % 24) + ":" +
                    (newTime % 3600 / 60) + ":" +
                    (newTime % 60));
        }

        sc.close();
    }
}


//output   javac Berkeley1.java

//Server Time: 13:3:16
//Enter number of nodes: 3
//Enter h m s for Node 1: 3 5 6
//Enter h m s for Node 2: 2 45 52
//Enter h m s for Node 3: 1 56 12

//Average Difference: -37652 sec

//Synchronized Times:
//Server -> 2:35:44
//Node 1 -> 2:35:44
//Node 2 -> 2:35:44
//Node 3 -> 2:35:44
//
