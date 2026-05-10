import mpi.*;

public class DistributedSum1 {
    public static void main(String[] args) throws Exception {

        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int[] data = {10, 20, 30, 40};
        int val;

        if (rank == 0) {
            for (int i = 1; i < size; i++)
                MPI.COMM_WORLD.Send(data, i, 1, MPI.INT, i, 0);
            val = data[0];
        } else {
            int[] r = new int[1];
            MPI.COMM_WORLD.Recv(r, 0, 1, MPI.INT, 0, 0);
            val = r[0];
        }

        System.out.println("Processor " + rank + " partial sum = " + val);

        if (rank != 0) {
            MPI.COMM_WORLD.Send(new int[]{val}, 0, 1, MPI.INT, 0, 1);
        } else {
            int sum = val;
            for (int i = 1; i < size; i++) {
                int[] r = new int[1];
                MPI.COMM_WORLD.Recv(r, 0, 1, MPI.INT, i, 1);
                sum += r[0];
            }
            System.out.println("Final Sum = " + sum);
        }

        MPI.Finalize();
    }
}


//download MPI


//dell@dell-Inspiron-14-5430:~$ export MPJ_HOME=$HOME/mpj-v0_44
//dell@dell-Inspiron-14-5430:~$ export PATH=$MPJ_HOME/bin:$PATH
//dell@dell-Inspiron-14-5430:~$ javac -cp .:$MPJ_HOME/lib/mpj.jar DistributedSum4.java
//dell@dell-Inspiron-14-5430:~$ mpjrun.sh -np 4 DistributedSum4
//MPJ Express (0.44) is started in the multicore configuration
//Processor 3 partial sum = 40
//Processor 1 partial sum = 20
//Processor 0 partial sum = 10
//Processor 2 partial sum = 30
//Final Sum = 100
//dell@dell-Inspiron-14-5430:~$ 











//This program simulates a distributed system where:

//The root processor (rank 0) has the full array
//It distributes one element to each processor using Send
//Each processor:
//Receives one element
//Treats it as its partial sum
//All processors send their results back to root
//Root adds everything → final sum


