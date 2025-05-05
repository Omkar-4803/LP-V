import java.rmi.*;

public interface ServerIntf extends Remote {
    public double Addition(double num1, double num2) throws RemoteException;
    public double Subtraction(double num1, double num2) throws RemoteException;
    public double Multiplication(double num1, double num2) throws RemoteException;
    public double Division(double num1, double num2) throws RemoteException;
    public void sendNumber(double num1, double num2) throws RemoteException;
}
