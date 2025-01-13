# N-Queens-Genetic-Algorithm
Java Solution to the N queens problem using genetic algorithms

## PROBLEM
The N queens problem deals with how many queen pieces you can fit on a chess board of size n. As long as n > 4, you can fit n number of queens on an nxn size board without attacking each other. The goal is to use genetic algorithms to solve this problem in a time efficient manner. 

## My Solution
This program uses java to efficiently solve the N queens problem using my knowledge of AI. The genetic algorithms work by randomly generating a population of possible solutions, and based on a fitness funciton of how close we are to a solution, we select which children should move on to the next generation. We also consider crossover and mutation functions which alter the nth generation so that n+1 will have variation. This is what leads us closer to a solution as the program progresses. 

## Efficiency
The program is capable to completing a 100x100 board in ~2 minutes using my 2021 Macbook Air. One good example of this completed the task in roughly 29900 generations and 125 seconds. The image of the grid is too large to fit on the screen but the output is shown below.

## Images


<img width="324" alt="Screenshot 2025-01-13 at 10 53 03 AM" src="https://github.com/user-attachments/assets/a0e77b91-66a4-43b0-9d0c-d024f859507f" />
<img width="293" alt="Screenshot 2025-01-13 at 10 52 33 AM" src="https://github.com/user-attachments/assets/1c5a317f-a04d-4ef5-85aa-8f3292d3ed81" />
