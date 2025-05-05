import java.net.*;
import java.io.*;
public class MyServer {
 public static void main(String[] args) throws Exception {
 ServerSocket serverSocket = new ServerSocket(5555);
 System.out.println("Server started, waiting for client...");
 Socket socket = serverSocket.accept();
 System.out.println("Client connected: " + socket);
 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
 BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
 while (true) {
 // Read from client
 String clientMessage = reader.readLine();
 if (clientMessage == null || clientMessage.equalsIgnoreCase("bye")) {
 System.out.println("Client disconnected.");
 break;
 }
 System.out.println("Client: " + clientMessage);
 // Send to client
 System.out.print("Server: ");
 String serverMessage = keyboardReader.readLine();
 writer.println(serverMessage);
 if (serverMessage.equalsIgnoreCase("bye")) {
 System.out.println("Server exiting...");
 break;
 }
 }
 socket.close();
 serverSocket.close();
 }
}