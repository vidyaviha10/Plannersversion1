
package version2;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.List;

import Model.Guest;
import Utils.GuestToPerson;

public class SolveSeating {

	public static void getInput(final SeatingProblem1 seating) {

		

		
		seating.Guests = GuestToPerson.guests;
		
		seating.number_of_items = seating.Guests.size();

		seating.seating_capacity = 50;

		seating.population_size = 4;

		seating.maximum_generations = 3;

		seating.prob_crossover = 0.5;

		seating.prob_mutation = 1;
	}
	
	public static void startsolving(int tablesize)
	{

		/*try {
			File file_name = new File("seatingplan.txt");
			if (file_name.exists()) {
				file_name.delete();
			}
			//FileOutputStream fos = new FileOutputStream(file_name, true);
			//PrintStream ps = new PrintStream(fos);
			//System.setOut(ps);
		} catch (FileNotFoundException e) {
			System.out.println("Problem with output file");
		}*/
		int no_of_tables = 3;
		ArrayList<Guest> seatingList = new ArrayList<>();
		HashMap<Integer,String> m = new HashMap();
		HashMap<Integer,String> j = new HashMap();
		HashMap<Integer,Double> n = new HashMap();
		SeatingProblem1 seating = new SeatingProblem1();
		getInput(seating);
		seating.table_size = tablesize;
		ArrayList<Guest> personCopy = new ArrayList<Guest>(seating.Guests);
		// Collections.copy(weight_of_items, seating.weight_of_items);
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

				toRemove.add(seating.Guests.get(h - 1).name);

			}
			System.out.println();

			for (String p : toRemove) {
				int counter = 0;
				for (Guest k : seating.Guests) {
					if (k.name.equals(p)) {
						seatingList.add(k);
						if(m.containsKey(i))
						{
							m.put(i,m.get(i) + ","+k.getName());
							j.put(i,j.get(i)+","+k.getId());
							double w = n.get(i);
							n.put(i, k.getWeight() + w);
						}
						else
						{
							m.put(i,k.getName());
							n.put(i, k.getWeight());
							j.put(i,""+k.getId());
						}
						seating.Guests.remove(counter);

						break;
					} else {
						counter++;
					}
				}
			}

			seating.number_of_items = seating.number_of_items - finals.size();
		}
		
		List<Entry<Integer, Double>> sorted =  SeatingProblem1.entriesSortedByValues(n);

		int count = 0;
		for (Guest i : personCopy) {
			count++;
			System.out.println(count + " " + i.weight + " name: " + i.name);
		}
		System.out.println("final list");
		count = 0;
		for (Guest i : seatingList) {
			count++;
			System.out.println(count + " " + i.weight + " name: " + i.name);
		}

		for(Map.Entry<Integer, String> entry:m.entrySet())
		{
			System.out.println(entry.getValue());
		}
		

		try  {
			String NEW_LINE_SEPARATOR = "\n";

			FileWriter fileWriter = new FileWriter("seating.txt");
			FileWriter fileWriter1 = new FileWriter("ids.txt");
			// get the content in bytes
			for(Map.Entry<Integer, Double> entry:sorted)
			{
				int l = entry.getKey();
				String content = m.get(l);
				fileWriter.append(content);
				fileWriter.append(NEW_LINE_SEPARATOR);

				String content1 = j.get(l);
				fileWriter1.append(content1);
				fileWriter1.append(NEW_LINE_SEPARATOR);
			}
			 fileWriter.flush();
			 fileWriter.close();

			 fileWriter1.flush();
			 fileWriter1.close();
			System.out.println("Done");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}

	public static void main(final String[] args) {
		int a =0;
		try {
			System.out.println(new File("").getAbsolutePath());
			File file_name = new File("seatingplan.txt");
			if (file_name.exists()) {
				file_name.delete();
			}
			FileOutputStream fos = new FileOutputStream(file_name, true);
			//PrintStream ps = new PrintStream(fos);
			//System.setOut(ps);
		} catch (FileNotFoundException e) {
			System.out.println("Problem with output file");
		}
		int no_of_tables = 100;
		ArrayList<Guest> seatingList = new ArrayList<>();
		SeatingProblem1 seating = new SeatingProblem1();
		getInput(seating);
		ArrayList<Guest> personCopy = new ArrayList<Guest>(seating.Guests);
		// Collections.copy(weight_of_items, seating.weight_of_items);
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

				toRemove.add(seating.Guests.get(h - 1).name);

			}
			System.out.println();

			for (String p : toRemove) {
				int counter = 0;
				for (Guest k : seating.Guests) {
					if (k.name.equals(p)) {
						seatingList.add(k);
						seating.Guests.remove(counter);

						break;
					} else {
						counter++;
					}
				}
			}

			seating.number_of_items = seating.number_of_items - finals.size();

			
			if(seating.number_of_items ==0)
			{
				break;
			}
		}

		int count = 0;
		for (Guest i : personCopy) {
			count++;
			System.out.println(count + " " + i.weight + " name: " + i.name);
		}
		System.out.println("final list");
		count = 0;
		for (Guest i : seatingList) {
			count++;
			System.out.println(count + " " + i.weight + " name: " + i.name);
		}
		
		
		
		
		
		
		

	}

	static String getRandomName() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		String output = sb.toString();
		System.out.println("names: " + output);
		return output;
	}

}
