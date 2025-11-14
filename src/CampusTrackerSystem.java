import javax.swing.*;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;

public class CampusTrackerSystem {

    static class Resource {
        String id, name;
        boolean available = true;

        Resource(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String toString() {
            return id + " - " + name + " (" + (available ? "Available" : "In Use") + ")";
        }
    }

    public static void main(String[] args) {
        ArrayList<Resource> resources = new ArrayList<>();
        Queue<String> waitingList = new LinkedList<>();
        Stack<String> returnHistory = new Stack<>();
        HashMap<String, Resource> reserved = new HashMap<>();

        resources.add(new Resource("R1", "Projector"));
        resources.add(new Resource("R2", "Laptop"));
        resources.add(new Resource("R3", "Tablet"));
        resources.add(new Resource("R4", "Speakers"));
        resources.add(new Resource("R5", "Extension Cord"));

        while (true) {
            String choice = JOptionPane.showInputDialog("""
                    📚 Campus Resource Tracker

                    1. Add Resource
                    2. Show Resources
                    3. Reserve Resource
                    4. Return Resource
                    5. Show Waiting List
                    6. Show Return History
                    7. Exit

                    Enter your choice (1-7):
                    """);

            if (choice == null) break;
            String msg = "";

            if (choice.equals("1")) {
                String id = JOptionPane.showInputDialog("Enter Resource ID:");
                String name = JOptionPane.showInputDialog("Enter Resource Name:");
                resources.add(new Resource(id, name));
                msg = "✅ Added: " + name;

            } else if (choice.equals("2")) { 
                if (resources.isEmpty()) msg = "No resources available.";
                else {
                    String msgList = "--- Resources ---\n";
                    for (Resource r : resources) {
                        msgList += r.toString() + "\n";
                    }
                    msg = msgList;
}
            } else if (choice.equals("3")) {
                String user = JOptionPane.showInputDialog("Enter your name:");
                String id = JOptionPane.showInputDialog("Enter Resource ID to reserve:");
                boolean found = false;

                for (Resource r : resources) {
                    if (r.id.equals(id)) {
                        found = true;
                        if (r.available) {
                            r.available = false;
                            reserved.put(user, r);
                            msg = user + " successfully reserved " + r.name;
                        } else {
                            waitingList.add(user);
                            msg = r.name + " is already in use.\n" + user + " added to waiting list.";
                        }
                        break;
                    }
                }
                if (!found) msg = "❌ Resource not found.";

            } else if (choice.equals("4")) {
                String user = JOptionPane.showInputDialog("Enter your name to return a resource:");
                if (!reserved.containsKey(user)) msg = "No reservation found for " + user;
                else {
                    Resource r = reserved.remove(user);
                    returnHistory.push(r.name);
                    msg = user + " returned " + r.name;
                    if (!waitingList.isEmpty()) {
                        String next = waitingList.poll();
                        reserved.put(next, r);
                        msg += "\nNext in line: " + next + " now has " + r.name;
                    } else {
                        r.available = true;
                    }
                }

            } else if (choice.equals("5")) {
                msg = waitingList.isEmpty() ? "No one is waiting." : "--- Waiting List ---\n" + String.join("\n", waitingList);

            } else if (choice.equals("6")) {
                if (returnHistory.isEmpty()) msg = "No returns yet.";
                else {
                    StringBuilder sb = new StringBuilder("--- Recently Returned ---\n");
                    for (String item : returnHistory) sb.append(item).append("\n");
                    msg = sb.toString();
                }

            } else if (choice.equals("7")) {
                JOptionPane.showMessageDialog(null, "👋 Thank you for using the system!");
                break;

            } else {
                msg = "❌ Invalid choice! Please enter 1–7.";
            }

            JOptionPane.showMessageDialog(null, msg);
        }
    }
}
