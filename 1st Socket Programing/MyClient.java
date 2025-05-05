import java.net.*;
import java.io.*;
public class MyClient {
 public static void main(String[] args) throws Exception {
 Socket socket = new Socket("127.0.0.1", 5555);
 System.out.println("Connected to server.");
 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
 BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
 while (true) {
 // Send to server
 System.out.print("Client: ");
 String clientMessage = keyboardReader.readLine();
 writer.println(clientMessage);
 if (clientMessage.equalsIgnoreCase("bye")) {
 System.out.println("Client exiting...");
 break;
 }
 // Read from server
 String serverMessage = reader.readLine();
 if (serverMessage == null || serverMessage.equalsIgnoreCase("bye")) {
 System.out.println("Server disconnected.");
 break;
 }
 System.out.println("Server: " + serverMessage);
 }
 socket.close();
 }
}
