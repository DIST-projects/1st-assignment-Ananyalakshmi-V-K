#!/usr/bin/env python3
"""
RPC Server Implementation using XML-RPC
Hosted on AWS EC2 for distributed computing demonstration
"""

from xmlrpc.server import SimpleXMLRPCServer
from xmlrpc.server import SimpleXMLRPCRequestHandler
import datetime
import socket

# Restrict to a particular path
class RequestHandler(SimpleXMLRPCRequestHandler):
    rpc_paths = ('/RPC2',)

class Calculator:
    """Remote calculator service with basic arithmetic operations"""
    
    def add(self, x, y):
        """Add two numbers"""
        print(f"[{datetime.datetime.now()}] Add operation: {x} + {y}")
        return x+y
    
    def subtract(self, x, y):
        """Subtract two numbers"""
        print(f"[{datetime.datetime.now()}] Subtract operation: {x} - {y}")
        return x-y
    
    def multiply(self, x, y):
        """Multiply two numbers"""
        print(f"[{datetime.datetime.now()}] Multiply operation: {x} * {y}")
        return x*y
    
    def divide(self, x, y):
        """Divide two numbers with error handling"""
        print(f"[{datetime.datetime.now()}] Divide operation: {x} / {y}")
        try:
            if y ==0:
                raise ValueError("Division by zero is not allowed")
            return x/y
        except Exception as e:
            return f"Error: {str(e)}"
    
    def power(self, base, exponent):
        """Calculate power of a number"""
        print(f"[{datetime.datetime.now()}] Power operation: {base} ^ {exponent}")
        return base ** exponent
    
    def get_server_time(self):
        """Get current server time"""
        return str(datetime.datetime.now())
    
    def get_server_info(self):
        """Get server information"""
        hostname = socket.gethostname()
        return f"Server: {hostname} | Time: {datetime.datetime.now()}"

def main():
    """Start the RPC server"""
    HOST = "0.0.0.0"  # Listen on all interfaces
    PORT = 8080
    
    try:
        # Create server
        with SimpleXMLRPCServer((HOST, PORT), 
                                requestHandler=RequestHandler,
                                allow_none=True) as server:
            
            server.register_introspection_functions()
            
            # Register calculator instance
            calc = Calculator()
            server.register_instance(calc)
            
            
            print("RPC Server Started Successfully")
           
            print(f"Listening on: {HOST}:{PORT}")
            print(f"Server Time: {datetime.datetime.now()}")
            print("Available methods: add, subtract, multiply, divide, power")
            print("Press Ctrl+C to stop the server")
            
            
            # Start serving
            server.serve_forever()
            
    except KeyboardInterrupt:
        print("\n\nServer stopped by user")
    except Exception as e:
        print(f"Error starting server: {e}")

if __name__ == "__main__":
    main()
