#!/bin/bash

# Compilation script for RMI Calculator Application
# Compiles all Java source files

echo "======================================"
echo "Compiling RMI Calculator Application"
echo "======================================"

# Create bin directory if it doesn't exist
mkdir -p bin

# Compile all Java files
echo "Compiling Java source files..."
javac -d . *.java

if [ $? -eq 0 ]; then
    echo "✓ Compilation successful!"
    echo "======================================"
    echo "You can now run:"
    echo "  Server: ./run_server.sh"
    echo "  Client: ./run_client.sh"
    echo "======================================"
else
    echo "❌ Compilation failed!"
    echo "Please check the errors above."
    exit 1
fi
