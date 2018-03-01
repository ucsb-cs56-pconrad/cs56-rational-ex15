package edu.ucsb.cs56.pconrad.rational;

import java.util.ArrayList;

public class ArrayListDemo { 
    
    public static void main (String [] args) {

	ArrayList<Rational> terms = new ArrayList<Rational>();
		
	int denom = 1;
	for (int i=0; i<5; i++) {
	    terms.add(new Rational(1, denom));
	    denom++;
	}
	
	System.out.println("Traditional for loop:");
	for (int i=0; i<terms.size(); i++) {
	    System.out.println("terms[" + i + "]=" + terms.get(i));
	}

	System.out.println("For-each style loop:");
	for (Rational r: terms) {
	    System.out.println("r=" + r);
	}


    } // main

}
