package edu.ucsb.cs56.pconrad.rational;

public class PlainArrayDemo { 
    
    public static void main (String [] args) {

		Rational [] terms = new Rational[5];
		
		int denom = 1;
		for (int i=0; i<terms.length; i++) {
			terms[i] = new Rational(1, denom);
			denom++;
		}
	
		System.out.println("Traditional for loop:");
		for (int i=0; i<terms.length; i++) {
			System.out.println("terms[" + i + "]=" + terms[i]);
		}

		System.out.println("For-each style loop:");
		for (Rational r: terms) {
			System.out.println("r=" + r);
		}


    } // main

}
