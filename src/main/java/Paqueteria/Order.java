package Paqueteria;

import java.io.Serializable;

public class Order implements Serializable {
    private String orderId;
    private String customerName;
    private String customerEmail;
    private String shippingAddress;

    public Order(String orderId, String customerName, String customerEmail, String shippingAddress) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.shippingAddress = shippingAddress;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }
}
