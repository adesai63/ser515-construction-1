import java.text.NumberFormat;
import java.util.*;

class PartTimeEmployee {
    public PartTimeEmployee (String name, int dept, double salary) {
	this.name = name;
	this.dept = dept;
	this.salary = salary;
    }
    public double computeBonus() {
	return 0.01 * salary;
    }
    public void raise(double amount) {
	salary = salary + amount;
    }
    public String toString() {
	java.text.NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
	return "PartTime - Name: "+name+" Dept: "+dept+" Wage: "+nf.format(salary);
    }
    private int dept;
    private double salary;
    private String name;
}

class FullTimeEmployee {
    public FullTimeEmployee (String name, int dept, double salary) {
	this.name = name;
	this.dept = dept;
	this.salary = salary;
    }
    public double computeBonus() {
	return 0.03 * salary + 100 * numOfOptions;
    }
    public String toString() {
	NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
	return "FullTime - Name: "+name+" Dept: "+dept+" Wage: "+nf.format(salary)+" Options: "+numOfOptions;
    }
    public void increaseOptions(int number) {
	numOfOptions += number;
    }
    private int numOfOptions = 0;
    private int dept;
    private double salary;
    private String name;
}

public class InheritanceDriverA {
    public enum Departments { HR, ENGINEERING, SALES, ACCOUNTING }
    
    public static void main(String args[]) {
	Random random = new Random();
	Object list[] = new Object[5];
	NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
	
	list[0] = new PartTimeEmployee("PTE1", random.nextInt(3), random.nextDouble()*50000);
	list[1] = new FullTimeEmployee("FTE2", random.nextInt(3), random.nextDouble()*100000);
	list[2] = new FullTimeEmployee("FTE3", random.nextInt(3), random.nextDouble()*100000);
	list[3] = new PartTimeEmployee("PTE4", random.nextInt(3), random.nextDouble()*50000);
	list[4] = new FullTimeEmployee("FTE5", random.nextInt(3), random.nextDouble()*100000);

	System.out.println("THE EMPLOYEES");
	for (int i=0; i < list.length; i++)
	    System.out.println(list[i]);

	System.out.println("THE EMPLOYEES WITH INCREASED OPTIONS");
	for (int i=0; i < list.length; i++) {
	    // This loop should increase the options of employees that have options, and print only those employees out.
	    if(list[i] instanceof FullTimeEmployee){
			FullTimeEmployee FTEmployee = (FullTimeEmployee) list[i];
			FTEmployee.increaseOptions(random.nextInt(100));
			System.out.println(FTEmployee);
		}
	    
	}
	System.out.println("THE EMPLOYEES WITH BONUSES");
	for (int i=0; i < list.length; i++) {
		if(list[i] instanceof PartTimeEmployee){
			PartTimeEmployee PartTE = (PartTimeEmployee) list[i];
			System.out.println(PartTE + " bonus: " + nf.format(PartTE.computeBonus()));
		}
		else if(list[i] instanceof FullTimeEmployee){
			FullTimeEmployee FTEmployee = (FullTimeEmployee) list[i];
			System.out.println(FTEmployee + " bonus: " + nf.format(FTEmployee.computeBonus()));
		}
	    // This loop should apply bonuses to employees that can get bonuses, and print only those employees out.
	    
	}
	System.out.println("THE EMPLOYEES AFTER RAISES");
	for (int i=0; i < list.length; i++) {
		if(list[i] instanceof PartTimeEmployee){
			PartTimeEmployee PartTE = (PartTimeEmployee) list[i];
			PartTE.raise(random.nextDouble(9000.0) + 1000.0);
			System.out.println(PartTE);
		}
	    // This loop should give raises to employees that can get raises, and print only those employees out.
	}
	System.out.println("FINAL STATE OF THE EMPLOYEES");
	for (int i=0; i < list.length; i++) {
	    System.out.println(list[i]);
	}
    }
}
