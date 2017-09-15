package io.lambda.unit1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.lambda.Person;

public class Unit1ExerciseSolution7 {

	public static void main(String[] args) {
		List<Person> people = Arrays.asList(
				new Person("Charles", "Dickens", 60),
				new Person("Lewis", "Carroll", 42),
				new Person("Thomas", "Carlyle", 51),
				new Person("Charlotte", "Bronte", 45),
				new Person("Mathew", "Arnold", 39)
				);

		
		//Sort list by the name
		Collections.sort(people, new Comparator<Person>(){
			@Override
			public int compare(Person person0, Person person1) {
				return person0.getFirstName().compareTo(person1.getFirstName());
			}			
		});
		
		
		//Create a method that prints all elements in the list
		System.out.println("Sorted based on first name and printing them....");
		printAllPeople(people);
		System.out.println();
		
		//Create a method that prints all people that has first name starting with "C"
		System.out.println("Identifying those person who first name starts with C and Printing them....");
		printStartingWithC(people, new Condition<Person>(){
			@Override
			public boolean satisfied(Person p) {
				return p.getFirstName().startsWith("C");
			}			
		});
		System.out.println();
		
		//Create a method that prints all people that has last name starting with "C"
		System.out.println("Identifying those person who last name starts with C and Printing them....");
		printStartingWithC(people, new Condition<Person>(){
			@Override
			public boolean satisfied(Person p) {
				return p.getLastName().startsWith("C");
			}			
		});
	}

	private static void printStartingWithC(List<Person> people, Condition<Person> condition) {
		for(Person p : people) {
			if(condition.satisfied(p)) {
				System.out.println(p);
			}
		}
		
	}

	private static void printAllPeople(List<Person> people) {
		for(Person person : people) {
			System.out.println(person);
		}
		
	}

}

interface Condition<T> {
	public boolean satisfied(T p);
}
