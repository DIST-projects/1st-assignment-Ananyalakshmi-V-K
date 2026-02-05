/**
 * RMI Client Implementation
 * Connects to the remote Calculator service and invokes methods
 * Provides an interactive menu for testing all calculator operations
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RMIClient {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            // Display banner
            System.out.println("\nRMI Calculator Client");
            System.out.println("");

            // Get server IP from user
            System.out.print("Enter Server IP (or press Enter for localhost): ");
            String serverIP = sc.nextLine().trim();
            if (serverIP.isEmpty()) {
                serverIP = "localhost";
            }

            System.out.println("\nConnecting to RMI server at " + serverIP + ":1099...");

            // Connect to RMI registry
            Registry registry = LocateRegistry.getRegistry(serverIP, 1099);
            
            // Lookup the remote calculator service
            CalculatorInterface calculator = 
                    (CalculatorInterface) registry.lookup("CalculatorService");

            System.out.println("Connected successfully!");

            // Interactive menu
            boolean running = true;
            while (running) {
                displayMenu();
                System.out.print("Enter your choice: ");
                
                String choice = sc.nextLine().trim();
                
                try {
                    switch (choice) {
                        case "1":
                            performAddition(calculator, sc);
                            break;
                        case "2":
                            performSubtraction(calculator, sc);
                            break;
                        case "3":
                            performMultiplication(calculator, sc);
                            break;
                        case "4":
                            performDivision(calculator, sc);
                            break;
                        case "5":
                            performPower(calculator, sc);
                            break;
                        case "6":
                            performModulus(calculator, sc);
                            break;
                        case "7":
                            getServerInfo(calculator);
                            break;
                        case "8":
                            runAllTests(calculator);
                            break;
                        case "9":
                            System.out.println("\nDisconnecting from server...");
                            System.out.println("Thank you for using RMI Calculator!");
                            running = false;
                            break;
                        default:
                            System.out.println("\nInvalid choice. Please try again.");
                    }
                } catch (Exception e) {
                    System.err.println("\nError during operation: " + e.getMessage());
                    e.printStackTrace();
                }
                
                if (running) {
                    System.out.println("\nPress Enter to continue...");
                    sc.nextLine();
                }
            }

        } catch (Exception e) {
            System.err.println("\nRMI Client Exception: " + e.getMessage());
            System.err.println("\nMake sure:");
            System.err.println("  1. The RMI server is running");
            System.err.println("  2. The server IP address is correct");
            System.err.println("  3. Ports 1099 and 1100 are open");
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }

    /**
     * Display the interactive menu
     */
    private static void displayMenu() {
        System.out.println("\nCALCULATOR OPERATIONS");
        System.out.println("");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.println("5. Power (a^b)");
        System.out.println("6. Modulus (a%b)");
        System.out.println("7. Get Server Info");
        System.out.println("8. Run All Tests");
        System.out.println("9. Exit");
        System.out.println("");
    }

    /**
     * Perform addition operation
     */
    private static void performAddition(CalculatorInterface calc, Scanner sc) throws Exception {
        System.out.print("\nEnter first number: ");
        int a = Integer.parseInt(sc.nextLine());
        System.out.print("Enter second number: ");
        int b = Integer.parseInt(sc.nextLine());
        
        int result = calc.add(a, b);
        System.out.println("\nResult: " + a + " + " + b + " = " + result);
    }

    /**
     * Perform subtraction operation
     */
    private static void performSubtraction(CalculatorInterface calc, Scanner sc) throws Exception {
        System.out.print("\nEnter first number: ");
        int a = Integer.parseInt(sc.nextLine());
        System.out.print("Enter second number: ");
        int b = Integer.parseInt(sc.nextLine());
        
        int result = calc.subtract(a, b);
        System.out.println("\nResult: " + a + " - " + b + " = " + result);
    }

    /**
     * Perform multiplication operation
     */
    private static void performMultiplication(CalculatorInterface calc, Scanner sc) throws Exception {
        System.out.print("\nEnter first number: ");
        int a = Integer.parseInt(sc.nextLine());
        System.out.print("Enter second number: ");
        int b = Integer.parseInt(sc.nextLine());
        
        int result = calc.multiply(a, b);
        System.out.println("\nResult: " + a + " * " + b + " = " + result);
    }

    /**
     * Perform division operation
     */
    private static void performDivision(CalculatorInterface calc, Scanner sc) throws Exception {
        System.out.print("\nEnter numerator: ");
        int a = Integer.parseInt(sc.nextLine());
        System.out.print("Enter denominator: ");
        int b = Integer.parseInt(sc.nextLine());
        
        double result = calc.divide(a, b);
        System.out.println("\nResult: " + a + " / " + b + " = " + result);
    }

    /**
     * Perform power operation
     */
    private static void performPower(CalculatorInterface calc, Scanner sc) throws Exception {
        System.out.print("\nEnter base: ");
        int base = Integer.parseInt(sc.nextLine());
        System.out.print("Enter exponent: ");
        int exp = Integer.parseInt(sc.nextLine());
        
        double result = calc.power(base, exp);
        System.out.println("\nResult: " + base + " ^ " + exp + " = " + result);
    }

    /**
     * Perform modulus operation
     */
    private static void performModulus(CalculatorInterface calc, Scanner sc) throws Exception {
        System.out.print("\nEnter first number: ");
        int a = Integer.parseInt(sc.nextLine());
        System.out.print("Enter second number: ");
        int b = Integer.parseInt(sc.nextLine());
        
        int result = calc.modulus(a, b);
        System.out.println("\nResult: " + a + " % " + b + " = " + result);
    }

    /**
     * Get server information
     */
    private static void getServerInfo(CalculatorInterface calc) throws Exception {
        String info = calc.getServerInfo();
        System.out.println(info);
    }

    /**
     * Run comprehensive tests on all operations
     */
    private static void runAllTests(CalculatorInterface calc) throws Exception {
        System.out.println("\nRUNNING COMPREHENSIVE TESTS");
        System.out.println("");
        
        // Test addition
        System.out.println("Test 1: Addition");
        int addResult = calc.add(15, 25);
        System.out.println("  15 + 25 = " + addResult);
        
        // Test subtraction
        System.out.println("\nTest 2: Subtraction");
        int subResult = calc.subtract(50, 20);
        System.out.println("  50 - 20 = " + subResult);
        
        // Test multiplication
        System.out.println("\nTest 3: Multiplication");
        int mulResult = calc.multiply(7, 8);
        System.out.println("  7 * 8 = " + mulResult);
        
        // Test division
        System.out.println("\nTest 4: Division");
        double divResult = calc.divide(100, 4);
        System.out.println("  100 / 4 = " + divResult);
        
        // Test power
        System.out.println("\nTest 5: Power");
        double powResult = calc.power(2, 10);
        System.out.println("  2 ^ 10 = " + powResult);
        
        // Test modulus
        System.out.println("\nTest 6: Modulus");
        int modResult = calc.modulus(17, 5);
        System.out.println("  17 % 5 = " + modResult);
        
        System.out.println("\nAll tests completed successfully!");
    }
}
