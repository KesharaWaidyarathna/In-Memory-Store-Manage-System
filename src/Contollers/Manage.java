package Contollers;

import Classes.ClasDB;
import Classes.CustomerTM;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

public class Manage {
    public AnchorPane anpManage;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public TableColumn clmID;
    public TableColumn clmName;
    public TableColumn clmAddress;
    public TableView<CustomerTM> tblCustomer;
    public JFXTextField txtId;
    public JFXButton btnDelete;
    public JFXButton btnSave;
    int newId=5;

    
    public void initialize(){




        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        ObservableList<CustomerTM> customer= FXCollections.observableList(ClasDB.customers);
        tblCustomer.setItems(customer);


        tblCustomer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerTM>() {
            @Override
            public void changed(ObservableValue<? extends CustomerTM> observable, CustomerTM oldValue, CustomerTM newValue) {

                CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();

                if(selectedItem==null){
                    btnSave.setText("SAAVE");
                    btnDelete.setDisable(true);
                }

                btnSave.setText("Update");
                btnDelete.setDisable(false);
                btnSave.setDisable(false);
                txtId.setText(selectedItem.getId());
                txtName.setText(selectedItem.getName());
                txtAddress.setText(selectedItem.getAddress());

            }
        });


    }

    public void btnNew_Action(ActionEvent actionEvent) {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtId.setDisable(true);


        for (CustomerTM customer : ClasDB.customers) {
            int id = Integer.parseInt(customer.getId().replace("C", ""));
            if (id > newId){
                newId = id;
            }
        }
        newId = newId + 1;
        String id = "";
        if (newId < 10){
            id = "C00" + newId;
        }else if (newId < 100){
            id = "C0" + newId;
        }else{
            id = "C" + newId;
        }
        txtId.setText(id);
    }

    public void btnback_Action(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/FXML/DashboardCMS.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene mainScene = new Scene(root);
        Stage primarystage=(Stage)(this.anpManage.getScene().getWindow());
        primarystage.setScene(mainScene);
        primarystage.centerOnScreen();
    }

    public void btnSave_Action(ActionEvent actionEvent) {

        if (btnSave.getText().equals("SAVE")) {
            ObservableList<CustomerTM> customers = tblCustomer.getItems();
            customers.add(new CustomerTM(
                    txtId.getText(),
                    txtName.getText(),
                    txtAddress.getText()
            ));
            btnNew_Action(actionEvent);
        }else {
            CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
            selectedItem.setName(txtName.getText());
            selectedItem.setAddress(txtAddress.getText());
        }
        tblCustomer.refresh();
        btnNew_Action(actionEvent);
    }


    public void btnDelete_Action(ActionEvent actionEvent) {

        Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you sure", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get()==ButtonType.YES) {
            CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
            tblCustomer.getItems().remove(selectedItem);
        }

    }
}
