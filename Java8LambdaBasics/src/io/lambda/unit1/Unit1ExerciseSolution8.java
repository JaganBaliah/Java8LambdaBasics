package io.lambda.unit1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import io.lambda.Person;

public class Unit1ExerciseSolution8 {

	public static void main(String[] args) {
		List<Person> people = Arrays.asList(
				new Person("Charles", "Dickens", 60),
				new Person("Lewis", "Carroll", 42),
				new Person("Thomas", "Carlyle", 51),
				new Person("Charlotte", "Bronte", 45),
				new Person("Mathew", "Arnold", 39));
		
		//Sort list by the name
		Collections.sort(people, (p0, p1) -> p0.getFirstName().compareTo(p1.getFirstName()));
		
		//Create a method that prints all elements in the list
		System.out.println("Sorted based on first name and printing them....");
		performConditionally(people, p -> true, p -> System.out.println(p));
		System.out.println();
		
		//Create a method that prints all people that has first name starting with "C"
		System.out.println("Identifying those person who first name starts with C and Printing them....");
		performConditionally(people, p -> p.getFirstName().startsWith("C"), p -> System.out.println(p.getLastName() + ", " + p.getFirstName()));
		System.out.println();
		
		//Create a method that prints all people that has last name starting with "C"
		System.out.println("Identifying those person who last name starts with C and Printing them....");
		performConditionally(people, p -> p.getLastName().startsWith("C"), p -> System.out.println(p.getLastName() + ", " + p.getFirstName()));
		System.out.println();
		performConditionally(people, p -> p.getLastName().startsWith("C"), System.out::println);
		System.out.println();
		performConditionally(people, p -> p.getLastName().startsWith("C"), Unit1ExerciseSolution8::print);
	}

	public static void print(Person p) {
		System.out.println(p.getFirstName() + " " + p.getLastName());		
	}
	
	private static void performConditionally(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer) {
		for(Person p : people) {
			if(predicate.test(p)) {
				consumer.accept(p);				
			}
		}		
	}
}