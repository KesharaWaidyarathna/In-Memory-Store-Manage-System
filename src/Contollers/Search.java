package Contollers;

import Classes.*;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;


public class Search {

    public JFXTextField txtSearch;
    public TableView<SearchTM> tblSearch;
    public TableColumn clmID;
    public TableColumn clmTotal;
    public TableColumn clmDate;
    public TableColumn clmCustomerId;
    public TableColumn clmCustomerName;
    public AnchorPane anpSearchOrders;


    public void initialize(){


        tblSearch.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderID"));
        tblSearch.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblSearch.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("total"));
        tblSearch.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblSearch.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("customerName"));

        ObservableList<SearchTM> searchTMS = FXCollections.observableList(ClasDB.search);

        tblSearch.setItems(searchTMS);





    }

    public void load(){

        ObservableList<Orders> orderList= FXCollections.observableArrayList(ClasDB.orders);
        SearchTM searchTM = new SearchTM();
        for (int i = 0; i < orderList.size(); i++) {
            searchTM.setCustomerId(orderList.get(i).getCustomerId());

//            searchTM.setCustomerName(orderList.get(i).);
        }

//        tblSearch.setItems(orderList);

    }

    public void btnHome_Action(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/FXML/DashboardCMS.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene mainScene = new Scene(root);
        Stage primarystage=(Stage)(this.anpSearchOrders.getScene().getWindow());
        primarystage.setScene(mainScene);
        primarystage.centerOnScreen();
    }


}
