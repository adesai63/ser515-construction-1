import java.text.NumberFormat;
import java.util.*;

class Employee{
    protected String name;
    protected int dept;
    protected double salary;

    public Employee(String name, int dept, double salary){
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }
    public double computeBonus(){
        return 0.01*salary;
    }
    public void raise(double amount){
        salary += amount;
    }
    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        return "Name: " + name + " Dept: " + dept + " Wage: " + nf.format(salary);
    }
}

class PartTimeEmployee extends Employee{
    public PartTimeEmployee(String name, int dept, double salary){
        super(name,dept,salary);
    }
    @Override
    public String toString(){
        return "PartTime- "+ super.toString();
    }
}

class FullTimeEmployee extends Employee{
    private int numOfOptions;
    public FullTimeEmployee(String name, int dept, double salary){
        super(name,dept,salary);
        this.numOfOptions = 0;
    }
    public void increaseOptions(int number){
        numOfOptions += number;
    }
    @Override
    public double computeBonus(){
        return 0.03 * salary +100 * numOfOptions;
    }
    @Override
    public String toString(){
        return "FullTime - " + super.toString() + " Options: " + numOfOptions;
    }
}

public class InheritanceDriverB{
    public static void main(String[] args) {
        Random random = new Random();
        Employee[] list = new Employee[5];
        NumberFormat nf= NumberFormat.getCurrencyInstance();

        list[0] = new PartTimeEmployee("PTE1", random.nextInt(3), random.nextDouble() * 50000);
        list[1] = new FullTimeEmployee("FTE2", random.nextInt(3), random.nextDouble() * 100000);
        list[2] = new FullTimeEmployee("FTE3", random.nextInt(3), random.nextDouble() * 100000);
        list[3] = new PartTimeEmployee("PTE4", random.nextInt(3), random.nextDouble() * 50000);
        list[4] = new FullTimeEmployee("FTE5", random.nextInt(3), random.nextDouble() * 100000);

        System.out.println("THE EMPLOYEES");
        for(Employee emp: list){
            System.out.println(emp);
        }

        System.out.println("THE EMPLOTEES WITH INCREASED OPTIONS");
        for(Employee emp: list){
            if(emp instanceof FullTimeEmployee){
                FullTimeEmployee fte = (FullTimeEmployee) emp;
                fte.increaseOptions(random.nextInt(100));
                System.out.println(fte);
            }
        }
        System.out.println("THE EMPLOYEES WITH BONUSES");
        for(Employee emp: list){
            System.out.println(emp + " bonus: " + nf.format(emp.computeBonus()));
        }
        System.out.println("THE EMPLOYEES AFTER RAISES");
        for (Employee emp : list) {
            emp.raise(random.nextDouble(9000.0) + 1000.0);
            System.out.println(emp);
        }
        System.out.println("FINAL STATE OF THE EMPLOYEES");
        for (Employee emp : list) {
            System.out.println(emp);
        }
    }
}