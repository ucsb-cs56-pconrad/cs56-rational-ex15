public class Rational {

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
    
    public Rational() {
	this.num = 1;
	this.denom = 1;
    }

    public Rational(int num, int denom) {
	if (denom== 0) {
	    throw new IllegalArgumentException("denominator may not be zero");
	}
	this.num = num;
	this.denom = denom;
	if (num != 0) {
	    int gcd = Rational.gcd(num,denom);
	    this.num /= gcd;
	    this.denom /= gcd;
	}
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
       hashCode for Rational class.  Concatenate least significant 
       16 bits of the 32 bit int values for num and denom.

       Can you think of cases where this might be a poor choice?
       Can you think of a better choice?
     */

    @Override
    public int hashCode() {	
	int numLowerBitsOnly   = this.num   & 0x0000FFFF;
	int denomLowerBitsOnly = this.denom & 0x0000FFFF;
	return (numLowerBitsOnly << 16) | denomLowerBitsOnly;
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

    

    
}
