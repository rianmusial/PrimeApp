package com.rianmusial.PrimeApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimeListGenerator {
	
	private static PrimeListGenerator instance = null;
	private PrimeListGenerator() {
		generatePrimes(maxNum);
	}
	
	public static synchronized PrimeListGenerator getInstance() {
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

	public synchronized List<Long> getPrimesBetween(int x, int y) {
		if (y > maxNum)
			generatePrimes(y);
		
		List<Long> subsetPrimes = new ArrayList<Long>();
		for (int i = x + 1; i < y; i++) {
			if (primes.contains((long) i))
				subsetPrimes.add((long) i);
		}
		return subsetPrimes;
	}
	
	public static void main(String[] args) {
		PrimeListGenerator plg = PrimeListGenerator.getInstance();
		Scanner scanner = new Scanner(System.in);
		System.out.println("The application will calculate all primes between two numbers you provide.");
		boolean continueLooping = true;
		while(continueLooping) {
			System.out.println("Please provide the first number:");
			int x = Integer.parseInt(scanner.nextLine());
			System.out.println("Please provide the second number:");
			int y = Integer.parseInt(scanner.nextLine());
			
			List<Long> list = plg.getPrimesBetween(x, y);
			System.out.println("The primes between your numbers are as follows: " + list);
			
			continueLooping = getUserBoolean(scanner, "Do you wish to try another combination? (Y/N)");
		}
		System.out.println("Shutting down.");
	}

	private static boolean getUserBoolean(Scanner scanner, String message) {
		System.out.println(message);
		String userBoolean = scanner.nextLine();
		Character c = userBoolean.toUpperCase().charAt(0);
		switch(c) {
			case 'Y':
				return true;
			case 'N':
				return false;
			default:
				System.out.println("Unrecognized input.");
				return getUserBoolean(scanner, message);
		}
	}

}
