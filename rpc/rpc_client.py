#!/usr/bin/env python3
"""
RPC Client Implementation
Connects to remote RPC server on AWS EC2
"""

import xmlrpc.client
import sys

def main():
    # Replace with your EC2 public IP
    SERVER_IP = input("Enter EC2 Public IP: ").strip()
    SERVER_URL = f"http://{SERVER_IP}:8080"
    
    try:
        print(f"\nConnecting to RPC Server at {SERVER_URL}...")
        proxy = xmlrpc.client.ServerProxy(SERVER_URL)
        
        
        print("RPC CLIENT - REMOTE PROCEDURE CALL DEMONSTRATION")
        
        
        # Test 1: Addition
        result =proxy.add(10, 5)
        print(f"\n1. Addition: add(10, 5) = {result}")
        
        # Test 2: Subtraction
        result =proxy.subtract(10, 5)
        print(f"2. Subtraction: subtract(10, 5) = {result}")
        
        # Test 3: Multiplication
        result =proxy.multiply(10, 5)
        print(f"3. Multiplication: multiply(10, 5) = {result}")
        
        # Test 4: Division
        result =proxy.divide(10, 5)
        print(f"4. Division: divide(10, 5) = {result}")
        
        # Test 5: Power
        result =proxy.power(2, 8)
        print(f"5. Power: power(2, 8) = {result}")
        
        # Test 6: Error handling - Division by zero
        print(f"\n6. Error Handling Test: divide(10, 0)")
        result =proxy.divide(10, 0)
        print(f"   Result: {result}")
        
        # Test 7: Get server time
        server_time = proxy.get_server_time()
        print(f"\n7. Server Time: {server_time}")
        
        # Test 8: Get server info
        server_info = proxy.get_server_info()
        print(f"8. Server Info: {server_info}")
        
        print("RPC CLIENT - ALL TESTS COMPLETED SUCCESSFULLY")
        
        
    except ConnectionRefusedError:
        print(f"\n!!Error: Could not connect to server at {SERVER_URL}")
        print("   Make sure:")
        print("   1. EC2 instance is running")
        print("   2. RPC server is started on EC2")
        print("   3. Security group allows port 8080")
        sys.exit(1)
    except Exception as e:
        print(f"\n!!Error: {e}")
        sys.exit(1)

if __name__ == "__main__":
    main()
