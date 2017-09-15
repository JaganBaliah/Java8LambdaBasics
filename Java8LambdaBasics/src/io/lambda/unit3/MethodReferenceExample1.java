package io.lambda.unit3;

public class MethodReferenceExample1 {

	public static void main(String[] args) {
		Thread thread1 = new Thread(() -> printMessage());
		thread1.start();
		
		Thread thread2 = new Thread(MethodReferenceExample1::printMessage);
		thread2.start();
	}
	
	public static void printMessage() {
		System.out.println("Hello");
	}

}
