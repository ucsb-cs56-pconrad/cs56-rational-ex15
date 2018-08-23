package edu.ucsb.cs56.pconrad.rational;

public class RationalTableDemo {
    public static void main (String [] args) {

		String htmlTable =
			Rational.markdownTable(4,5,new Rational.HTMLFormatter());
		System.out.println(htmlTable);
	}
}    
