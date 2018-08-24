package edu.ucsb.cs56.pconrad.rational;

public class Rational implements Comparable<Rational> {

    public static final boolean DEBUG=false;
    
    private int num;
    private int denom;

	/**
	   Format Rational for LaTeX.  Example: 
	   <code>$$\frac{1}{2}$$</code>. 
	   Note: Implemented as lambda function.
	 */
	
	public static final Rational2String latexFormatter = (r)->{
		int num = r.getNumerator();
		int denom = r.getDenominator();
		return ( (denom==1)? "" + num : "$$\\frac{" +
				 num + "}{" +
				 denom + "}$$" );
	};

	/**
	   Format Rational for HTML.  Example: 
	   <code>&lt;sup&gt;1&lt;/sup&gt;&amp;frasl;&lt;sub&gt;10&lt;/sub&gt;&lt;</code>
	   Note: Implemented as `public static` inner named class.
	 */
	
	public static class HTMLFormatter implements Rational2String {
		@Override
		public String r2s(Rational r) {
			int num = r.getNumerator();
			int denom = r.getDenominator();
			// Example: <sup>1</sup>&frasl;<sub>10</sub>
			// See: http://changelog.ca/log/2008/07/01/writing_fractions_in_html
			
			return ( (denom==1)? "" + num : "<sup>" +
					 num + "</sup>&frasl;<sub>" +
					 denom + "</sub>" );
		}
	}
	
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

	public class Foo implements Rational2String {
		public String r2s(Rational r) {
			return r.toString();
		}		
	}
	
	public static String markdownTable(int rows,int cols)  {
		//Foo foo = new Foo();
		//return markdownTable(rows,cols,foo.r2s(r));

		/*
		return markdownTable(rows, cols,
										new Rational2String(){
											public String r2s(Rational r) {
												return r.toString();
											}

										});
		*/
		return markdownTable(rows,cols,(r)->r.toString());
	}
    
	public static String markdownTable(int rows,
												  int cols,
												  Rational2String r2s_instance) {

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
				result += "|" + r2s_instance.r2s(r);
				// add this rational into the result
			}
			result += "|" + System.lineSeparator();
		}
		return result;
	}

	@Override
	public int compareTo(Rational o) {
		if (this.equals(o))
			return 0;
		if (this.gt(o))
			return 1;
		else
			return -1; 
	}
}
