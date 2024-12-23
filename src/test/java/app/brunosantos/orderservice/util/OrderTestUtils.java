package app.brunosantos.orderservice.util;

import app.brunosantos.orderservice.domain.model.Order;

/**
 * Utility class for shared test methods related to Order.
 */
public class OrderTestUtils {

    /**
     * Generates a unique order code for testing.
     * 
     * @param order the order for which to generate the code
     * @return a unique order code
     */
    public static String generateOrderCode(Order order) {
        // Example: Create a unique order code
        return "ORDER-" + System.currentTimeMillis();
    }

    // Add other shared utility methods as needed
}
