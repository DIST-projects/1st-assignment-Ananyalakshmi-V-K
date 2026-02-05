/**
 * Remote Interface for RMI Calculator
 * Defines methods that can be invoked remotely
 * This interface must extend Remote and all methods must throw RemoteException
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculatorInterface extends Remote {
    /**
     * Add two integers
     * @param a First number
     * @param b Second number
     * @return Sum of a and b
     * @throws RemoteException if remote call fails
     */
    int add(int a, int b) throws RemoteException;
    
    /**
     * Subtract two integers
     * @param a First number
     * @param b Second number
     * @return Difference of a and b
     * @throws RemoteException if remote call fails
     */
    int subtract(int a, int b) throws RemoteException;
    
    /**
     * Multiply two integers
     * @param a First number
     * @param b Second number
     * @return Product of a and b
     * @throws RemoteException if remote call fails
     */
    int multiply(int a, int b) throws RemoteException;
    
    /**
     * Divide two integers
     * @param a Numerator
     * @param b Denominator
     * @return Division result
     * @throws RemoteException if remote call fails
     */
    double divide(int a, int b) throws RemoteException;
    
    /**
     * Calculate power (a raised to b)
     * @param base Base number
     * @param exponent Exponent
     * @return base^exponent
     * @throws RemoteException if remote call fails
     */
    double power(int base, int exponent) throws RemoteException;
    
    /**
     * Calculate modulus
     * @param a First number
     * @param b Second number
     * @return a % b
     * @throws RemoteException if remote call fails
     */
    int modulus(int a, int b) throws RemoteException;
    
    /**
     * Get server information including hostname and timestamp
     * @return Server information string
     * @throws RemoteException if remote call fails
     */
    String getServerInfo() throws RemoteException;
}