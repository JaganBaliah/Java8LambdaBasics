package io.lambda.unit3;

import java.util.Arrays;
import java.util.List;

import io.lambda.Person;

public class StreamsExample {

	public static void main(String[] args) {
		List<Person> people = Arrays.asList(
				new Person("Charles", "Dickens", 60),
				new Person("Lewis", "Carroll", 42),
				new Person("Thomas", "Carlyle", 51),
				new Person("Charlotte", "Bronte", 45),
				new Person("Mathew", "Arnold", 39));
		
		people.stream()
		.filter(p -> p.getLastName().startsWith("C"))		
		.forEach(p -> System.out.println(p));
		
		long count = people.stream()
		.filter(p -> p.getLastName().startsWith("D"))
		.count();
		
		System.out.println("Count : " + count);
		
		count = people.parallelStream()
		.filter(p -> p.getLastName().startsWith("D"))
		.count();
				
		System.out.println("Count : " + count);
		
	}

}
