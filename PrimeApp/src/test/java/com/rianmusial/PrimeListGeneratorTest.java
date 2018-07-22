package com.rianmusial;


import java.util.List;

import org.junit.Test;

import com.rianmusial.PrimeApp.PrimeListGenerator;

import junit.framework.Assert;

public class PrimeListGeneratorTest {

	@Test
	public void testGetInstance() {
		PrimeListGenerator plg = PrimeListGenerator.getInstance();
		if (plg == null)
			throw new IllegalStateException("instance of PrimeListGenerator retrieved from getInstance() should not be null");
	}
	
	@Test
	public void testPrimeSubsetCalculation() {
		PrimeListGenerator plg = PrimeListGenerator.getInstance();
		List<Long> primes = plg.getPrimesBetween(1, 10);
		Assert.assertEquals(4, primes.size());
		long[] expectedPrimes = {
			2L, 3L, 5L, 7L
		};
		for (long num : expectedPrimes) {
			if (!primes.contains(num))
				throw new AssertionError(num + " should be in prime list");
		}
	}
	
	@Test
	public void subsetCalculationAccountsForNumbersLargerThanDefaultMax() {
		PrimeListGenerator plg = PrimeListGenerator.getInstance();
		List<Long> primes = plg.getPrimesBetween(10_000, 20_000);
		if (primes.isEmpty())
			throw new AssertionError("There is at least one prime number between 10,000 and 20,000");
	}

}
