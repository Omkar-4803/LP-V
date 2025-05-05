import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf {
    public ServerImpl() throws RemoteException {
        super();
    }

    public void sendNumber(double num1, double num2) throws RemoteException {
        System.out.println("Received num1 = " + num1);
        System.out.println("Received num2 = " + num2);
    }

    public double Addition(double num1, double num2) throws RemoteException {
        double result = num1 + num2;
        System.out.println("Addition is: " + result);
        return result;
    }

    public double Subtraction(double num1, double num2) throws RemoteException {
        double result = num1 - num2;
        System.out.println("Subtraction is: " + result);
        return result;
    }

    public double Multiplication(double num1, double num2) throws RemoteException {
        double result = num1 * num2;
        System.out.println("Multiplication is: " + result);
        return result;
    }

    public double Division(double num1, double num2) throws RemoteException {
        if (num2 == 0) {
            System.out.println("Cannot divide a number by zero!");
            return Double.NaN;
        }
        double result = num1 / num2;
        System.out.println("Division is: " + result);
        return result;
    }
}
