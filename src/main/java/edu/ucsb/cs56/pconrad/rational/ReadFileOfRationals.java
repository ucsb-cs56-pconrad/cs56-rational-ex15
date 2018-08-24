package edu.ucsb.cs56.pconrad.rational;
    
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

/**
   Example of reading text data from a file to initialize a sequence
   of Rational objects.

   Adapted from example 4.1 from
   https://www.mkyong.com/java8/java-8-stream-read-a-file-line-by-line/

   Also uses "try with resources" to autoclose 

   @author P. Conrad (based on program from https://www.mkyong.com/ )
   @version rational_ex11
   @see <a href="https://ucsb-cs56-pconrad.github.io/tutorials/rational_ex11/">https://ucsb-cs56-pconrad.github.io/tutorials/rational_ex11/</a>
   @see <a href="https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html">Oracle tutorial on "try with resources"</a>

*/


public class ReadFileOfRationals {

    public static final String USAGE =
		"Usage: \n" +
		"  java [-cp jar-file] edu.ucsb.cs56.pconrad.rational.ReadFileOfRationals inputFile.txt\n";

    public static void main(String args[]) {

		if (args.length != 1) {
			System.err.println("Error: Missing filename parameter");
			System.err.println(USAGE);
			System.exit(1);
		}
	
		String filename = args[0];

		ArrayList<Rational> numbers = readArrayListFromFile(filename);

		System.out.println("numbers (before sorting) = " + numbers);
		java.util.Collections.sort(numbers);
		System.out.println("numbers (after sorting) = " + numbers);
    }

    public static ArrayList<Rational> readArrayListFromFile(String fileName) {

		ArrayList<Rational> items = new ArrayList<Rational>();
	
		// Example of a "try with resources" loop
		// Short version: objects that implement "java.lang.AutoCloseable"
		// can be "automatically closed" at the end of a try with resources.
		// See link at top of program for more info
	
		try (BufferedReader br =
			 new BufferedReader(new FileReader(fileName))) {

			String line;
			while ((line = br.readLine()) != null) {
				// process "line" as input
				try {
					Rational r = new Rational(line);
					items.add(r);
				} catch (IllegalArgumentException iae) {
					System.err.println("Warning: ignored bad input line: " +
									   line);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return items;
    }
    
}
