package edu.ucsb.cs56.pconrad.rational;
    
import java.util.ArrayList;
import java.util.Comparator;

/**
   Example of sorting Rationals with lambdas
*/


public class SortRationalsWithLambdas {

	public static String toStringWithDecimals(ArrayList<Rational> nums) {
		String result = "[";
		for (int i = 0; i<nums.size(); i++) {
			result += (i==0) ? "" : ",";
			result += new RealApproximationR2S().r2s(nums.get(i));
		}
		result += "]";
		return result;			
	}
	
	
	public static ArrayList<Rational> makeRationalArrayListFromStrings( String [] strings) {
		ArrayList<Rational> nums = new ArrayList<Rational>();

		for (String s: strings) {
			nums.add(new Rational(s));
		}
		return nums;
	}

	/** 
		Comparator that orders first by denominator, then by numerator, e.g.
		<code>1, 2, 3, -5/2, 1/2, 3/2, 5/2, -7/3, 1/3, 2/3, 4/3</code>.
		
		The comparator is initialized with a Lambda Expression
	 */
	public static final Comparator<Rational> denomThenNum = (r1,r2) -> {
			int r1num = r1.getNumerator();
			int r2num = r2.getNumerator();
			int r1denom = r1.getDenominator();
			int r2denom = r2.getDenominator();
			if (r1denom==r2denom) {
				return r1num - r2num;
			} else {
				return r1denom - r2denom;
			}
		};
	
	
	public static void demonstrateSortingAndPrinting(ArrayList<Rational> nums) {		
		System.out.println("Before sorting                             = \n" + nums);
		System.out.println("  as decimals                              = \n" + toStringWithDecimals(nums));
		
		java.util.Collections.sort(nums);
		System.out.println("After sort with Collections.sort(nums)     = \n" + nums);
		System.out.println("  as decimals                              = \n" + toStringWithDecimals(nums));
		
		nums.sort(denomThenNum); 
		System.out.println("After nums.sort(denomThenNum)              = \n" + nums);
		System.out.println("  as decimals                              = \n" + toStringWithDecimals(nums));
		
		nums.sort( (r1, r2)->r1.compareTo(r2)); 
		System.out.println("After nums.sort((r1,r2)->r1.compareTo(r2)) = \n" + nums);
		System.out.println("  as decimals                              = \n" + toStringWithDecimals(nums));
	}
	
    public static void main(String args[]) {

		String [] strings1 = new String [] { "1/3", "-2/3", "4/5", "7/2", "8/9", "2/1", "8/1", "1/9", "-8/9", "3/7" };
		ArrayList<Rational> nums = makeRationalArrayListFromStrings(strings1);						
		demonstrateSortingAndPrinting(nums);
    }
    
}
