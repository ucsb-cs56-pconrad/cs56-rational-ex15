package edu.ucsb.cs56.pconrad.rational;

public class Rational {

    public static final boolean DEBUG=false;
    
    private int num;
    private int denom;

    /** 
	greatest common divisor of a and b
	@param a first number
	@param b second number
	@return gcd of a and b
    */
    public static int gcd(int a, int b) {
	if (a==0)
	    return b;
	else if (b==0)
	    return a;
	else
	    return gcd(b%a, a);
    }

    public Rational(String s) {
	String [] parts = s.split("/");
	if (parts.length != 2) {
	    throw new IllegalArgumentException("must contain /");	   
	}
	String numString = parts[0].trim();
	String denomString = parts[1].trim();
	try {
	    num = Integer.parseInt(numString);
	} catch (NumberFormatException nfe) {
	    throw new IllegalArgumentException
		("bad numerator: " + numString);
	}
	try {
	    denom = Integer.parseInt(denomString);
	    if (denom== 0) {
		throw new IllegalArgumentException
		    ("denominator may not be zero");
	    }
	} catch (NumberFormatException nfe) {
	    throw new IllegalArgumentException
		("bad denominator: " + denomString);
	}
	this.rationalize();	
    } // Rational(String)

    
    public Rational() {
	this.num = 1;
	this.denom = 1;
    }

    private void rationalize() {
	if (this.num != 0) {
	    int gcd = Rational.gcd(this.num,this.denom);
	    this.num /= gcd;
	    this.denom /= gcd;
	}
	if ( this.denom < 0 ) {
	    // if both are negative makes both positive
	    // if only denom negative, moves sign to numerator
	    this.num = (-this.num);
	    this.denom = (-this.denom);
	}
    }
    
    public Rational(int num, int denom) {
	if (denom== 0) {
	    throw new IllegalArgumentException("denominator may not be zero");
	}
	this.num = num;
	this.denom = denom;
	this.rationalize();
    }

    public String toString() {
	if (denom == 1 || num == 0)
	    return "" + num;
	return num + "/" + denom;
    }

    public int getNumerator() { return this.num; }
    public int getDenominator() { return this.denom; }

    public Rational times(Rational r) {
	return new Rational(this.num * r.num,
			    this.denom * r.denom);
    }

    public static Rational product(Rational a, Rational b) {
	return new Rational(a.num * b.num,
			    a.denom * b.denom);
    }

    /**
       hashCode for Rational class.   Piggyback off of java.lang.String.hashCode()

     */

	@Override
    public int hashCode() {
		return this.toString().hashCode();
    }
    
    /** 
	return true if and only if numerators and denominators are equal
     */
    @Override
    public boolean equals(Object o) {

	// Start: boilerplate code for .equals
	
	if (this == o) return true;
	if (o == null) return false;
	if (getClass() != o.getClass()) return false;
	Rational r = (Rational) o;

	// End boilerplate
	
	return this.num == r.num && this.denom == r.denom;
    }

    public static int lcm(int a, int b) {
	return Math.abs(a*b)/gcd(a,b);
    }

    /**
       less than
    */
    public boolean lt(Rational other) {
	int commonDenominator = Math.abs(lcm(this.denom,other.denom));

	
	int thisNewNum = this.num * (commonDenominator / this.denom);
	int otherNewNum = other.num * (commonDenominator / other.denom);

	if (Rational.DEBUG) {
	    System.err.println("commonDenominator="+commonDenominator);
	    System.err.println("this=" + this);
	    System.err.println("other=" + other);
	    System.err.println("thisNewNum=" + thisNewNum);
	    System.err.println("otherNewNum=" + otherNewNum);
	}
	
	return (thisNewNum - otherNewNum) < 0;
	
    }

    /**
       less than or equal
    */
    
    public boolean le(Rational other) {
	return this.equals(other) || this.lt(other);
    }


    /**
       greater than
    */
    
    public boolean gt(Rational other) {
	return ! this.le(other);
    }


    /**
       greater than or equal
    */
    
    public boolean ge(Rational other) {
	return ! this.lt(other);
    }

    
    /** 
	For testing getters.  
	@param args unused
     */

    public static void main (String [] args) {
	Rational r = new Rational(5,7);
	System.out.println("r.getNumerator()=" + r.getNumerator());
	System.out.println("r.getDenominator()=" + r.getDenominator());
	System.out.println("Integer.toHexString(r.hashCode())=" +
			   Integer.toHexString(r.hashCode()));
	Rational r2 = new Rational(0x01234567,0x89ABCDEF);
	System.out.println("Integer.toHexString(r2.getNumerator())=" +
			   Integer.toHexString(r2.getNumerator()));
	System.out.println("Integer.toHexString(r2.getDenominator())=" +
			   Integer.toHexString(r2.getDenominator()));
	System.out.println("Integer.toHexString(r2.hashCode())=" +
			   Integer.toHexString(r2.hashCode()));

	Rational r3 = new Rational(0x89ABCDEF,0x01234567);
	System.out.println("Integer.toHexString(r3.getNumerator())=" +
			   Integer.toHexString(r3.getNumerator()));
	System.out.println("Integer.toHexString(r3.getDenominator())=" +
			   Integer.toHexString(r3.getDenominator()));
	System.out.println("Integer.toHexString(r3.hashCode())=" +
			   Integer.toHexString(r3.hashCode()));
    }

    
	public static String tableOfRationalsMarkdown(int rows,int cols)  {

		/* 
		   String expected =
			"| |1|2|3|4|" + newline +
			"|-|-|-|-|-|" + newline +
			"|1|1|2|3|4|" + newline +
			"|2|1/2|1|3/2|2|" + newline +
			"|3|1/3|2/3|1|4/3|" + newline;

		*/
		
		String result = "| ";

		// generate the first line of header

		for (int i=1; i<=cols; i++) {
			result += "|" + i;
		}
		result += "|" + System.lineSeparator();

		// generate the second line of header
		// extra column for the column labels
		
		for (int i=1; i<=(cols+1); i++) {
			result += "|-" ;
		}
		result += "|" + System.lineSeparator();

		// generate the rest of the table
		
		for (int denom=1; denom<=rows; denom++) {
			result += "|" + denom;
			for (int num=1; num<=cols; num++) {
				Rational r = new Rational(num,denom);
				result += "|" + r.toString();
				// add this rational into the result
			}
			result += "|" + System.lineSeparator();
		}
		return result;
	}
    
}
