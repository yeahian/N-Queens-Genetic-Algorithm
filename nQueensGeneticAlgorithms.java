import java.util.Random;
import java.util.Scanner;

public class nQueensGeneticAlgorithms {

    /*
    class to make a new array of chromosomes to be tested
     */
    public static int[] newChromosome(int nVal){
        Random rand = new Random();
        int[] chromosome = new int[nVal];

        //loop creating n number of values between 1 and n
        for(int i = 0; i < nVal; i++){
            chromosome[i] = rand.nextInt(nVal) + 1;
        }

        return chromosome;
    }


    /*
    prints the working chess board
     */
    public static void printBoard(int[] chromosome, int n) {
        // Print the board
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                //checks for queen
                if (chromosome[col] == n - row) {
                    System.out.print("Q ");
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println(); //returns at end of row
        }
    }

    public static int calcFitness(int[] chromosome){
        int numColisions = 0;

        for (int row = 0; row < chromosome.length; row++) {
            for (int col = row + 1; col < chromosome.length; col++) {
                //first check if colliding horizontally, then checking the diagonals
                if (chromosome[row] == chromosome[col] || Math.abs(row - col) == Math.abs(chromosome[row] - chromosome[col])) {
                    //System.out.println(chromosome[row] + "," + chromosome[col]);
                    //System.out.println(row + "," + col);
                    numColisions++;
                }
            }
        }
        return numColisions;
    }


    /*
    This mutation function makes changes in a current chromosome so that it can evolve as we go through populations
     */
    public static int[] mutation(int[] chromosome) {
        Random rand = new Random();

        // Select a random position to mutate, and a new random value to switch
        int mutationPos = rand.nextInt(chromosome.length);
        int newVal = rand.nextInt(chromosome.length) + 1;
        chromosome[mutationPos] = newVal;

        return chromosome;
    }


    public static int[] crossover(int[] parent1, int[] parent2) {
        Random rand = new Random();
        int[] children = new int[parent1.length];

        // Select crossover point
        int splitPoint = rand.nextInt(parent1.length - 1); // Randomly select the crossover point by taking (length -1) to prevent no swapping

        //System.out.println(splitPoint);

        // swaps the two parts of the parent chromosomes (crossover)
        for (int i = 0; i < parent1.length; i++) {
            if (i <= splitPoint) {
                children[i] = parent1[i];
            } else {
                children[i] = parent2[i];
            }
        }

        return children;
    }



    /*
    In this function, we are searching through the fitnessValues array and replacing the best performing values in the array.
    We don't want to search the whole array to cut down the run time, so it will only run a couple of times.
     */
    public static int parentSelection(int[] fitnessValues){
        Random rand = new Random();

        int numIterations = 4;
        //select a random value to be the starter
        int bestValue = rand.nextInt(fitnessValues.length);

        //loop (numIterations) number of times to find replacement values
        for(int i = 0; i < numIterations; i++){
            int temp = rand.nextInt(fitnessValues.length);
            if(fitnessValues[bestValue] < fitnessValues[temp]){
                bestValue = temp;
            }
        }
        return bestValue;
    }




    /*
        IAN NOTES

        use a fitness function to determine which variations will move on to the next generation
            - select the best ones by calculating how many bad moves they made, until the program works correctly

        FUNCTIONS
            chromosome creation

            check for collisions

            fitness

            mutation

            crossover

            parentSelection

         */


    /*
    This function is the main part of the program; main calls this as many times as we the user would like
     */
    public static void geneticAlgorithm(int boardSize){
        int genNumber = 1;
        int populationSize = 100;
        double mutationRate = 0.3;

        //This is to increase the population size and mutation rate when we get to the higher n values that usually give problems.
        if(boardSize > 8 && boardSize <= 12){
            populationSize = 500;
            mutationRate = 0.6;
        }
        else if (boardSize > 12) {
            populationSize = 1000;
            mutationRate = 0.9;
        }

        //starting of the timer and creation of the population/ fitness arrays.
        long startTime = System.currentTimeMillis();
        int[][] population = new int[populationSize][boardSize];
        int[] fitnessValues = new int[populationSize];


        //filling the first population and creating an array that has all the fitness values for the respective positions
        for (int i = 0; i < populationSize; i++) {
            population[i] = newChromosome(boardSize);
            fitnessValues[i] = calcFitness(population[i]);
        }


        // Continue looping until a solution is found or maximum generations reached
        while (true) {
            int[][] newPopulation = new int[populationSize][boardSize];
            int[] newFitnessValues = new int[populationSize];
            int totalFitness = 0;


            //calculations for the fitness of each population
            for (int i = 0; i < populationSize; i++) {
                totalFitness += fitnessValues[i];
            }
            double averageFitness = (double) totalFitness / populationSize;
            System.out.println("Generation: " + genNumber + " Average Fitness: " + averageFitness);


            //loop dealing with the crossover and mutations in each generation.
            for (int i = 0; i < populationSize; i++) {

                //randomly looks for two parents to be altered for the next generation
                int parent1 = parentSelection(fitnessValues);
                int parent2 = parentSelection(fitnessValues);
                //System.out.println(parent1 + "," + parent2);


                //makes the crossover of the two parents, and places it in the population
                int[] child = crossover(population[parent1], population[parent2]);
                if (Math.random() < mutationRate) {
                    child = mutation(child);
                }


                //updating populations if we have a child with better fitness
                int childFitness = calcFitness(child);
                if (childFitness < fitnessValues[i]) {
                    newPopulation[i] = child;
                    newFitnessValues[i] = childFitness;
                } else {
                    newPopulation[i] = population[i];
                    newFitnessValues[i] = fitnessValues[i];
                }


                // Check if a solution is found and print the board and all the statistics
                if (childFitness == 0) {
                    printBoard(child, boardSize);
                    System.out.println("Solution found in generation: " + genNumber);
                    long endTime = System.currentTimeMillis();
                    double seconds = (endTime - startTime) / 1000.0;
                    System.out.println("Time taken: " + seconds + " seconds");
                    //System.out.println("Population Size: " + populationSize + "\nMutation Rate: " + mutationRate);
                    return;
                }
            }
            //if solution not found, changes values for the next generation
            population = newPopulation;
            fitnessValues = newFitnessValues;
            genNumber++;
        }
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        do {
            System.out.println("Please enter the size of the board greater than 4, or -1 to quit: ");
            int boardSize = userInput.nextInt();
            System.out.println(boardSize);

            //check if user input matches what we are looking for
            if (boardSize >= 4) {
                geneticAlgorithm(boardSize);

            } else if(boardSize == -1){
                break;
            }
            else {
                System.out.println("That number is not greater than 4.");
            }
        } while (true);
    }
}


