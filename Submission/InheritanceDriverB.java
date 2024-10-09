import java.text.NumberFormat;
import java.util.*;

class EmployeeB {
    public String name;
    public int dept;
    public double salary;

    public EmployeeB(String name, int dept, double salary){
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

class PartTimeEmployeeB extends EmployeeB {
    public PartTimeEmployeeB(String name, int dept, double salary){
        super(name, dept, salary);
    }
    @Override
    public String toString(){
        return "PartTime- " + super.toString();
    }
}

class FullTimeEmployeeB extends EmployeeB {
    private int numOfOptions;
    public FullTimeEmployeeB(String name, int dept, double salary){
        super(name, dept, salary);
        this.numOfOptions = 0;
    }
    public void increaseOptions(int number){
        numOfOptions += number;
    }
    @Override
    public double computeBonus(){
        return 0.03 * salary + 100 * numOfOptions;
    }
    @Override
    public String toString(){
        return "FullTime - " + super.toString() + " Options: " + numOfOptions;
    }
}

public class InheritanceDriverB {
    public static void main(String[] args) {
        Random random = new Random();
        EmployeeB[] list = new EmployeeB[5];
        NumberFormat nf = NumberFormat.getCurrencyInstance();

        list[0] = new PartTimeEmployeeB("PTE1", random.nextInt(3), random.nextDouble() * 50000);
        list[1] = new FullTimeEmployeeB("FTE2", random.nextInt(3), random.nextDouble() * 100000);
        list[2] = new FullTimeEmployeeB("FTE3", random.nextInt(3), random.nextDouble() * 100000);
        list[3] = new PartTimeEmployeeB("PTE4", random.nextInt(3), random.nextDouble() * 50000);
        list[4] = new FullTimeEmployeeB("FTE5", random.nextInt(3), random.nextDouble() * 100000);

        System.out.println("THE EMPLOYEES");
        for (EmployeeB emp : list) {
            System.out.println(emp);
        }

        System.out.println("THE EMPLOYEES WITH INCREASED OPTIONS");
        for (EmployeeB emp : list) {
            if (emp instanceof FullTimeEmployeeB) {
                FullTimeEmployeeB fte = (FullTimeEmployeeB) emp;
                fte.increaseOptions(random.nextInt(100));
                System.out.println(fte);
            }
        }

        System.out.println("THE EMPLOYEES WITH BONUSES");
        for (EmployeeB emp : list) {
            System.out.println(emp + " bonus: " + nf.format(emp.computeBonus()));
        }

        System.out.println("THE EMPLOYEES AFTER RAISES");
        for (EmployeeB emp : list) {
            emp.raise(random.nextDouble(9000.0) + 1000.0);
            System.out.println(emp);
        }

        System.out.println("FINAL STATE OF THE EMPLOYEES");
        for (EmployeeB emp : list) {
            System.out.println(emp);
        }
    }
}