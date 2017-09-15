package io.lambda.unit3;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class StringOccurences {
	
	public static void main(String[] args) {
		String inputStr = "Communication";
		char[] chArray = inputStr.toLowerCase().toCharArray();
		char[] chAy = chArray;
		char[] cArray = chAy;
		char[] charArray = cArray;
		Map<Character, Integer> countMap = new HashMap<>();
		for(char eachChar : charArray) {
			Character character = Character.valueOf(eachChar);
			Integer count = countMap.get(character);
			if(count == null) {
				countMap.put(character, 1);
			} else {
				countMap.put(character, ++count);
			}
		}
		System.out.println("Occurences : " + countMap);
		
		IntStream charseq = inputStr.toLowerCase().chars();
		
		IntStream istream = IntStream.of(1, 2, 3, 4, 5);
		int sum = istream
        .filter(e -> e > 2)
        .peek(e -> System.out.println("Filtered value: " + e))
        .map(e -> e * e)
        .peek(e -> System.out.println("Mapped value: " + e))
        .sum();
		System.out.println(sum);
		
		
		Map<Integer, Integer> occurrenceMap = new HashMap<>();
		charseq.forEach(e -> {
			Integer count = occurrenceMap.get(e);
			if(count == null) occurrenceMap.put(e, 1);
			else occurrenceMap.put(e, ++count);			
		});
		occurrenceMap.forEach((x, y) -> {
			System.out.println(((char)(x.intValue())) + " : " + y);
		});
				
		StringOccurences so = new StringOccurences();
		so.printPrimes(20);
		System.out.println("Count Primes : " + so.countPrimes(20));
		
	}

	private void printPrimes(int max) {
	    range(1, max).parallel().filter(this::isPrime).forEachOrdered(System.out::println);
	}
	
	private long countPrimes(int max) {
	    return range(1, max).parallel().filter(this::isPrime).count();
	}
	
	private IntStream range(int i, int max) {
		return IntStream.range(i, max);
	}

	private boolean isPrime(long n) {
	    return n > 1 && rangeClosed(2, (long) sqrt(n)).noneMatch(divisor -> n % divisor == 0);
	}

	private long sqrt(long n) {
		return (long)Math.sqrt((double)n);
	}

	private IntStream rangeClosed(int i, long max) {
		return IntStream.rangeClosed(i, (int)max);
	}
	
}
