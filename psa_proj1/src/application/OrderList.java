package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class OrderList {
	
	  private List<Order> orders;
	    
	    public OrderList() {
	        orders = new ArrayList<>();
	    }
	    
	    // Add an order to the list
	    public void addOrder(Order order) {
	        orders.add(order);
	    }
	    
	    // Get all orders
	    public List<Order> getAllOrders() {
	        return orders;
	    }
	    
	    // Sort orders by date (newest first)
	    public void sortByDateDescending() {
	        Collections.sort(orders, new Comparator<Order>() {
	            @Override
	            public int compare(Order o1, Order o2) {
	                return o2.getOrderDate().compareTo(o1.getOrderDate());
	            }
	        });
	    }
	    
	    // Sort orders by date (oldest first)
	    public void sortByDateAscending() {
	        Collections.sort(orders, new Comparator<Order>() {
	            @Override
	            public int compare(Order o1, Order o2) {
	                return o1.getOrderDate().compareTo(o2.getOrderDate());
	            }
	        });
	    }
	    
	    // Sort orders by total amount (highest first)
	    public void sortByAmountDescending() {
	        Collections.sort(orders, new Comparator<Order>() {
	            @Override
	            public int compare(Order o1, Order o2) {
	                return Double.compare(o2.getTotalAmount(), o1.getTotalAmount());
	            }
	        });
	    }
	    
	    // Sort orders by total amount (lowest first)
	    public void sortByAmountAscending() {
	        Collections.sort(orders, new Comparator<Order>() {
	            @Override
	            public int compare(Order o1, Order o2) {
	                return Double.compare(o1.getTotalAmount(), o2.getTotalAmount());
	            }
	        });
	    }
	    
	    // Get the size of the order list
	    public int size() {
	        return orders.size();
	    }
	    
	    // Get an order by index
	    public Order get(int index) {
	        if (index < 0 || index >= orders.size()) {
	            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + orders.size());
	        }
	        return orders.get(index);
	    }
	
	
}
