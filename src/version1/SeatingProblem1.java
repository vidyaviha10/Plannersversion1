package version1;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class SeatingProblem1 {

    public int table_size = 5;
    public boolean verbose = false;
    public boolean mutation = false;
    public int crossover_count = 0;
    public int clone_count = 0;
    public int number_of_items = 0;
    public int population_size = 0;
    public int maximum_generations = 0;
    public int generation_counter = 1;
    public double seating_capacity = 0;
    public double prob_crossover = 0;
    public double prob_mutation = 0;
    public double total_fitness_of_generation = 0;
    public ArrayList<person> persons = new ArrayList<person>();
   
    public ArrayList<Double> fitness = new ArrayList<Double>();
    public ArrayList<Double> best_fitness_of_generation = new ArrayList<Double>();
    public ArrayList<Double> mean_fitness_of_generation = new ArrayList<Double>();
    public ArrayList<String> population = new ArrayList<String>();
    public ArrayList<String> breed_population = new ArrayList<String>();
    public ArrayList<String> best_solution_of_generation = new ArrayList<String>();


    /**
     * Main method
     */
    public static void main(final String[] args) {

        // Check for command line argument output_filename
        // If filename present, redirect all System.out to file
        if (args.length == 1) {

        }

        // Construct SeatingProblem instance and pass control
        SeatingProblem1 seating = new SeatingProblem1();

        // Construct graph of mean fitness by generation
        // SimpleGraph graph = new
        // SimpleGraph(seating.mean_fitness_of_generation, "Mean Fitness by
        // Generation");

    }


    /**
     * Default constructor
     */
    public SeatingProblem1() {


    }


    /**
     * Controls seating problem logic and creates first generation
     */
    public void buildSeatingProblem() {

        // Generate initial random population (first generation)
        this.makePopulation();

        // Start printing out summary
        System.out.println("\nInitial Generation:");
        System.out.println("===================");
        System.out.println("Population:");
        for (int i = 0; i < this.population_size; i++) {
            System.out.println((i + 1) + " - " + this.population.get(i));
        }

        // Evaluate fitness of initial population members
        this.evalPopulation();

        // Output fitness summary
        System.out.println("\nFitness:");
        for (int i = 0; i < this.population_size; i++) {
            System.out.println((i + 1) + " - " + this.fitness.get(i));
        }

        // Find best solution of generation
        this.best_solution_of_generation.add(this.population.get(this.getBestSolution()));

        // Output best solution of generation
        System.out.println("\nBest solution of initial generation: " + this.best_solution_of_generation.get(0));

        // Find mean solution of generation
        this.mean_fitness_of_generation.add(this.getMeanFitness());

        // Output mean solution of generation
        System.out.println("Mean fitness of initial generation: " + this.mean_fitness_of_generation.get(0));

        // Compute fitness of best solution of generation
        this.best_fitness_of_generation.add(this.evalGene(this.population.get(this.getBestSolution())));

        // Output best fitness of generation
        System.out.println("Fitness score of best solution of initialgeneration: " + this.best_fitness_of_generation.get(0));

        // If maximum_generations > 1, breed further generations
        if (this.maximum_generations > 1) {
            makeFurtherGenerations();
        }

    }


    /**
     * Makes further generations beyond first, if necessary
     */
    public void makeFurtherGenerations() {

        // Breeding loops maximum_generation number of times at most
        for (int i = 1; i < this.maximum_generations; i++) {

            // Check for stopping criterion
            if ((this.maximum_generations > 4) && (i > 4)) {

                // Previous 3 generational fitness values
                double a = this.mean_fitness_of_generation.get(i - 1);
                double b = this.mean_fitness_of_generation.get(i - 2);
                double c = this.mean_fitness_of_generation.get(i - 3);

                // If all are 3 equal, stop
                if (a == b && b == c) {
                    System.out.println("\nStop criterion met");
                    this.maximum_generations = i;
                    break;
                }
            }

            // Reset some counters
            this.crossover_count = 0;
            this.clone_count = 0;
            this.mutation = false;

            // Breed population
            for (int j = 0; j < this.population_size / 2; j++) {
                this.breedPopulation();
            }

            // Clear fitness values of previous generation
            this.fitness.clear();

            // Evaluate fitness of breed population members
            this.evalBreedPopulation();

            // Copy breed_population to population
            for (int k = 0; k < this.population_size; k++) {
                this.population.set(k, this.breed_population.get(k));
            }

            // Output population
            System.out.println("\nGeneration " + (i + 1) + ":");
            if ((i + 1) < 10) {
                System.out.println("=============");
            }
            if ((i + 1) >= 10) {
                System.out.println("==============");
            }
            if ((i + 1) >= 100) {
                System.out.println("===============");
            }
            System.out.println("Population:");
            for (int l = 0; l < this.population_size; l++) {
                System.out.println((l + 1) + " - " + this.population.get(l));
            }

            // Output fitness summary
            System.out.println("\nFitness:");
            for (int m = 0; m < this.population_size; m++) {
                System.out.println((m + 1) + " - " + this.fitness.get(m));
            }

            // Clear breed_population
            this.breed_population.clear();

            // Find best solution of generation
            this.best_solution_of_generation.add(this.population.get(this.getBestSolution()));

            // Output best solution of generation
            System.out.println("\nBest solution of generation " + (i + 1) + ": " + this.best_solution_of_generation.get(i));

            // Find mean solution of generation
            this.mean_fitness_of_generation.add(this.getMeanFitness());

            // Output mean solution of generation
            System.out.println("Mean fitness of generation: " + this.mean_fitness_of_generation.get(i));

            // Compute fitness of best solution of generation
            this.best_fitness_of_generation.add(this.evalGene(this.population.get(this.getBestSolution())));

            // Output best fitness of generation
            System.out
            .println("Fitness score of best solution of generation " + (i + 1) + ": " + this.best_fitness_of_generation.get(i));

            // Output crossover/cloning summary
            System.out.println("Crossover occurred " + this.crossover_count + " times");
            System.out.println("Cloning occurred " + this.clone_count + " times");
            if (this.mutation == false) {
                System.out.println("Mutation did not occur");
            }
            if (this.mutation == true) {
                System.out.println("Mutation did occur");
            }
        }
    }

    public void stopCriterion() {
        // SimpleGraph graph = new SimpleGraph(this.mean_fitness_of_generation,
        // "Mean Fitness by Generation");

    }


    /**
     * Output SeatingProblem summary
     */
    public ArrayList<Integer> showOptimalList() {
        ArrayList<Integer> finalls = new ArrayList<Integer>();
        // Output optimal list of items
        System.out.println("\nOptimal list of items to include in seating: ");

        double best_fitness = 0;
        int best_gen = 0;

        // First, find best solution out of generational bests
        for (int z = 0; z < this.maximum_generations - 1; z++) {
            if (this.best_fitness_of_generation.get(z) > best_fitness) {
                best_fitness = this.best_fitness_of_generation.get(z);
                best_gen = z;
            }
        }

        // Then, go through that's generation's best solution and output items
        String optimal_list = this.best_solution_of_generation.get(best_gen);
        int counter = 0;
        for (int y = 0; y < this.number_of_items; y++) {
            if (optimal_list.substring(y, y + 1).equals("1")) {
                System.out.print((y + 1) + " "+" name: "+this.persons.get(y).name);
                finalls.add(y + 1);
            }
        }

        System.out.println();

        return finalls;
    }


    /**
     * Breeds current population to create a new generation's population
     */
    public void breedPopulation() {

        // 2 genes for breeding
        int gene_1;
        int gene_2;

        // Increase generation_counter
        this.generation_counter = this.generation_counter + 1;

        // If population_size is odd #, use elitism to clone best solution of
        // previous generation
        if (this.population_size % 2 == 1) {
            this.breed_population.add(this.best_solution_of_generation.get(this.generation_counter - 1));
        }

        // Get positions of pair of genes for breeding
        gene_1 = selectGene();
        gene_2 = selectGene();

        // Crossover or cloning
        crossoverGenes(gene_1, gene_2);

    }


    /**
     * Performs mutation, if necessary
     */
    public void mutateGene() {

        // Decide if mutation is to be used
        double rand_mutation = Math.random();
        if (rand_mutation <= this.prob_mutation) {

            // If so, perform mutation
            this.mutation = true;
            String mut_gene;
            String new_mut_gene;
            Random generator = new Random();
            int mut_point = 0;
            double which_gene = Math.random() * 100;
            System.out.println("ensuring size in mutation");
            // Mutate gene
            if (which_gene <= 50) {
                mut_gene = this.breed_population.get(this.breed_population.size() - 1);
                mut_point = generator.nextInt(this.number_of_items);
                if (mut_gene.substring(mut_point, mut_point + 1).equals("1")) {
                    new_mut_gene = mut_gene.substring(0, mut_point) + "0" + mut_gene.substring(mut_point);
                    new_mut_gene = ensureSize(new_mut_gene);
                    this.breed_population.set(this.breed_population.size() - 1, new_mut_gene);
                }
                if (mut_gene.substring(mut_point, mut_point + 1).equals("0")) {
                    new_mut_gene = mut_gene.substring(0, mut_point) + "1" + mut_gene.substring(mut_point);
                    new_mut_gene = ensureSize(new_mut_gene);
                    this.breed_population.set(this.breed_population.size() - 1, new_mut_gene);
                }
            }
            if (which_gene > 50) {
                mut_gene = this.breed_population.get(this.breed_population.size() - 2);
                mut_point = generator.nextInt(this.number_of_items);
                if (mut_gene.substring(mut_point, mut_point + 1).equals("1")) {
                    new_mut_gene = mut_gene.substring(0, mut_point) + "0" + mut_gene.substring(mut_point);
                    new_mut_gene = ensureSize(new_mut_gene);
                    this.breed_population.set(this.breed_population.size() - 1, new_mut_gene);

                }
                if (mut_gene.substring(mut_point, mut_point + 1).equals("0")) {
                    new_mut_gene = mut_gene.substring(0, mut_point) + "1" + mut_gene.substring(mut_point);
                    new_mut_gene = ensureSize(new_mut_gene);
                    this.breed_population.set(this.breed_population.size() - 2, new_mut_gene);
                }
            }
        }
    }


    /**
     * Selects a gene for breeding
     *
     * @return int - position of gene in population ArrayList to use for
     *         breeding
     */
    public int selectGene() {

        // Generate random number between 0 and total_fitness_of_generation
        double rand = Math.random() * this.total_fitness_of_generation;

        // Use random number to select gene based on fitness level
        for (int i = 0; i < this.population_size; i++) {
            if (rand <= this.fitness.get(i)) {
                return i;
            }
            rand = rand - this.fitness.get(i);
        }

        // Not reachable; default return value
        return 0;
    }


    /**
     * Performs either crossover or cloning
     */
    public void crossoverGenes(final int gene_1, final int gene_2) {
        System.out.println("cross over");
        // Strings to hold new genes
        String new_gene_1;
        String new_gene_2;

        // Decide if crossover is to be used
        double rand_crossover = Math.random();
        if (rand_crossover <= this.prob_crossover) {
            // Perform crossover
            this.crossover_count = this.crossover_count + 1;
            Random generator = new Random();
            int cross_point = generator.nextInt(this.number_of_items) + 1;

            // Cross genes at random spot in strings
            new_gene_1 = this.population.get(gene_1).substring(0, cross_point) + this.population.get(gene_2).substring(cross_point);
            new_gene_2 = this.population.get(gene_2).substring(0, cross_point) + this.population.get(gene_1).substring(cross_point);

            // make sure size remains equal to table size and only low
            // weightage is made zero.
            System.out.println("ensuring size in crossover");

            new_gene_1 = ensureSize(new_gene_1);
            new_gene_2 = ensureSize(new_gene_2);

            // Add new genes to breed_population
            this.breed_population.add(new_gene_1);
            this.breed_population.add(new_gene_2);
        } else {
            // Otherwise, perform cloning
            this.clone_count = this.clone_count + 1;
            this.breed_population.add(this.population.get(gene_1));
            this.breed_population.add(this.population.get(gene_2));
        }

        // Check if mutation is to be performed
        mutateGene();
    }


    /**
     * Gets best solution in population
     *
     * @return int - position of best solution in population
     */
    public int getBestSolution() {
        int best_position = 0;
        double this_fitness = 0;
        double best_fitness = 0;
        for (int i = 0; i < this.population_size; i++) {
            this_fitness = evalGene(this.population.get(i));
            if (this_fitness > best_fitness) {
                best_fitness = this_fitness;
                best_position = i;
            }
        }
        return best_position;
    }


    /**
     * Gets mean fitness of generation
     */
    public double getMeanFitness() {
        double total_fitness = 0;
        double mean_fitness = 0;
        for (int i = 0; i < this.population_size; i++) {
            total_fitness = total_fitness + this.fitness.get(i);
        }
        mean_fitness = total_fitness / this.population_size;
        return mean_fitness;
    }


    /**
     * Evaluates entire population's fitness, by filling fitness ArrayList with
     * fitness value of each corresponding population member gene
     */
    public void evalPopulation() {
        this.total_fitness_of_generation = 0;
        for (int i = 0; i < this.population_size; i++) {
            double temp_fitness = evalGene(this.population.get(i));
            this.fitness.add(temp_fitness);
            this.total_fitness_of_generation = this.total_fitness_of_generation + temp_fitness;
        }
    }


    /**
     * Evaluates entire breed_population's fitness, by filling breed_fitness
     * ArrayList with fitness value of each corresponding breed_population
     * member gene
     */
    public void evalBreedPopulation() {
        this.total_fitness_of_generation = 0;
        for (int i = 0; i < this.population_size; i++) {
            double temp_fitness = evalGene(this.breed_population.get(i));
            this.fitness.add(temp_fitness);
            this.total_fitness_of_generation = this.total_fitness_of_generation + temp_fitness;
        }
    }


    /**
     * Evaluates a single gene's fitness, by calculating the total_weight of
     * items selected by the gene
     *
     * @return double - gene's total fitness value
     */
    public double evalGene(final String gene) {
        double total_weight = 0;
        //double total_value = 0;
        double fitness_value = 0;
        double difference = 0;
        char c = '0';
        System.out.println("inside evalGene: " + gene);
        // Get total_weight associated with items selected by this gene
        for (int j = 0; j < this.number_of_items; j++) {
            c = gene.charAt(j);
            // If chromosome is a '1', add corresponding item position's
            // weight to total weight
            if (c == '1') {
                total_weight = total_weight + this.persons.get(j).weight;
                System.out.println("this.weight_of_items.get(j): "+this.persons.get(j).weight);
               // total_value = total_value + this.value_of_items.get(j);
            }
        }
        // Check if gene's total weight is less than seating capacity
        difference = this.seating_capacity - total_weight;
        System.out.println("difference: " + difference);
        if (difference >= 0) {
            // This is acceptable; calculate a fitness_value
            // Otherwise, fitness_value remains 0 (default), since seating
            // cannot hold all items selected by gene
            // Fitness value is simply total value of acceptable permutation,
            // and for unacceptable permutation is set to '0'
            fitness_value = total_weight;
        }

        // Return fitness value
        return fitness_value;
    }


    /**
     * Makes a population by filling population ArrayList with strings of
     * length number_of_items, each element a gene of randomly generated
     * chromosomes (1s and 0s)
     */


    public void makePopulation() {


        Map<Integer, Double> map = new HashMap<>();
        for (int i = 0; i < this.number_of_items; i++) {
System.out.println("!!!!!!: "+ this.persons.get(i).weight);
            map.put(i, this.persons.get(i).weight);

        }

        List<Entry<Integer, Double>> sorted = entriesSortedByValues(map);
        
        for(Entry<Integer, Double> e:sorted)
        {
        	System.out.print(" "+e.getValue());
        }
        System.out.println();
        int[] ss = new int[this.number_of_items];
        StringBuilder sb = new StringBuilder();
        int counter = 0;

        for (Entry entry : sorted) {
            int position = (Integer) entry.getKey();
            ss[position] = 1;
            counter++;
            if (counter == this.table_size) {
                break;
            }
        }


        for (Integer s : ss) {
            sb.append(s); // sb.append("\t");

        }

        String g = sb.toString();
        g = g.replace(",", "");
        g = g.replace("[", "");
        g = g.replace("]", "");
        g = g.replace(" ", "");
        g = g.trim();

        this.population.add(g);
        for (int i = 1; i < this.population_size; i++) {
            // Calls makeGene() once for each element position
            this.population.add(makeGene());
        }
    }


    /**
     * Generates a single gene, a random String of 1s and 0s
     *
     * @return String - a randomly generated gene
     */
    public String makeGene() {

        // Stringbuilder builds gene, one chromosome (1 or 0) at a time
        StringBuilder gene = new StringBuilder(this.number_of_items);

        // Each chromosome
        char c;
        int counter = 0;
        boolean allZero = false;
        // Loop creating gene
        for (int i = 0; i < this.number_of_items; i++) {
            c = '0';
            double rnd = Math.random();
            double rnd1 = Math.random();
            double rnd2 = Math.random();
            // If random number is greater than 0.5, chromosome is '1'
            // If random number is less than 0.5, chromosome is '0'
            if (!allZero && rnd + rnd1 + rnd2 > 1.5) {
                int d = (int) (rnd + rnd1 + rnd2);
                if (d % 2 == 0 && rnd + rnd1 < 1) {
                    c = '1';
                    counter++;
                }
                if (counter == this.table_size) {
                    allZero = true;
                }
            } else {
                if (!allZero) {
                    int d = (int) (rnd + rnd1 + rnd2);
                    if (d % 2 != 0 && rnd + rnd2 < 1) {
                        c = '1';
                        counter++;
                    }
                    if (counter == this.table_size) {
                        allZero = true;
                    }
                }
            }
            // Append chromosome to gene
            gene.append(c);
        }
        // Stringbuilder object to string; return
        return gene.toString();
    }


    /**
     * Collects user input to be used as parameters for seating problem
     */


    /**
     * Determines if input string can be converted to integer
     *
     * @param String
     *            - string to be checked
     * @return boolean - whether or not string can be converted
     */
    public static boolean isInteger(final String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }


    /**
     * Determines if input string can be converted to double
     *
     * @param String
     *            - string to be checked
     * @return boolean - whether or not string can be converted
     */
    public static boolean isDouble(final String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public String ensureSize(final String gene) {

        String tempgene = "";
        if (gene.length() > this.number_of_items) {
            tempgene = gene.substring(0, this.number_of_items);
        } else {
            tempgene = gene;
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@ gener: " + gene + " tempgene " + tempgene+" gene.length() "+gene.length()+" number_of_items "+number_of_items);
        Map<Integer, Double> map = new HashMap<>();
        for (int i = 0; i < tempgene.length(); i++) {
            if (tempgene.charAt(i) == '1') {
                map.put(i, this.persons.get(i).weight);
            } else {
                continue;
            }
        }

        /*
         * List<Integer> sortedKeys = new ArrayList<>(map.values());
         * Collections.sort(sortedKeys); for (Integer key : sortedKeys) {
         * Integer value = map.get(key); //**System.out.println(value); // do
         * something }
         */
        int[] ss = new int[this.number_of_items];
        StringBuilder sb = new StringBuilder();

        // {"0", "0", "0", "0", "0", "0", "0", "0"};
        System.out.println(entriesSortedByValues(map));
        int counter = 0;
        List<Entry<Integer, Double>> sorted = entriesSortedByValues(map);
        for (Entry entry : sorted) {
            int position = (Integer) entry.getKey();
            ss[position] = 1;
            counter++;
            if (counter == this.table_size) {
                break;
            }
        }


        for (Integer s : ss) {
            sb.append(s);
            // sb.append("\t");
        }

        String g = sb.toString();
        g = g.replace(",", "");
        g = g.replace("[", "");
        g = g.replace("]", "");
        g = g.replace(" ", "");
        g = g.trim();
        System.out.println("returning s: " + g);
        return g;
    }

    static <K, V extends Comparable<? super V>> List<Entry<K, V>> entriesSortedByValues(final Map<K, V> map) {

        List<Entry<K, V>> sortedEntries = new ArrayList<Entry<K, V>>(map.entrySet());

        Collections.sort(sortedEntries, new Comparator<Entry<K, V>>() {
            @Override
            public int compare(final Entry<K, V> e1, final Entry<K, V> e2) {
                return e2.getValue().compareTo(e1.getValue());
            }
        });

        return sortedEntries;
    }

    int getRandomWeight() {
        double rand_mutation = Math.random();
        int k = (int) (rand_mutation * 10);
        return k;
    }

} // SeatingProblem