package classes;

public class Payroll1 extends Invoice{
    final double salary;

    public Payroll1(int Period, Worker Worker, double Salary) {
        super(Period, Worker);
        this.salary = Salary;
    }

    public double getMerit() {
        return this.salary;
    }
}
