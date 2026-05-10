/* NO-3: Develop a distributed system, to find sum of N elements in an array by distributing N/n elements to n
number of processors MPI or OpenMP.Demonstrate by displaying the intermediate sums calculated
at different processors.
*/

#include <stdio.h>
#include <mpi.h>

    int main(int argc, char *argv[])
{
    int rank, size;

    // Total number of elements in the array
    int N = 20;

    // num[] stores complete array at root process
    // local[] stores part of array received by each process
    // sums[] stores local sums collected at root process
    int num[20], local[20], sums[20];

    // Initialize MPI environment
    MPI_Init(&argc, &argv);

    // Get current process rank/id
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    // Get total number of processes
    MPI_Comm_size(MPI_COMM_WORLD, &size);

    // N must divide equally among all processes
    if (N % size != 0)
    {
        if (rank == 0)
            printf("N must be divisible by number of processors\n");

        MPI_Finalize();
        return 0;
    }

    // Number of elements given to each process
    int chunk = N / size;

    // Root process creates the array: 1 to 20
    if (rank == 0)
    {
        for (int i = 0; i < N; i++)
            num[i] = i + 1;
    }

    // Divide array equally and send one part to each process
    MPI_Scatter(num, chunk, MPI_INT,
                local, chunk, MPI_INT,
                0, MPI_COMM_WORLD);

    // Each process calculates sum of its own part
    int local_sum = 0;
    for (int i = 0; i < chunk; i++)
        local_sum += local[i];

    // Send all local sums back to root process
    MPI_Gather(&local_sum, 1, MPI_INT,
               sums, 1, MPI_INT,
               0, MPI_COMM_WORLD);

    // Root process prints intermediate sums and final sum
    if (rank == 0)
    {
        int total = 0;

        printf("Intermediate sums:\n");

        for (int i = 0; i < size; i++)
        {
            printf("local sum at rank %d is %d\n", i, sums[i]);
            total += sums[i];
        }

        printf("final sum = %d\n", total);
    }

    // Close MPI environment
    MPI_Finalize();

    return 0;
}



/*

Otherwise install them using:

sudo apt update
sudo apt install gcc
sudo apt install openmpi-bin libopenmpi-dev

gcc --version
mpicc --version
mpirun --version

How to run:

Save file as:
add.c

Compile:
mpicc add.c -o add

Run using 4 processors:
mpirun -np 4 ./add

OR:
mpiexec -np 4 ./add


Sample Input:
No manual input is required.

The array is automatically created as:
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20

For -np 4:
Each process gets 20 / 4 = 5 elements.

Distribution:
Rank 0 gets: 1 2 3 4 5          Local sum = 15
Rank 1 gets: 6 7 8 9 10         Local sum = 40
Rank 2 gets: 11 12 13 14 15     Local sum = 65
Rank 3 gets: 16 17 18 19 20     Local sum = 90


Sample Output:

Intermediate sums:
local sum at rank 0 is 15
local sum at rank 1 is 40
local sum at rank 2 is 65
local sum at rank 3 is 90
final sum = 210
*/