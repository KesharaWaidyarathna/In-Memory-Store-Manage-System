package Contollers;

import Classes.ClasDB;
import Classes.ItemsTM;
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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class ManageItemController {
    public JFXTextField txtItemCode;
    public JFXTextField txtItemDiscription;
    public JFXTextField txtHOQ;
    public JFXTextField txtUnitPrice;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public TableView<ItemsTM> tblItem;
    public AnchorPane anpManageItems;
    int itemId = 0;

    public void initialize() {

        tblItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemId"));
        tblItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemDiscripition"));
        tblItem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("handsOnQuantity"));
        tblItem.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        ObservableList<ItemsTM> items = FXCollections.observableList(ClasDB.items);
        tblItem.setItems(items);

        tblItem.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ItemsTM>() {
            @Override
            public void changed(ObservableValue<? extends ItemsTM> observable, ItemsTM oldValue, ItemsTM newValue) {

                ItemsTM selectedItem = tblItem.getSelectionModel().getSelectedItem();
                if (selectedItem == null) {
                    btnSave.setText("Save");
                    btnDelete.setDisable(true);
                }

                btnSave.setText("Update");
                btnDelete.setDisable(false);
                btnSave.setDisable(false);
                txtItemDiscription.requestFocus();
                txtItemCode.setText(selectedItem.getItemId());
                txtItemDiscription.setText(selectedItem.getItemDiscripition());
                txtHOQ.setText(selectedItem.getHandsOnQuantity());
                txtUnitPrice.setText(String.valueOf(selectedItem.getUnitPrice()));
            }
        });
    }

    public void btnSave_Action(ActionEvent actionEvent) {

        String quantityHand=txtHOQ.getText();
        String unitPrice=txtUnitPrice.getText();

        if(!quantityHand.matches("^\\d+$")){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Can not Enter letters or symbol for \" Hands on Quantity\" ", ButtonType.OK);
            alert.show();
            txtHOQ.requestFocus();
        }else if(!unitPrice.matches("^\\d+$")){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Can not Enter letters or symbol for \" Unit Price\" ", ButtonType.OK);
            alert.show();
            txtUnitPrice.requestFocus();
        }
        else {

            if (btnSave.getText().equals("Save")) {
                double price = Double.parseDouble(txtUnitPrice.getText());
                ObservableList<ItemsTM> item = tblItem.getItems();
                item.add(new ItemsTM(
                        txtItemCode.getText(),
                        txtItemDiscription.getText(),
                        txtHOQ.getText(),
                        price
                ));
                Alert alert=new Alert(Alert.AlertType.INFORMATION,"Item add successfully !  ",ButtonType.OK);
                alert.show();
                btnNew_Action(actionEvent);
            } else {

                ItemsTM selectedItem = tblItem.getSelectionModel().getSelectedItem();
                selectedItem.setItemId(txtItemCode.getText());
                selectedItem.setItemDiscripition(txtItemDiscription.getText());
                selectedItem.setHandsOnQuantity(txtHOQ.getText());
                selectedItem.setUnitPrice(Double.parseDouble(txtHOQ.getText()));
                tblItem.refresh();
                btnNew_Action(actionEvent);
                Alert alert=new Alert(Alert.AlertType.INFORMATION,"Item details update successfully !  ",ButtonType.OK);
                alert.show();
            }
        }


    }

    public void btnDelete_Action(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this Item?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            ItemsTM selectedItem = tblItem.getSelectionModel().getSelectedItem();
            tblItem.getItems().remove(selectedItem);
        }
        Alert alertDelete=new Alert(Alert.AlertType.INFORMATION,"Item delete successfully !  ",ButtonType.OK);
        alertDelete.show();
    }

    public void btnBack_Action(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/DashboardCMS.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene mainScene = new Scene(root);
        Stage primarystage = (Stage) (this.anpManageItems.getScene().getWindow());
        primarystage.setScene(mainScene);
        primarystage.centerOnScreen();
    }

    public void btnNew_Action(ActionEvent actionEvent) {
        txtItemCode.clear();
        txtItemDiscription.clear();
        txtHOQ.clear();
        txtUnitPrice.clear();
        txtItemCode.setDisable(true);
        txtItemDiscription.requestFocus();
        tblItem.getSelectionModel().clearSelection();


        for (ItemsTM item : ClasDB.items) {
            int id = Integer.parseInt(item.getItemId().replace("I", ""));
            if (id > itemId) {
                itemId = id;
            }
        }
        itemId = itemId + 1;
        String id = "";
        if (itemId < 10) {
            id = "I00" + itemId;
        } else if (itemId < 100) {
            id = "I0" + itemId;
        } else {
            id = "I" + itemId;
        }
        txtItemCode.setText(id);

    }
}
