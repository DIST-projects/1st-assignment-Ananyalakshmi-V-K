/**
 * Implementation of CalculatorInterface
 * This class contains the actual logic for calculator operations
 * It implements the remote interface and handles all calculations
 */
import java.rmi.RemoteException;
import java.net.InetAddress;
import java.util.Date;

public class CalculatorImpl implements CalculatorInterface {

    /**
     * Add two integers
     */
    @Override
    public int add(int a, int b) throws RemoteException {
        try {
            System.out.println("Server: Performing addition: " + a + " + " + b);
            return a + b;
        } catch (Exception e) {
            throw new RemoteException("Error performing addition", e);
        }
    }

    /**
     * Subtract two integers
     */
    @Override
    public int subtract(int a, int b) throws RemoteException {
        try {
            System.out.println("Server: Performing subtraction: " + a + " - " + b);
            return a - b;
        } catch (Exception e) {
            throw new RemoteException("Error performing subtraction", e);
        }
    }

    /**
     * Multiply two integers
     */
    @Override
    public int multiply(int a, int b) throws RemoteException {
        try {
            System.out.println("Server: Performing multiplication: " + a + " * " + b);
            return a * b;
        } catch (Exception e) {
            throw new RemoteException("Error performing multiplication", e);
        }
    }

    /**
     * Divide two integers with error handling for division by zero
     */
    @Override
    public double divide(int a, int b) throws RemoteException {
        try {
            System.out.println("Server: Performing division: " + a + " / " + b);
            if (b == 0) {
                throw new ArithmeticException("Division by zero is not allowed");
            }
            return (double) a / b;
        } catch (ArithmeticException e) {
            throw new RemoteException("Division error: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RemoteException("Error performing division", e);
        }
    }

    /**
     * Calculate power (base raised to exponent)
     */
    @Override
    public double power(int base, int exponent) throws RemoteException {
        try {
            System.out.println("Server: Calculating power: " + base + " ^ " + exponent);
            return Math.pow(base, exponent);
        } catch (Exception e) {
            throw new RemoteException("Error calculating power", e);
        }
    }

    /**
     * Calculate modulus with error handling
     */
    @Override
    public int modulus(int a, int b) throws RemoteException {
        try {
            System.out.println("Server: Performing modulus: " + a + " % " + b);
            if (b == 0) {
                throw new ArithmeticException("Modulus by zero is not allowed");
            }
            return a % b;
        } catch (ArithmeticException e) {
            throw new RemoteException("Modulus error: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RemoteException("Error performing modulus", e);
        }
    }

    /**
     * Get server information including hostname, IP and timestamp
     */
    @Override
    public String getServerInfo() throws RemoteException {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            String hostname = addr.getHostName();
            String ip = addr.getHostAddress();
            String timestamp = new Date().toString();
            
            StringBuilder info = new StringBuilder();
            info.append("Hostname: ").append(hostname).append("\n");
            info.append("IP Address: ").append(ip).append("\n");
            info.append("Timestamp: ").append(timestamp).append("\n");
            info.append("Java Version: ").append(System.getProperty("java.version")).append("\n");
            info.append("OS: ").append(System.getProperty("os.name")).append("\n");
            
            System.out.println("Server: Providing server information");
            return info.toString();
        } catch (Exception e) {
            throw new RemoteException("Error getting server information", e);
        }
    }
}
