import socket
import threading
from datetime import datetime

clients = []
client_times = []

# Convert timestamp to HH:MM:SS format
def seconds_to_time(ts):
    return datetime.fromtimestamp(ts).strftime('%H:%M:%S')

# Handle each client connection
def handle_client(conn, addr):
    print(f"Connected by {addr}")
    data = conn.recv(1024).decode()  # Receive client timestamp
    client_time = float(data)
    client_times.append((conn, addr, client_time))  # Store client data

# Berkeley Algorithm for time synchronization
def berkeley_algorithm(server_time, client_data):
    all_times = [server_time] + [c[2] for c in client_data]
    avg_time = sum(all_times) / len(all_times)
    print(f"\nServer Time: {seconds_to_time(server_time)}")
    print("\nClient Times:")
    for i, (conn, addr, time_val) in enumerate(client_data):
        print(f" Client {i+1} [{addr}]: {seconds_to_time(time_val)}")
    
    print(f"\nAverage Time for Sync: {seconds_to_time(avg_time)}")
    
    # Calculate adjustments for each client
    adjustments = [avg_time - t[2] for t in client_data]
    
    # Send time adjustment to each client
    for i, (conn, addr, time_val) in enumerate(client_data):
        adj = adjustments[i]
        message = f"{adj}|{server_time}"
        conn.send(message.encode())
        direction = "ahead" if adj < 0 else "behind"
        print(f"Sent adjustment of {adj:.2f} seconds ({direction}) to {addr}")
        conn.close()

# Start the server
def start_server():
    server_time_now = datetime.now()
    print(f"Server started, time({server_time_now.strftime('%H:%M:%S')})")
    print("Waiting for clients...")
    
    # Create a TCP/IP socket
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.bind(('localhost', 9999))  # Bind server to localhost and port 9999
    server.listen(5)
    
    while len(clients) < 3:  # Wait for 3 clients
        conn, addr = server.accept()
        clients.append(threading.Thread(target=handle_client, args=(conn, addr)))
        clients[-1].start()
    
    # Wait for all client threads to finish
    for thread in clients:
        thread.join()
    
    # Get the server's current time and run Berkeley algorithm
    server_time = datetime.now().timestamp()
    berkeley_algorithm(server_time, client_times)

if __name__ == "__main__":
    start_server()
