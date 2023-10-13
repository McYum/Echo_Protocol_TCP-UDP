package classes;

public class Payroll2 extends Invoice{
    final double hourlywage, amountOfHours;

    public Payroll2(int Period, Worker Worker, double HourlyWage, double AmountOfHours) {
        super(Period, Worker);
        this.hourlywage = HourlyWage;
        this.amountOfHours = AmountOfHours;
    }

    public double getMerit() {
        return this.hourlywage * this.amountOfHours;
    }
}
