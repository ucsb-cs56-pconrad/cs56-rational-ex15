package edu.ucsb.cs56.pconrad.rational;


public class RealApproximationR2S implements Rational2String {
	/** 
		Return a string representation of a Rational number
		as a decimal with exactly three places after the decimal
		point, always.
	 */
	@Override
	public String r2s(Rational r) {
		double num = r.getNumerator();
		double denom = r.getDenominator();
		return String.format("%.3f", num/denom);
	}
}
