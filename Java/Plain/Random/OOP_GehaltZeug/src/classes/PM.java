package classes;
import java.util.ArrayList;
import java.util.Collections;

public class PM {
    final ArrayList<Worker> Workers = new ArrayList<>();
    final ArrayList<Invoice> Invoices = new ArrayList<>();
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String WHITE = "\u001B[0m";

    public void addWorker(Worker ToAdd) {
        Workers.add(ToAdd);
        System.out.println(GREEN + "[Added] " + ToAdd);
        System.out.println(WHITE);
    }

    public void addInvoice(Invoice ToAdd) {
        Invoices.add(ToAdd);
    }

    public void removeWorker(Worker ToRemove) {
        Workers.remove(ToRemove);
        System.out.println(RED + "[Removed] " + ToRemove);
        System.out.println(WHITE);
    }

    public void removeInvoice(Invoice ToRemove) {
        Invoices.remove(ToRemove);
    }

    public void listInvoicesByPeriod(int periodToFetch) {
        System.out.println("Invoices for Period " + periodToFetch + ":");
        for (Invoice invoice : Invoices) {
            if (invoice.getPeriod() == periodToFetch) {
                System.out.println("[worker] " + invoice.worker + " [period] " + invoice.period + " [merit] " + invoice.getMerit());
            }
        }
    }

    public void listWorkers() {
        for (Worker worker : Workers) {
            System.out.println(worker);
        }
    }

    public void sortWorkers() {
        Collections.sort(Workers);
        /* -- Bubblesort
        int n = Workers.size();
        boolean swapped;

        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                Worker worker1 = Workers.get(i - 1);
                Worker worker2 = Workers.get(i);

                if (worker1.IsAlphabeticallyHigher(worker2)) {
                    Workers.set(i - 1, worker2);
                    Workers.set(i, worker1);
                    swapped = true;
                }
            }
        } while (swapped);

        Collections.reverse(Workers);
        */
    }
}
