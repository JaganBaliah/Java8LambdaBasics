package io.lambda;

public class ThisReferenceExample {

	public void doProcess(int i, Process p) {
		p.process(i);
	}
	
	public void execute() {
		doProcess(10, i -> {
			System.out.println("Value of i is " + i);
			System.out.println(this);
		});
	}
	
	public String toString() {
		return "This is main ThisReferenceExample class";
	}
	
	public static void main(String[] args) {
		ThisReferenceExample thisReferenceExample = new ThisReferenceExample();
		thisReferenceExample.doProcess(10, new Process() {
			@Override
			public void process(int i) {
				System.out.println("Value of i is " + i);
				System.out.println(this);
			}
			
			public String toString() {
				return "This is an anonymous inner class";
			}
		});
		
		thisReferenceExample.doProcess(10, i -> {
			System.out.println("Value of i is " + i);
			//System.out.println(this);
		});
		
		thisReferenceExample.execute();
	}

	
}
