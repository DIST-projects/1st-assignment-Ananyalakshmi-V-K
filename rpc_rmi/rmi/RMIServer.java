/**
 * RMI Server Implementation
 * This server hosts the Calculator service and makes it available for remote clients
 * It's designed to work both locally and in cloud environments (AWS EC2)
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.net.URL;
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RMIServer {

    /**
     * Fetch EC2 public IP using AWS metadata service
     * Falls back to localhost if not running on EC2
     */
    private static String getPublicIP() {
        try {
            // Try to get EC2 public IP from metadata service
            URL url = new URL("http://169.254.169.254/latest/meta-data/public-ipv4");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            String ip = br.readLine();
            br.close();
            System.out.println("Running on AWS EC2");
            return ip;
        } catch (Exception e) {
            // Not on EC2, try to get local IP
            try {
                String localIP = InetAddress.getLocalHost().getHostAddress();
                System.out.println("Running locally");
                return localIP;
            } catch (Exception ex) {
                return "localhost";
            }
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("\nRMI Calculator Server - Starting...");
            System.out.println("");
            
            // Get public/local IP
            String publicIP = "13.60.78.219";

            // CRITICAL: Set hostname for RMI stub serialization
            // This ensures clients can connect back to the correct IP
            System.setProperty("java.rmi.server.hostname", publicIP);

            System.out.println("\nServer Configuration:");
            System.out.println("  Public/Local IP  : " + publicIP);
            System.out.println("  Registry Port    : 1099");
            System.out.println("  Object Port      : 1100");
            System.out.println("  Service Name     : CalculatorService");

            // Create the implementation object
            CalculatorImpl calculator = new CalculatorImpl();
            System.out.println("\nCalculator implementation created");

            // Export the remote object on fixed port 1100
            // This is important for cloud environments where we need to configure firewall rules
            CalculatorInterface stub = 
                    (CalculatorInterface) UnicastRemoteObject.exportObject(calculator, 1100);
            System.out.println("Remote object exported on port 1100");

            // Create RMI registry on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println("RMI registry created on port 1099");

            // Bind the stub to the registry
            registry.rebind("CalculatorService", stub);

            System.out.println("CalculatorService bound successfully");
            System.out.println("\nRMI Server is running and ready to accept connections.");

        } catch (Exception e) {
            System.err.println("\nRMI Server Exception:");
            e.printStackTrace();
        }
    }
}
