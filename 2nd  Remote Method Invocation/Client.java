import java.rmi.Naming;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter the Server address:");
            String server = scanner.nextLine();

            ServerInterface si = (ServerInterface) Naming.lookup("rmi://" + server + "/Server");

            System.out.println("Enter first string:");
            String first = scanner.nextLine();

            System.out.println("Enter second string:");
            String second = scanner.nextLine();

            System.out.println("Concatenated String: " + si.concat(first, second));

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
