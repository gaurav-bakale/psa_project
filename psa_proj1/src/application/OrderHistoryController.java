
package application;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class OrderHistoryController {
    
    @FXML
    private ComboBox<String> sortComboBox;
    
    @FXML
    private Button sortButton;
    
    @FXML
    private TableView<Order> orderTable;
    
    @FXML
    private TableColumn<Order, String> orderIdColumn;
    
    @FXML
    private TableColumn<Order, Date> dateColumn;
    
    @FXML
    private TableColumn<Order, Double> amountColumn;
    
    @FXML
    private TableColumn<Order, List<CartEntry>> itemsColumn;
    
    @FXML
    private Button backButton;
    
    private OrderList orderHistory;
    
    @FXML
    public void initialize() {
        // Get the OrderList from Checkout_controller
        orderHistory = Checkout_controller.getOrderHistory();
        
        // Setup sorting options
        List<String> sortOptions = Arrays.asList(
            "Date (Newest First)", 
            "Date (Oldest First)", 
            "Amount (Highest First)", 
            "Amount (Lowest First)"
        );
        sortComboBox.setItems(FXCollections.observableArrayList(sortOptions));
        sortComboBox.getSelectionModel().select(0);
        
        // Setup table columns
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        dateColumn.setCellFactory(column -> {
            return new TableCell<Order, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty || item == null) {
                        setText(null);
                    } else {
                        setText(format.format(item));
                    }
                }
            };
        });
        
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        amountColumn.setCellFactory(column -> {
            return new TableCell<Order, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty || item == null) {
                        setText(null);
                    } else {
                        setText(String.format("$%.2f", item));
                    }
                }
            };
        });
        
     // Find this part in your initialize() method
        itemsColumn.setCellValueFactory(new PropertyValueFactory<>("items"));
        itemsColumn.setCellFactory(column -> {
            return new TableCell<Order, List<CartEntry>>() {
                @Override
                protected void updateItem(List<CartEntry> items, boolean empty) {
                    super.updateItem(items, empty);
                    if (empty || items == null) {
                        setText(null);
                    } else {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < items.size(); i++) {
                            CartEntry entry = items.get(i);
                            sb.append(entry.getProduct().getName())
                              .append(" (")
                              .append(entry.getQuantity())
                              .append(")");

                            if (i < items.size() - 1) {
                                sb.append(", ");
                            }
                        }
                        setText(sb.toString());
                    }
                }
            };
        });

        // Load data
        loadOrderData();
    }
    
    private void loadOrderData() {
        ObservableList<Order> orders = FXCollections.observableArrayList();
        for(int i = 0; i < orderHistory.size(); i++) {
            orders.add(orderHistory.get(i));
        }
        orderTable.setItems(orders);
    }
    
    @FXML
    public void handleSort(ActionEvent event) {
        int selectedIndex = sortComboBox.getSelectionModel().getSelectedIndex();
        
        switch(selectedIndex) {
            case 0: // Date (Newest First)
                orderHistory.sortByDateDescending();
                break;
            case 1: // Date (Oldest First)
                orderHistory.sortByDateAscending();
                break;
            case 2: // Amount (Highest First)
                orderHistory.sortByAmountDescending();
                break;
            case 3: // Amount (Lowest First)
                orderHistory.sortByAmountAscending();
                break;
        }
        
        loadOrderData();
    }
    
    @FXML
    public void handleBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}