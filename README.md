# Matrix Path Finder

This Java application finds the shortest and longest paths through a matrix, where movement is allowed horizontally (right) or diagonally (up-right or down-right).

## Overview

The Matrix Path Finder is an implementation of an algorithm that calculates all possible paths through a matrix and identifies the paths with minimum and maximum sums. The application uses a combinatorial approach to enumerate all valid paths and find optimal solutions.

## Features

- Generates a random matrix of user-specified dimensions
- Calculates all possible paths through the matrix
- Identifies the path with the smallest sum (shortest path)
- Identifies the path with the largest sum (longest path)
- Visualizes the matrix and resulting paths

## Algorithm

The algorithm works in the following way:

1. It creates address tables (R and S matrices) to track the number of paths and their starting positions
2. R matrix calculates how many paths end at each cell
3. S matrix tracks the starting indices for paths in the results matrix
4. It enumerates all possible paths through the matrix column by column
5. For each column, it extends existing paths from the previous column
6. Finally, it calculates the sum of each path and finds the minimum and maximum

## Usage

1. Run the program
2. Enter the number of rows (N) when prompted
3. Enter the number of columns (K) when prompted
4. The program will generate a random matrix and calculate the paths
5. The minimum and maximum paths along with their sums will be displayed

## Example Output

Number of rows:
N= 3
Number of columns:
K= 4
[Matrix display]
[Address tables display]
[Paths calculation]
Suma = 12
SHORTEST PATH
2 3 1 6
Suma = 28
LONGEST PATH
9 7 5 7

## Requirements

- Java 8 or higher
- Console/Terminal for input/output

## Implementation Notes

- This is a Java implementation of a algorithm written in MATLAB
- The code has been adapted to work with Java's 0-based indexing (compared to MATLAB's 1-based indexing)
- The algorithm has O(n * 3^k) time complexity where n is the number of rows and k is the number of columns

## License

MIT License
