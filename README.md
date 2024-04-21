# N-Queens-Genetic-Algorithm
Java Solution to the N queens problem using genetic algorithms

***Switch to code view to see the correct output***

PROBLEM: The N queens problem deals with how many queen pieces you can fit on a chess board of size n. As long as n > 4, you can fit n number of queens on an nxn size board without attacking each other. The goal is to use genetic algorithms to solve this problem in a time efficient manner. 

This program uses java to efficiently solve the N queens problem using my knowledge of AI. The genetic algorithms work by randomly generating a population of possible solutions, and based on a fitness funciton of how close we are to a solution, we select which children should move on to the next generation. We also consider crossover and mutation functions which alter the nth generation so that n+1 will have variation. This is what leads us closer to a solution as the program progresses. 

----Example output of the solution for a 10x10 board size----

Generation: 116 Average Fitness: 2.454
 _ _ _ _ Q _ _ _ _ _ 
 
 _ _ _ _ _ _ Q _ _ _ 
 
 _ Q _ _ _ _ _ _ _ _ 
 _ _ _ _ _ _ _ _ _ Q 
 _ _ _ _ _ _ _ Q _ _ 
 _ _ _ Q _ _ _ _ _ _ 
 Q _ _ _ _ _ _ _ _ _ 
 _ _ Q _ _ _ _ _ _ _ 
 _ _ _ _ _ _ _ _ Q _ 
 _ _ _ _ _ Q _ _ _ _ 
 Solution found in generation: 116
Time taken: 0.047 seconds


The program is capable to completing a 100x100 board in ~2 minutes using my 2021 Macbook Air. One good example of this completed the task in roughly 35000 generations and 130 seconds.




