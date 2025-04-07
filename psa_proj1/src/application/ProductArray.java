package application;

public class ProductArray {
	
	private Product[] products;
    private int size;
    private static final int DEFAULT_CAPACITY = 32;
    
    // Constructor with default capacity
    public ProductArray() {
        this(DEFAULT_CAPACITY);
    }
    
    // Constructor with specified capacity
    public ProductArray(int capacity) {
        products = new Product[capacity];
        size = 0;
    }
    
    // Add a product to the array
    public void add(Product product) {
        if (size >= products.length) {
            resize();
        }
        products[size++] = product;
    }
    
    // Get product at specified index
    public Product get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return products[index];
    }
    
    // Get the number of products in the array
    public int size() {
        return size;
    }
    
    // Check if the array is empty
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Resize the array when it becomes full
    private void resize() {
        Product[] newArray = new Product[products.length * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = products[i];
        }
        products = newArray;
    }
    
    // Find a product by name (exact match)
    public Product findByName(String productName) {
        for (int i = 0; i < size; i++) {
            if (products[i].getName().equals(productName)) {
                return products[i];
            }
        }
        return null;
    }
    
    // Search for products by name (partial match)
    public ProductArray searchByName(String searchString) {
        ProductArray result = new ProductArray();
        
        for (int i = 0; i < size; i++) {
            String productName = products[i].getName();
            if (containsIgnoreCase(productName, searchString)) {
                result.add(products[i]);
            }
        }
        
        return result;
    }
    
    // Helper method for case-insensitive substring search
    private boolean containsIgnoreCase(String source, String searchString) {
        final int length = searchString.length();
        if (length == 0)
            return true; // Empty string is contained
            
        final char firstLower = Character.toLowerCase(searchString.charAt(0));
        final char firstUpper = Character.toUpperCase(searchString.charAt(0));
        
        for (int i = source.length() - length; i >= 0; i--) {
            final char ch = source.charAt(i);
            if (ch != firstLower && ch != firstUpper)
                continue;
            
            if (source.regionMatches(true, i, searchString, 0, length))
                return true;
        }
        
        return false;
    }

}
