package com.rianmusial.PrimeApp;

import java.util.ArrayList;
import java.util.List;

public class PrimeListGenerator {
	
	private static PrimeListGenerator instance = null;
	private PrimeListGenerator() {
		generatePrimes(maxNum);
	}
	
	public static PrimeListGenerator getInstance() {
		if (instance == null)
			instance = new PrimeListGenerator();
		return instance;
	}
	
	private Long maxNum = 10_000L;
	private List<Long> primes = new ArrayList<>();
	
	private void generatePrimes(long max) {
		this.maxNum = max;
		generatePrimes();
	}

	private void generatePrimes() {
		List<Boolean> primeStates = initializePrimeStateList();		
		filterPrimeStatesUsingSieve(primeStates);		
		List<Long> primes = getPrimeListFromStateList(primeStates);
		
		this.primes = primes;
	}

	private List<Long> getPrimeListFromStateList(List<Boolean> primeStates) {
		List<Long> primes = new ArrayList<>();
		for (int i = 0; i < primeStates.size(); i++) {
			if (primeStates.get(i))
				primes.add((long) i);
		}
		return primes;
	}

	private void filterPrimeStatesUsingSieve(List<Boolean> primeStates) {
		for (long i = 2; i < maxNum; i++) {
			long j = 2L;
			long product = i * j;
			while (product <= maxNum) {
				primeStates.set((int) product, false);
				j++;
				product = i * j;
			}
		}
	}

	private List<Boolean> initializePrimeStateList() {
		List<Boolean> primeStates = new ArrayList<>();
		for (long i = 0; i <= maxNum; i++) {
			primeStates.add(true);
		}
		
		primeStates.set(0, false);
		primeStates.set(1, false);
		return primeStates;
	}

	public List<Long> getPrimesBetween(int x, int y) {
		if (y > maxNum)
			generatePrimes(y);
		
		List<Long> subsetPrimes = new ArrayList<Long>();
		for (int i = x + 1; i < y; i++) {
			if (primes.contains((long) i))
				subsetPrimes.add((long) i);
		}
		return subsetPrimes;
	}

}
