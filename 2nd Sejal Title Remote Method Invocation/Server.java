import java.rmi.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerImpl obj = new ServerImpl();
            Naming.rebind("CalculatorService", obj);
            System.out.println("Calculator RMI Server is ready.");
        } catch (Exception e) {
            System.out.println("Server Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
