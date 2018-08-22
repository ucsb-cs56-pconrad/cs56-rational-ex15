package edu.ucsb.cs56.pconrad.rational;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.Before;

public class Rational2StringTest {

    private Rational r_5_15;
    private Rational r_24_6;
    private Rational r_3_7;  
    private Rational r_13_4;
    private Rational r_20_25;
    private Rational r_25_20;
    private Rational r_0_1; 
    
    @Before public void setUp() {
		r_5_15 = new Rational(5,15);
		r_24_6 = new Rational(24,6);
		r_3_7  = new Rational(3,7);
		r_13_4 = new Rational(13,4);
		r_20_25 = new Rational(20,25);
		r_0_1 = new Rational(0,1);
    }


    @Test
    public void test_BasicRational2String_1() {
		Rational r = new Rational(13,1);
		BasicRational2String br2s = new BasicRational2String();
		assertEquals("13",br2s.r2s(r));
    }


	@Test
    public void test_RealApproximationR2S_1() {
		Rational r = new Rational(13,1);
		RealApproximationR2S ra = new RealApproximationR2S();
		assertEquals("13.000",ra.r2s(r));
    }
    @Test
    public void test_RealApproximationR2S_2() {
		Rational r = new Rational(1,2);
		RealApproximationR2S ra = new RealApproximationR2S();
		assertEquals("0.500",ra.r2s(r));
    }
    @Test
    public void test_RealApproximationR2S_3() {
		Rational r = new Rational(1,3);
		RealApproximationR2S ra = new RealApproximationR2S();
		assertEquals("0.333",ra.r2s(r));
    }

    @Test
    public void test_RealApproximationR2S_4() {
		Rational r = new Rational(2,3);
		RealApproximationR2S ra = new RealApproximationR2S();
		assertEquals("0.667",ra.r2s(r));
    }

}
