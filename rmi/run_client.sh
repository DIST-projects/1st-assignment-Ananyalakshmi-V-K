#!/bin/bash

# Start RMI Client
# This script runs the interactive RMI client

echo ""
echo "Starting RMI Calculator Client"
echo ""

# Check if files are compiled
if [ ! -f "RMIClient.class" ]; then
    echo "Class files not found. Compiling first..."
    ./compile.sh
    echo ""
fi

# Run the client
java RMIClient
