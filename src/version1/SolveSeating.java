/*
 * COPYRIGHT. HSBC HOLDINGS PLC 2017. ALL RIGHTS RESERVED.
 *
 * This software is only to be used for the purpose for which it has been
 * provided. No part of it is to be reproduced, disassembled, transmitted,
 * stored in a retrieval system nor translated in any human or computer
 * language in any way or for any other purposes whatsoever without the prior
 * written consent of HSBC Holdings plc.
 */
package version1;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class SolveSeating {

    /**
     * <p>
     * <b> TODO : Insert description of the method's responsibility/role. </b>
     * </p>
     *
     * @param args
     */
    private static void getInput(final SeatingProblem1 seating) {

        // Hold user input, line by line
        String input;

        // Initialize console for user input
     /*   Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }*/

        // Number of items
        /*
         * input = c.readLine("Enter the number of items: "); if
         * (isInteger(input)) { seating.number_of_items =
         * Integer.parseInt(input); } else { System.out.println(
         * "Not a number. Please try again."); System.exit(1); }
         */
        seating.number_of_items = 24;


        for (int i = 0; i < seating.number_of_items; i++) {
        	seating.persons.add(new person(getRandomName(),1.0,((double) getRandomWeight())));
          
        }
        // Value and weight of each item
        /*
         * for (int i = 0; i < seating.number_of_items; i++) { input =
         * c.readLine( "Enter the value of item " + (i + 1) + ": "); if
         * (isDouble(input)) {
         * seating.value_of_items.add(Double.parseDouble(input)); } else {
         * System.out.println("Not a number. Please try again.");
         * System.exit(1); }
         *
         * input = c.readLine("Enter the weight of item " + (i + 1) + ": "); if
         * (isDouble(input)) {
         * seating.weight_of_items.add(Double.parseDouble(input)); } else {
         * System.out.println("Not a number. Please try again.");
         * System.exit(1); } }
         */

        /*
         * seating.value_of_items.add(Double.parseDouble("1"));
         * seating.value_of_items.add(Double.parseDouble("1"));
         * seating.value_of_items.add(Double.parseDouble("1"));
         * seating.value_of_items.add(Double.parseDouble("1"));
         * seating.value_of_items.add(Double.parseDouble("1"));
         * seating.value_of_items.add(Double.parseDouble("1"));
         *
         * seating.value_of_items.add(Double.parseDouble("1"));
         * seating.value_of_items.add(Double.parseDouble("1"));
         * seating.value_of_items.add(Double.parseDouble("1"));
         * seating.value_of_items.add(Double.parseDouble("1"));
         * seating.value_of_items.add(Double.parseDouble("1"));
         * seating.value_of_items.add(Double.parseDouble("1"));
         *
         * seating.weight_of_items.add(Double.parseDouble("10"));
         * seating.weight_of_items.add(Double.parseDouble("10"));
         * seating.weight_of_items.add(Double.parseDouble("3"));
         * seating.weight_of_items.add(Double.parseDouble("6"));
         * seating.weight_of_items.add(Double.parseDouble("2"));
         * seating.weight_of_items.add(Double.parseDouble("9"));
         *
         * seating.weight_of_items.add(Double.parseDouble("7"));
         * seating.weight_of_items.add(Double.parseDouble("5"));
         * seating.weight_of_items.add(Double.parseDouble("6"));
         * seating.weight_of_items.add(Double.parseDouble("10"));
         * seating.weight_of_items.add(Double.parseDouble("8"));
         * seating.weight_of_items.add(Double.parseDouble("9"));
         */

        // Capacity of seating
        /*
         * input = c.readLine("Enter the seating capacity: "); if
         * (isInteger(input)) { seating.seating_capacity =
         * Integer.parseInt(input); } else { System.out.println(
         * "Not a number. Please try again."); System.exit(1); }
         */
        seating.seating_capacity = 50;
        // Population size
        /*
         * input = c.readLine("Enter the population size: "); if
         * (isInteger(input)) { seating.population_size =
         * Integer.parseInt(input); } else { System.out.println(
         * "Not a number. Please try again."); System.exit(1); }
         */
        seating.population_size = 4;
        // Maximum number of generations
        /*
         * input = c.readLine("Enter the maximum number of generations: "); if
         * (isInteger(input)) { seating.maximum_generations =
         * Integer.parseInt(input); } else { System.out.println(
         * "Not a number. Please try again."); System.exit(1); }
         */
        seating.maximum_generations = 3;
        // Crossover probability
        /*
         * input = c.readLine("Enter the crossover probability: "); if
         * (isDouble(input)) { seating.prob_crossover =
         * Double.parseDouble(input); } else { System.out.println(
         * "Not a number. Please try again."); System.exit(1); }
         */
        seating.prob_crossover = 0.5;
        // Mutation probability
        /*
         * input = c.readLine("Enter the mutation probability: "); if
         * (isDouble(input)) { seating.prob_mutation =
         * Double.parseDouble(input); } else { System.out.println(
         * "Not a number. Please try again."); System.exit(1); }
         */
        seating.prob_mutation = 0.03;
    }

    static int getRandomWeight() {
        double rand_mutation = Math.random();
        int k = (int) (rand_mutation * 10);
        return k;
    }

    public static void main(final String[] args) {
        try {
            File file_name = new File("output");
            if (file_name.exists()) {
                file_name.delete();
            }
            FileOutputStream fos = new FileOutputStream(file_name, true);
            PrintStream ps = new PrintStream(fos);
            System.setOut(ps);
        } catch (FileNotFoundException e) {
            System.out.println("Problem with output file");
        }
        int no_of_tables = 5;
        ArrayList<person> seatingList = new ArrayList<>();
        SeatingProblem1 seating = new SeatingProblem1();
        getInput(seating);
        ArrayList<person> personCopy = new ArrayList<person>(seating.persons);
         //Collections.copy(weight_of_items, seating.weight_of_items);
        for (int i = 0; i < no_of_tables; i++) {
            // Get user input

        	seating.verbose = false;
            seating.mutation = false;

            seating.generation_counter = 1;

            seating.total_fitness_of_generation = 0;

           
            seating.fitness = new ArrayList<Double>();
            seating.best_fitness_of_generation = new ArrayList<Double>();
            seating.mean_fitness_of_generation = new ArrayList<Double>();
            seating.population = new ArrayList<String>();
            seating.breed_population = new ArrayList<String>();
            seating.best_solution_of_generation = new ArrayList<String>();
            // Make first generation
            seating.buildSeatingProblem();
            
            
            
            

            // Output summary
            ArrayList<Integer> finals = seating.showOptimalList();
            System.out.println();
            ArrayList<String> toRemove = new ArrayList<>();
            
           
            
            for (int h : finals) {
                System.out.println("H " + h);
                /*seating.value_of_items.remove(h - 1);
                seating.weight_of_items.remove(h - 1);*/
            	//seating.weight_of_items.set(h-1, 1*(-1.0));
                
                toRemove.add(seating.persons.get(h-1).name);

            }
            System.out.println();
            
            for(String p:toRemove)
            {
            	int counter = 0;
            	for(person k:seating.persons)
            	{
            		if(k.name.equals(p))
            		{
            			seatingList.add(k);
            			seating.persons.remove(counter);
            			
            			break;
            		}
            		else
            		{
            			counter++;
            		}
            	}
            }
            
            
           seating.number_of_items = seating.number_of_items - finals.size();

        }

        int count = 0;
        for (person i : personCopy) {
            count++;
            System.out.println(count + " " + i.weight+" name: "+ i.name);
        }
        System.out.println("final list");
        count = 0;
        for (person i : seatingList) {
            count++;
            System.out.println(count + " " + i.weight+" name: "+ i.name);
        }

    }
    
    static String getRandomName()
    {
    	char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    	StringBuilder sb = new StringBuilder();
    	Random random = new Random();
    	for (int i = 0; i < 6; i++) {
    	    char c = chars[random.nextInt(chars.length)];
    	    sb.append(c);
    	}
    	String output = sb.toString();
    	System.out.println("names: "+output);
    	return output;
    }

}
