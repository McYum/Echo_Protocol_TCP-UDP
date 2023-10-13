import classes.*;

public class Main {
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String WHITE = "\u001B[0m";

    public static void main(String[] args) {
        Worker A = new Worker("Ajay", 0);
        Worker B = new Worker("Bernd", 1);
        Worker C = new Worker("CJ", 1);
        Worker Hubert = new Worker("Pornelius Hubertus", 2);
        Worker Hoff = new Worker("Jack Hoff", 3);
        Worker Z = new Worker("Zain", 1);
        PM personalManagement = new PM();

        personalManagement.addWorker(Hubert);
        personalManagement.addWorker(Hoff);
        personalManagement.addWorker(A);
        personalManagement.addWorker(B);
        personalManagement.addWorker(C);
        personalManagement.addWorker(Z);
        personalManagement.sortWorkers();
        //System.out.println(Hubert.IsAlphabeticallyHigher(Hoff));

        personalManagement.addInvoice(new Payroll2(2, A, 16, 158));
        personalManagement.addInvoice(new Payroll2(2, B, 5, 5108));
        personalManagement.listWorkers();
        personalManagement.listInvoicesByPeriod(2);
    }
}