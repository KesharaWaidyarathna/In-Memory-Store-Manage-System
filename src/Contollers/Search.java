package Contollers;

import Classes.*;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class Search {

    public JFXTextField txtSearch;
    public TableView<SearchTM> tblSearch;
    public TableColumn clmID;
    public TableColumn clmTotal;
    public TableColumn clmDate;
    public TableColumn clmCustomerId;
    public TableColumn clmCustomerName;
    public AnchorPane anpSearchOrders;


    public void initialize() {

        tblSearch.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderID"));
        tblSearch.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblSearch.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("total"));
        tblSearch.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblSearch.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("customerName"));

        ObservableList<Orders> orders = FXCollections.observableArrayList(ClasDB.orders);
        ObservableList<OrdersTM> orderTable = FXCollections.observableArrayList(ClasDB.ordertm);
        ObservableList<CustomerTM> customerName = FXCollections.observableArrayList(ClasDB.customers);

        ObservableList<SearchTM> tableList = FXCollections.observableArrayList();


        if (orders.size() > 0) {
            for (int i = 0; i < orders.size(); i++) {
                for (int j = 0; j < orders.get(i).getOrderDetails().size(); j++) {
                    String customerName1 = null;
                    for (int k = 0; k < customerName.size(); k++) {
                        if (orders.get(i).getCustomerId() == customerName.get(k).getId()) {
                            System.out.println("Found!");
                            customerName1 = customerName.get(k).getName();
                            break;
                        } else {
                            System.out.println("User not found.");
                        }
                    }

                    System.out.println(orders.get(i));
                    int quantity = Integer.parseInt(orders.get(i).getOrderDetails().get(j).getQuantity());
                    double unitprise = orders.get(i).getOrderDetails().get(j).getUnitPrice();
                    double total = quantity * unitprise;

                    tableList.add(new SearchTM(orders.get(i).getId(), orders.get(i).getDate(),
                            String.valueOf(total), orders.get(i).getCustomerId(), customerName1));

                }
            }
        }
      tblSearch.setItems(tableList);

        ObservableList<SearchTM>searchCopy=FXCollections.observableArrayList(tableList);

        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                String input = txtSearch.getText();
                ObservableList<SearchTM>tempSearch=FXCollections.observableArrayList();

                for (SearchTM searchTM : searchCopy) {

                    if(searchTM.getOrderID().contains(input) ||searchTM.getDate().contains(input)||searchTM.getTotal().contains(input)||
                            searchTM.getCustomerId().contains(input) || searchTM.getCustomerName().contains(input)){
                        tempSearch.add(searchTM);

                    }
                }

                tblSearch.setItems(tempSearch);

            }
        });


    }

    public void btnHome_Action(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/DashboardCMS.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene mainScene = new Scene(root);
        Stage primarystage = (Stage) (this.anpSearchOrders.getScene().getWindow());
        primarystage.setScene(mainScene);
        primarystage.centerOnScreen();
    }


    public void tblSearch_Action(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getClickCount()==2){
            URL resource = this.getClass().getResource("/View/PlaceOrder.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(resource);
            Parent root = fxmlLoader.load();
            Scene placeOrder = new Scene(root);
            Stage secondouary = new Stage();
            secondouary.setScene(placeOrder);
            secondouary.centerOnScreen();
            secondouary.setResizable(false);
            secondouary.show();

            PlaceOrderController crl = fxmlLoader.getController();
            SearchTM selectedOrder = tblSearch.getSelectionModel().getSelectedItem();
            crl.initializePlaceOrder(selectedOrder.getOrderID());

        }
    }
}


