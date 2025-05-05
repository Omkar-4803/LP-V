import java.rmi.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            ServerIntf calc = (ServerIntf) Naming.lookup("rmi://localhost/CalculatorService");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter first number: ");
            double num1 = scanner.nextDouble();
            System.out.print("Enter second number: ");
            double num2 = scanner.nextDouble();

            System.out.println("Addition: " + calc.Addition(num1, num2));
            System.out.println("Subtraction: " + calc.Subtraction(num1, num2));
            System.out.println("Multiplication: " + calc.Multiplication(num1, num2));
            System.out.println("Division: " + calc.Division(num1, num2));
        } catch (Exception e) {
            System.out.println("Client Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
