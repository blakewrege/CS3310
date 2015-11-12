import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by cpg on 11/11/15.
 */
public class CustomerPQ {
    private int startValue = 100;

    private Heap heap;

    public CustomerPQ() {
        heap = new Heap(500);
    }

    public String createCustPQ() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("LineAt6Am.txt"));
        String logResult = "";

        String line = null;
        while ((line = reader.readLine()) != null) {
            logResult = logResult.concat(String.format("ADDED:  %s%n", addCustToPQ(line)));
        }

        reader.close();
        return logResult;
    }

    public Customer addCustToPQ(String line) {
        startValue++;
        String[] tokens = line.split(",");

        String name = tokens[0];
        int employeeStatus = 0;
        if (tokens[1].equals("employee")) {
            employeeStatus = 10;
        } else if (tokens[1].equals("owner")) {
            employeeStatus = 50;
        }

        int vipStatus = 0;
        if (tokens[2].equals("vip")) {
            vipStatus = 5;
        } else if (tokens[2].equals("superVip")) {
            vipStatus = 8;
        }

        int loyaltyCard = 0;
        if (tokens[3].equals("loyalty")) {
            loyaltyCard = 4;
        }

        int haveChild = 0;
        if (tokens[4].equals("child")) {
            haveChild = 2;
        }

        int age = 0;
        try {
            age = Integer.parseInt(tokens[5]);
        } catch (NumberFormatException e) {

        }

        int priority = startValue
                - employeeStatus
                - vipStatus
                - loyaltyCard
                - haveChild;

        if (age >= 65) {
            priority -= 5;
        }

        if (age >= 80) {
            priority -= 5;
        }

        Customer customer = new Customer(name, priority);
        heap.insert(customer);
        return customer;
    }

    public Customer serveNextCustInPQ() {
        return heap.deleteMin();
    }

    public String serveRestOfCustInPQ() {
        String logResult = "";
        while (!heap.isEmpty()) {
            logResult = logResult.concat(String.format("SERVED:  %s%n", serveNextCustInPQ()));
        }
        return logResult;
    }

    public Heap getHeap() {
        return heap;
    }

    public String dumpNodes() {
        String result = String.format(">> SUB  PV   NAME%n", null);

        for (int i = 1; i < heap.getNodeCount(); i++) {
            Customer customer = heap.getNodeAt(i);
            result = result.concat(String.format(">> %d   %d  %s%n", i, customer.getPriority(), customer.getName()));
        }

        return result;
    }
}
