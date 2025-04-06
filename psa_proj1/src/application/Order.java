package application;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	
	
	 private String orderId;
	    private Date orderDate;
	    private double totalAmount;
	    private List<CartEntry> items;
	    
	    public Order(String orderId, Date orderDate, double totalAmount, List<CartEntry> items) {
	        this.orderId = orderId;
	        this.orderDate = orderDate;
	        this.totalAmount = totalAmount;
	        this.items = new ArrayList<>(items);
	    }
	    
	    public String getOrderId() {
	        return orderId;
	    }
	    
	    public Date getOrderDate() {
	        return orderDate;
	    }
	    
	    public double getTotalAmount() {
	        return totalAmount;
	    }
	    
	    public List<CartEntry> getItems() {
	        return items;
	    }

}
