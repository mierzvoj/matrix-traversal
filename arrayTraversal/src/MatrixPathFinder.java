import java.util.Scanner;
import java.util.Random;

public class MatrixPathFinder {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        // Program searches for the shortest and longest path
        System.out.println("Number of rows");
        System.out.print("N= ");
        int N = scanner.nextInt();

        System.out.println("Number of columns");
        System.out.print("K= ");
        int K = scanner.nextInt();

        // ADDRESS ARRAYS
        // Matrix sizes table R
        int[][] R = new int[N][K];

        // Initialize first column to 1
        for (int n = 0; n < N; n++) {
            R[n][0] = 1;
        }

        // Calculate R matrix
        for (int k = 1; k < K; k++) {
            R[0][k] = R[0][k - 1];
            if (N > 1) {
                R[0][k] += R[1][k - 1];
            }

            for (int n = 1; n < N - 1; n++) {
                R[n][k] = R[n - 1][k - 1] + R[n][k - 1] + R[n + 1][k - 1];
            }

            if (N > 1) {
                R[N - 1][k] = R[N - 2][k - 1] + R[N - 1][k - 1];
            }
        }

        System.out.println("Number of rows in cells R");
        printMatrix(R);

        // Calculate number of rows in columns P
        int[] P = new int[K];
        for (int k = 0; k < K; k++) {
            for (int n = 0; n < N; n++) {
                P[k] += R[n][k];
            }
        }

        System.out.println("Number of rows in columns P");
        printArray(P);

        // Starting address table
        int[][] S = new int[N][K];

        // Initialize first column to 0 (Java uses 0-based indexing)
        for (int n = 0; n < N; n++) {
            S[n][0] = 0;
        }

        // Initialize first and second row of other columns to 0
        for (int k = 1; k < K; k++) {
            S[0][k] = 0;
            if (N > 1) {
                S[1][k] = 0;
            }
        }

        // Initialize rest of second column
        for (int i = 2; i < N; i++) {
            S[i][1] = i - 1;
        }

        // Calculate rest of S matrix
        for (int k = 1; k < K; k++) {
            for (int n = 2; n < N; n++) {
                S[n][k] = S[n - 1][k] + R[n - 2][k - 1];
            }
        }

        System.out.println("Starting address table S");
        printMatrix(S);

        // GENERATE MATRIX
        System.out.println("The MATRIX");
        Random random = new Random();
        int[][] M = new int[N][K];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K; j++) {
                M[i][j] = random.nextInt(11); // 0-10
            }
        }
        printMatrix(M);

        // FINDING ALL PATHS
        // Find paths for the second column
        if (K < 2) {
            System.out.println("Matrix needs at least 2 columns for path finding");
            return;
        }

        int[][] X = new int[P[1]][2];
        int p = 0;

        // Handle first paths differently for safety
        int minP = Math.min(2, N);
        for (int i = 0; i < minP; i++) {
            if (p < X.length) {
                X[p][0] = M[i][0];
                X[p][1] = M[0][1];
                p++;
            }
        }

        for (int n = 1; n < N; n++) {
            for (int m = 0; m < R[n][1]; m++) {
                if (p < X.length) {
                    int x = S[n][1] + m;
                    if (x < N) {
                        X[p][0] = M[x][0];
                        X[p][1] = M[n][1];
                        p++;
                    }
                }
            }
        }

        System.out.println("Result for second column");
        printMatrix(X);

        // Find paths for columns from k=3 to K
        for (int k = 2; k < K; k++) {
            p = 0;
            int[][] Y = new int[P[k]][k + 1];

            for (int n = 0; n < N; n++) {
                for (int m = 0; m < R[n][k]; m++) {
                    if (p < Y.length) {
                        int x = S[n][k] + m;

                        // Ensure x is within bounds
                        if (x < P[k - 1]) {
                            // Copy from previous results
                            for (int v = 0; v < k; v++) {
                                Y[p][v] = X[x][v];
                            }

                            // Add current column value
                            Y[p][k] = M[n][k];
                            p++;
                        }
                    }
                }
            }

            X = Y; // Update X for next iteration
            System.out.println("Results for column " + (k + 1));
            printMatrix(X);
        }

        if (X.length == 0) {
            System.out.println("No valid paths found");
            return;
        }

        // Calculate sums and find min/max paths
        int[] A = new int[X.length];
        for (int i = 0; i < X.length; i++) {
            for (int j = 0; j < X[i].length; j++) {
                A[i] += X[i][j];
            }
        }

        // Find minimum path
        int minValue = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] < minValue) {
                minValue = A[i];
                minIndex = i;
            }
        }

        int suma = minValue;
        System.out.println("Sum = " + suma);
        System.out.println("PATH WITH THE SMALLEST SUM OF ELEMENTS");
        printRow(X[minIndex]);

        // Find maximum path
        int maxValue = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > maxValue) {
                maxValue = A[i];
                maxIndex = i;
            }
        }

        suma = maxValue;
        System.out.println("Sum = " + suma);
        System.out.println("PATH WITH THE LARGEST SUM OF ELEMENTS");
        printRow(X[maxIndex]);

        System.out.println("Generated matrix");
        printMatrix(M);

        scanner.close();
    }

    // Helper method to print a matrix
    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Helper method to print an array
    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("\n");
    }

    // Helper method to print a single row
    private static void printRow(int[] row) {
        for (int i = 0; i < row.length; i++) {
            System.out.print(row[i] + " ");
        }
        System.out.println("\n");
    }
}