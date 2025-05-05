import java.util.*;

public class Ring {
    int max_processes;
    int coordinator;
    boolean[] processes;
    ArrayList<Integer> pid;

    public Ring(int max) {
        max_processes = max;
        processes = new boolean[max_processes];
        pid = new ArrayList<>();

        for (int i = 0; i < max_processes; i++) {
            processes[i] = true;
            System.out.println("P" + (i + 1) + " created.");
        }

        coordinator = max_processes; // Initially highest process is the coordinator
        System.out.println("P" + coordinator + " is the coordinator");
    }

    void displayProcesses() {
        for (int i = 0; i < max_processes; i++) {
            if (processes[i])
                System.out.println("P" + (i + 1) + " is up.");
            else
                System.out.println("P" + (i + 1) + " is down.");
        }
        System.out.println("P" + coordinator + " is the coordinator");
    }

    void upProcess(int process_id) {
        if (process_id < 1 || process_id > max_processes) {
            System.out.println("Invalid process ID.");
            return;
        }

        if (!processes[process_id - 1]) {
            processes[process_id - 1] = true;
            System.out.println("Process P" + process_id + " is up.");
        } else {
            System.out.println("Process P" + process_id + " is already up.");
        }
    }

    void downProcess(int process_id) {
        if (process_id < 1 || process_id > max_processes) {
            System.out.println("Invalid process ID.");
            return;
        }

        if (!processes[process_id - 1]) {
            System.out.println("Process P" + process_id + " is already down.");
        } else {
            processes[process_id - 1] = false;
            System.out.println("Process P" + process_id + " is down.");
        }
    }

    void displayArrayList(ArrayList<Integer> pidList) {
        System.out.print("[ ");
        for (Integer x : pidList) {
            System.out.print("P" + x + " ");
        }
        System.out.println("]");
    }

    void initElection(int process_id) {
        if (process_id < 1 || process_id > max_processes) {
            System.out.println("Invalid process ID.");
            return;
        }

        if (!processes[process_id - 1]) {
            System.out.println("Process P" + process_id + " is down. Cannot initiate election.");
            return;
        }

        pid.clear();
        int initiator = process_id - 1;
        int current = (initiator + 1) % max_processes;

        pid.add(initiator + 1); // Add self (1-based ID)
        System.out.println("Election initiated by Process P" + (initiator + 1));

        while (current != initiator) {
            if (processes[current]) {
                pid.add(current + 1);
                System.out.print("Process P" + (current + 1) + " received message. Current list: ");
                displayArrayList(pid);
            }
            current = (current + 1) % max_processes;
        }

        coordinator = Collections.max(pid);
        System.out.println("Process P" + process_id + " has declared P" + coordinator + " as the coordinator");
        pid.clear();
    }

    public static void main(String[] args) {
        Ring ring = null;
        int max_processes = 0, process_id = 0;
        int choice;

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nRing Algorithm");
            System.out.println("1. Create processes");
            System.out.println("2. Display processes");
            System.out.println("3. Up a process");
            System.out.println("4. Down a process");
            System.out.println("5. Run election algorithm");
            System.out.println("6. Exit Program");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the total number of processes: ");
                    max_processes = sc.nextInt();
                    ring = new Ring(max_processes);
                    break;

                case 2:
                    if (ring != null)
                        ring.displayProcesses();
                    else
                        System.out.println("Create processes first.");
                    break;

                case 3:
                    if (ring != null) {
                        System.out.print("Enter the process to up: ");
                        process_id = sc.nextInt();
                        ring.upProcess(process_id);
                    } else {
                        System.out.println("Create processes first.");
                    }
                    break;

                case 4:
                    if (ring != null) {
                        System.out.print("Enter the process to down: ");
                        process_id = sc.nextInt();
                        ring.downProcess(process_id);
                    } else {
                        System.out.println("Create processes first.");
                    }
                    break;

                case 5:
                    if (ring != null) {
                        System.out.print("Enter the process which will initiate election: ");
                        process_id = sc.nextInt();
                        ring.initElection(process_id);
                    } else {
                        System.out.println("Create processes first.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
