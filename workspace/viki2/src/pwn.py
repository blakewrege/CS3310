#!/usr/bin/python           # This is client.py file

import socket               # Import socket module

s = socket.socket()         # Create a socket object
host = socket.gethostname() # Get local machine name
port = 31337                # Reserve a port for your service.

s.connect((host, port))
print s.recv(1024)
payload = "A" * 1025
s.send( payload )
print s.recv(2024)
s.close()                     # Close the socket when done
