package classes;

public abstract class Invoice {
    final int period;
    final Worker worker;

    public Invoice(int period, Worker w) {
        this.period = period;
        this.worker = w;
    }

    public int getPeriod() {
        return this.period;
    }

    public Worker getWorkers() {
        return this.worker;
    }

    public abstract double getMerit();

    public String toString() {
        String Temp;
        Temp = "Name: ";

        return Temp;
    }
}
