#!/bin/bash

# Start RMI Server
# This script runs the RMI server for the calculator service

echo ""
echo "Starting RMI Calculator Server"
echo ""

# Check if files are compiled
if [ ! -f "RMIServer.class" ]; then
    echo "Class files not found. Compiling first..."
    ./compile.sh
fi

echo ""
echo "Starting server..."
echo "Press Ctrl+C to stop the server"
echo ""

# Run the server
java RMIServer
