package Contollers;

import Classes.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;


public class PlaceOrderController {
    public JFXTextField txtDescription;
    public JFXComboBox<String> cmbItemCode;
    public JFXTextField txtUnitePrice;
    public JFXTextField txtQuantity;
    public TableView<OrdersTM> tblOrders;
    public JFXTextField txtQOH;
    public Label lblTotal;
    public JFXButton btnSave;
    public JFXComboBox cmbCustomerID;
    public JFXTextField txtOrderID;
    public JFXTextField txtName;
    public JFXButton btnNewOrder;
    public JFXTextField txtDate;
    public AnchorPane apnPlaceOrder;

    static int newId = 0;

    public void initialize() {

        ObservableList<CustomerTM> cutomers = cmbCustomerID.getItems();
        ObservableList<String> items = cmbItemCode.getItems();


        for (CustomerTM customer : ClasDB.customers) {
            cmbCustomerID.getItems().add(customer.getId());
        }

        for (ItemsTM item : ClasDB.items) {
            cmbItemCode.getItems().add(item.getItemId());

        }

        newId = newId + 1;
        String id = "";
        if (newId < 10) {
            id = "OD00" + newId;
        } else if (newId < 100) {
            id = "OD0" + newId;
        } else {
            id = "OD" + newId;
        }
        txtOrderID.setText(id);


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        txtDate.setText(formatter.format(date) + "");

        cmbCustomerID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                Object selectedItem = cmbCustomerID.getSelectionModel().getSelectedItem();

                if (selectedItem == null) {
                    return;
                }
                for (CustomerTM name : ClasDB.customers) {
                    if (selectedItem.equals(name.getId())) {
                        txtName.setText(name.getName());
                    }
                }

            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                Object selectedItem = cmbItemCode.getSelectionModel().getSelectedItem();

                if (selectedItem == null) {
                    return;
                }

                for (ItemsTM item : ClasDB.items) {
                    if (selectedItem.equals(item.getItemId())) {
                        txtQOH.setText(item.getHandsOnQuantity());
                        txtDescription.setText(item.getItemDiscripition());
                        txtUnitePrice.setText(String.valueOf(item.getUnitPrice()));

                    }

                }


            }
        });

        tblOrders.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<OrdersTM>() {
            @Override
            public void changed(ObservableValue<? extends OrdersTM> observable, OrdersTM oldValue, OrdersTM newValue) {

                OrdersTM selectedItem = tblOrders.getSelectionModel().getSelectedItem();
                if (selectedItem == null) {
                    btnSave.setText("Add");
                }

                btnSave.setText("Update");

            }
        });

        txtOrderID.setEditable(false);
        txtDate.setEditable(false);
        txtDescription.setEditable(false);
        txtName.setEditable(false);
        txtQOH.setEditable(false);
        txtUnitePrice.setEditable(false);

        tblOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        tblOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Description"));
        tblOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        tblOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("UnitePrice"));
        tblOrders.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Total"));
        tblOrders.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("delete"));

        ObservableList<OrdersTM> ordes = FXCollections.observableList(ClasDB.ordertm);
        tblOrders.setItems(ordes);

    }

    public void btnNewOrder_Action(ActionEvent actionEvent) {

        //initialize();
        cmbItemCode.getSelectionModel().clearSelection();
        txtDescription.clear();
        txtUnitePrice.clear();
        txtQOH.clear();
        txtQuantity.clear();
        cmbCustomerID.setDisable(true);
        tblOrders.getSelectionModel().clearSelection();
        btnSave.setText("Add");


    }

    public void btnHome_Action(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/FXML/DashboardCMS.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene mainScene = new Scene(root);
        Stage primarystage = (Stage) (this.apnPlaceOrder.getScene().getWindow());
        primarystage.setScene(mainScene);
        primarystage.centerOnScreen();

    }

    public void btnSave_Action(ActionEvent actionEvent) {

        double totalAll = 0.00;
        if (btnSave.getText().equals("Add")) {


            if (txtQuantity.getText().isEmpty() || txtQuantity.getText().equals("0")) {
                System.out.println("fkf");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Quantity is Empty or invalid", ButtonType.OK);
                alert.show();
                return;
            }

            int quanty = Integer.parseInt(txtQuantity.getText());
            int HNQ = Integer.parseInt(txtQOH.getText());

            if (HNQ < quanty) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Quantity is Higher than stock", ButtonType.OK);
                alert.show();
                return;

            }
            Double a = quanty * Double.parseDouble(txtUnitePrice.getText());
            String total = String.valueOf(a);

            ObservableList<OrdersTM> order = tblOrders.getItems();
            String selectedItem = cmbItemCode.getSelectionModel().getSelectedItem();

            for (int i = 0; i < order.size(); i++) {

                if (selectedItem.equals(order.get(i).getItemCode())) {
                    int d = Integer.parseInt(order.get(i).getQuantity());
                    int l = Integer.parseInt((txtQuantity.getText()));
                    int sum1 = d + l;
                    order.get(i).setQuantity(String.valueOf(sum1));
                    a = sum1 * Double.parseDouble(txtUnitePrice.getText());
                    order.get(i).setTotal(String.valueOf(a));

                    for (int j = 0; j < order.size(); j++) {

                        totalAll += Double.parseDouble(order.get(j).getTotal());
                    }
                    lblTotal.setText(totalAll + "");
                    tblOrders.refresh();
                    return;
                }

            }

            JFXButton jfxButton = new JFXButton("Delete");
            Object selectedItem4 = cmbItemCode.getSelectionModel().getSelectedItem();
            String item = String.valueOf(selectedItem4);

            OrdersTM newone = new OrdersTM(
                    item,
                    txtDescription.getText(),
                    txtQuantity.getText(),
                    txtUnitePrice.getText(),
                    total,
                    jfxButton
            );

            order.add(newone);
            jfxButton.setOnAction(event -> {
                btnDelete_Action(newone);
            });

            for (int i = 0; i < order.size(); i++) {
                totalAll += Double.parseDouble(order.get(i).getTotal());
            }

            lblTotal.setText("" + totalAll);

            // Update the stock
            ObservableList<ItemsTM> items = FXCollections.observableList(ClasDB.items);
            String selectedItemCode = cmbItemCode.getSelectionModel().getSelectedItem();

            for (int i = 0; i < items.size(); i++) {
                if (selectedItemCode.equals(items.get(i).getItemId())) {

                    int count = Integer.parseInt(items.get(i).getHandsOnQuantity());
                    int countnext = Integer.parseInt(txtQuantity.getText());
                    int newQty = (count - countnext);
                    items.get(i).setHandsOnQuantity(String.valueOf(newQty));
                }
            }
        } else {
            ObservableList<OrdersTM> table = tblOrders.getItems();
            OrdersTM selectedItem1 = tblOrders.getSelectionModel().getSelectedItem();
            ObservableList<ItemsTM> item = FXCollections.observableList(ClasDB.items);
            ObservableList<OrdersTM> ordes1 = FXCollections.observableList(ClasDB.ordertm);
            int value = 0;

            for (int j = 0; j < table.size(); j++) {
                if (selectedItem1.getItemCode() == table.get(j).getItemCode()) {
                    value = Integer.parseInt(ordes1.get(j).getQuantity());
                    ordes1.get(j).setQuantity(txtQuantity.getText());
                    double count = Double.parseDouble(ordes1.get(j).getQuantity());
                    double contq = Double.parseDouble(ordes1.get(j).getUnitePrice());
                    double sum = contq * count;
                    totalAll += sum;
                    lblTotal.setText(totalAll + "");
                    ordes1.get(j).setTotal(String.valueOf(sum));

                }
                if (selectedItem1.getItemCode() == item.get(j).getItemId()) {
                    int count = Integer.parseInt(ordes1.get(j).getQuantity());
                    int countq = Integer.parseInt(item.get(j).getHandsOnQuantity() + value);
                    int sum = countq - count;
                    item.get(j).setHandsOnQuantity(String.valueOf(sum));
                }

            }
            tblOrders.refresh();


        }


    }

    private void btnDelete_Action(OrdersTM newone) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        ObservableList<ItemsTM> item = FXCollections.observableList(ClasDB.items);
        if (buttonType.get() == ButtonType.YES) {
            Double count1 = Double.valueOf(newone.getTotal());
            Double finalcount = Double.valueOf(lblTotal.getText());
            lblTotal.setText(String.valueOf(finalcount - count1));

            for (int i = 0; i < item.size(); i++) {
                if (item.get(i).getItemId().equals(newone.getItemCode())) {
                    int hq = Integer.parseInt(item.get(i).getHandsOnQuantity());
                    int q = Integer.parseInt(newone.getQuantity());
                    String sum = String.valueOf(hq + q);
                    item.get(i).setHandsOnQuantity(sum);
                }
                tblOrders.getItems().remove(newone);
            }
        }


    }


    public void btnPlaceOrder_Action(ActionEvent actionEvent) {
        OrderDetails orderDetails1 = new OrderDetails();
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();

        ObservableList<OrdersTM> table = tblOrders.getItems();
        for (int i = 0; i < table.size(); i++) {

            orderDetails1 = new OrderDetails(table.get(i).getItemCode(), table.get(i).getQuantity(), Double.parseDouble(table.get(i).getUnitePrice()));
            System.out.println(orderDetails1);
            orderDetails.add(orderDetails1);
        }

        ObservableList<Orders> orders = FXCollections.observableList(ClasDB.orders);

        orders.add(new Orders(txtOrderID.getText(), txtDate.getText(), (String) cmbCustomerID.getSelectionModel().getSelectedItem(), orderDetails));
        System.out.println(orders.toString());


        txtDescription.clear();
        txtUnitePrice.clear();
        txtQOH.clear();
        txtQuantity.clear();
        cmbCustomerID.setDisable(false);
        cmbCustomerID.getItems().clear();
        cmbItemCode.getItems().clear();
        tblOrders.getItems().clear();
        lblTotal.setText("");
        initialize();
    }


    public void btnSave_KeyAction(KeyEvent keyEvent) {
    }

    public void initializePlaceOrder(String orderID) {
        ObservableList<Orders> ordersId = FXCollections.observableArrayList(ClasDB.orders);
        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList(ClasDB.customers);
        ObservableList<ItemsTM> items = FXCollections.observableArrayList(ClasDB.items);
        ObservableList<CustomerTM> customerid = cmbCustomerID.getItems();
        ObservableList<OrdersTM>table=FXCollections.observableArrayList();





        for (int i = 0; i < ordersId.size(); i++) {
            String dis=null;
            if (orderID.equals(ordersId.get(i).getId())) {
                for (int j = 0; j < customerTMS.size(); j++) {

                    if (ordersId.get(i).getCustomerId().equals(customerTMS.get(j).getId())) {

                        txtOrderID.setText(ordersId.get(i).getId());
                        txtDate.setText(ordersId.get(i).getDate());
//                        cmbCustomerID.getSelectionModel().select(ordersId.get(j).getCustomerId());
//                        cmbCustomerID.setId(ordersId.get(j).getCustomerId());
                        txtName.setText(customerTMS.get(j).getName());
                        txtQuantity.setText(ordersId.get(i).getOrderDetails().get(i).getQuantity());
                        txtUnitePrice.setText(String.valueOf(ordersId.get(i).getOrderDetails().get(i).getUnitPrice()));

                        for (int k = 0; k <items.size() ; k++) {

                            if(ordersId.get(i).getOrderDetails().get(i).getCode().equals(items.get(k).getItemId())){
                                dis=items.get(k).getItemDiscripition();

                                txtDescription.setText(dis);
                                txtQOH.setText(items.get(k).getHandsOnQuantity());
                            }

                        }
                        for (int l = 0; l <ordersId.get(i).getOrderDetails().size() ; l++) {

                            int quantity = Integer.parseInt(ordersId.get(i).getOrderDetails().get(l).getQuantity());
                            double unitprise = ordersId.get(i).getOrderDetails().get(l).getUnitPrice();
                            double total = quantity * unitprise;
                            String tot = String.valueOf(total);
                            JFXButton Button = new JFXButton("");
                            String qty=ordersId.get(i).getOrderDetails().get(l).getQuantity();
                            String code = ordersId.get(i).getOrderDetails().get(l).getCode();
                            String unit= String.valueOf(ordersId.get(i).getOrderDetails().get(l).getUnitPrice());

                            table.add(new OrdersTM(code,dis,qty ,unit,tot,new JFXButton()));

                        }

                    }

                }


            }

        }
        tblOrders.setItems(table);

    }
}
