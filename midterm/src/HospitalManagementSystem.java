
import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;

public class HospitalManagementSystem {

    public static void main(String[] args) {

        Queue<String> arrivalQ = new LinkedList<>();

        PriorityQueue<String> priorityQ = new PriorityQueue<>();

        arrivalQ.add("Anna (Severity: 3)");
        arrivalQ.add("Bob (Severity: 1)");
        arrivalQ.add("Carla (Severity: 2)");
        arrivalQ.add("Dave (Severity: 1)");

        priorityQ.add("1:Bob");
        priorityQ.add("3:Anna");
        priorityQ.add("2:Carla");
        priorityQ.add("1:Dave");

        System.out.println("=== Arrival Order ===");
        System.out.println("Arrival Order: " + arrivalQ);

        System.out.println("\n=== Treatment Order ===");

        while (!priorityQ.isEmpty()) {
            String patient = priorityQ.poll();
            String[] parts = patient.split(":");
            String severity = parts[0];
            String name = parts[1];
            System.out.println("Treating: " + name + " (Severity: " + severity + ")");
        }
    }
}
