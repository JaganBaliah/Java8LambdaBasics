Understanding Lambdas
=====================

Why Lambdas? 
	Enables functional programming
	Readable & concise code as it eliminates boiler plate code
	Easier to use APIs and libraries
	Enables support for parallel processing as more processors are multi-core processors
	
Code in OOP - Everything is an Object and all code blocks are associated with classes and objects
Instead of passing objects and calling methods on it, if we had just the action being passed through the method it becomes simple.
Imagine assigning a method to a variable of the calling method, and that is what exactly Lambda expression is.

	aBlockOfCode = public void perform() {
		System.out.println("Hello World!");
	}
	
	aBlockOfCode = () -> {
		System.out.println("Hello World!")
	}

	aBlockOfCode = () -> System.out.println("Hello World!")		//If it is just one line curly braces can be removed

	doubleNumberFunction = public int double(int a) {
		return a * 2;
	}

	doubleNumberFn = (int a) -> return a * 2
	doubleNumberFn = (int a) -> a * 2		//If it is just one line and function is returning something even "return" keyword is not needed 
	
	
Using Lambdas
=============

	1. aBlockOfCode = () -> System.out.println("Hello World!")

	2. doubleNumberFn = a -> a * 2

	3. addFn = (a, b) -> a + b

	4. safeDivideFn = (int a, int b) -> {
			if(b == 0) return 0;
			return a / b; 
	   }

	5. stringLenghtFn = s -> s.length()

	Leveraging interface functionality for function types:
	FunctionType myLambdaFunction = () -> System.out.println("Hello World!");
	FunctionType addFunction = (int a, int b) -> a + b;
	
	interface MyLambdaPrint {
		void print();
	}
	
	interface MyLambdaAdd {
		int add(int a, int b);
	}
	
	MyLambdaPrint myLambdaPrintFunction = () -> System.out.println("Hello World!");
	MyLambdaAdd myLambdaAddFunction = (a, b) -> a + b;
	
	What if we had two functions in MyLambdaPrint with similar signature
	interface MyLambdaPrint {
		void print();
		void output();
	}
	
	The above statement will thrown an error saying "The target type of this expression must be a functional interface"
	
	
	interface Greeting {
		public void print();
	}
	Greeting helloWorldGreeting = new Greeting() {
		public void print() {
			System.out.println("Hello World!");
		} 
	}
	helloWorldGreeting.print();	//Created a new anonymous inner class to print "Hello World!"

	Greeting lambdaGreeting = () -> System.out.println("Hello World!");	
	lambdaGreeting.print(); //Created a inline function to print "Hello World!"
	This is how we execute lambda expressions. By calling the interface method on it, just as if it were an instance of a class
	
	Lambda expressions are kind of shortcut to creating anonymous inner class with a slight difference
	Type inference is done based on the details inside the interface method.
				
Functional Interfaces
=====================

	If lambda type was introduced, it could have been used only in the code that uses lambda type.
	For e.g., if we want to use an existing function in library, then the whole library should have been rewritten to support lambda types
	
	Hence it was decided to go with interface as lambda type, so only the way we call that function changes- earlier it was 
	using anonymous inner class and now using lambda expression. 
	
	For eg., Thread thread = new Thread(new Runnable() {
				public void run() {
					System.out.println("Hello World!");
				}
			}
			thread.run();
			
			Thread myLambdaThread = new Thread(() -> System.out.println("Hello World!"));
			myLambdaThread.run();
			
	This thread was implemented way before Java 8. Did the java8 developers go in and fix it, so that it works with Lambda? No
	They didn't have to. Because the lambda behaves like an instance of what it trying to match an interface.
	
	This way we get huge backward compatibility and all old APIs are still accessible but now with lambdas, the only constraint is the 
	interface that those APIs expect has to be something that has only one abstract method and these interfaces are called Functional Interfaces

	As long as the interface has only one method, then the lambda expressions will continue to work. But the moment, someone
	tries to add another method to that interface we are going to get compilation errors in the place lambda expressions are used.	
	In Java8 we have a new way of marking an interface as Functional interface using the annotation @FunctionalInterface.
	If we had annotated the interface, then the moment someone tries to add another method, we will get compilation error then and there.
	The above annotation is completely optional. Java compiler does not require it for lambda types. But it is good practice to add it.

	Collections.sort(persons, (p1, p2) -> n1.getFirstName().compareTo(n2.getLastName()));	
	printConditionally(persons, p -> p.getFirstName().startsWith("C")); //Prints only those persons whose firstname starts with "C"
	printConditionally(persons, p -> p.getLastName().startsWith("C"));	//Prints only those persons whose lastname starts with "C"
	printConditionally(persons, p -> true);		//Prints all persons names

	printConditionally(List<Person> persons, Condition condition) {
		for(Person p : persons) {		if(condition.test(p) System.out.pritln("Person : " + p);		}
	}
	
	Predicate<T>	test(T t)
	printConditionally(List<Person> persons, Predicate<Person> predicate) {
		for(Person p : persons) {		if(predicate.test(p) System.out.pritln("Person : " + p);		}
	}

	Consumer<T>		void accept(T t)		Represents a function that accepts one input argument and returns no results  
	Supplier<T>		T get()					Represents a supplier of results
	Function<T, R> 	R apply(T t)			Represents a function that accepts one argument and produces a result
	BiConsumer<T,U> void accept(T t, U u)	Represents a function that accepts two arguments and returns no results
	BiFunction<T,U> R apply(T t, U u)		Represents a function that accepts two arguments and produces a result

	Wrapper lambda are those lambda that gets in and returns another lambda, which is a good way to achieve exception handling
	
	int a = 10;
	int b = 20;
	doProcess(a, i -> System.out.println(i + b));
	
	b is effectively final. It takes in the frozen value of 20 and uses it.	  

	In anonymous inner class, the "this" reference points to the object of that inner class.
	But inside the lambda expression, the "this" reference is same as the one outside the lambda expression.
	Basically it wont override the "this" reference and it refers to the instance that it points to outside of lambda.
	
	
Method references
=================

	Alternative ways to writing lambdas. 
	
	class ThisClass {
		public static void printMessage() {System.out.println("Hello")};
	}
	
	Thread thread = new Thread(() -> printMessage());
	Thread thread = new Thread(ThisClass::printMessage);
	Thread thread = new Thread(System.out::println);
	
	As long as the param that is obtained is just passed to the method, we can use method references as shown above for System.out.println() 

Collections improvements
========================

	persons.forEach(p -> System.out.println(p));
	persons.forEach(System.out::println);
			
Streams
=======

A sequence of elements supporting sequential and parellel aggregate operations.

	Stream<Person> stream = persons.stream();
	
	persons
		.stream()
		.forEach(System.out::println);
	
	persons
		.stream()
		.filter(p -> p.getFirstName().startsWith("C"))
		.count();		//terminal operation where the stream ends
		
	persons
		.stream()
		.filter(p -> p.getFirstName().startsWith("C"))
		.forEach(System.out::println);
		
	
	persons.parallelStream(); //For making use of multi-core processors to improve processing speed
	
	
	
	