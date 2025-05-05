import socket
from datetime import datetime
import random

# Convert timestamp to HH:MM:SS format
def seconds_to_time(ts):
    return datetime.fromtimestamp(ts).strftime('%H:%M:%S')

# Simulate the client behavior
def start_client():
    client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client.connect(('localhost', 9999))  # Connect to the server on localhost and port 9999
    
    # Generate a random time offset between -150 and 150 seconds
    time_offset = random.randint(-150, 150)
    
    # Simulate the current time with the random offset
    simulated_time = datetime.now().timestamp() + time_offset
    print(f"\nClient simulated time (offset {time_offset} sec): {seconds_to_time(simulated_time)}")
    
    # Send the simulated time to the server
    client.send(str(simulated_time).encode())
    
    # Receive data from the server
    try:
        data = client.recv(1024).decode()
        if "|" not in data:
            raise ValueError(f"Invalid format received: {data}")
        
        adjustment_str, server_time_str = data.split("|")
        adjustment = float(adjustment_str)
        server_time = float(server_time_str)
        
        # Adjust the client's time based on the received adjustment
        adjusted_time = simulated_time + adjustment
        
        print(f"Server original time: {seconds_to_time(server_time)}")
        print(f"Received adjustment: {adjustment:.2f} seconds")
        print(f"Adjusted client time: {seconds_to_time(adjusted_time)}\n")
    
    except Exception as e:
        print(f"Error in receiving or parsing server data: {e}")
    
    client.close()

if __name__ == "__main__":
    start_client()
