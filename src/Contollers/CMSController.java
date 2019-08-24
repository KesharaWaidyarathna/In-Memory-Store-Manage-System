package Contollers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class CMSController {
    public AnchorPane anpMain;


    public void imgCustomer_Action(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/FXML/ManageCustomer.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene mainScene = new Scene(root);
        Stage primarystage=(Stage)(this.anpMain.getScene().getWindow());
        primarystage.setScene(mainScene);
        primarystage.centerOnScreen();
    }

    public void icnManageItem_Action(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/FXML/ManageItem.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene mainScene = new Scene(root);
        Stage primarystage=(Stage)(this.anpMain.getScene().getWindow());
        primarystage.setScene(mainScene);
        primarystage.centerOnScreen();
    }

    public void icnPlaceOrder(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/FXML/PlaceOrder.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene mainScene = new Scene(root);
        Stage primarystage=(Stage)(this.anpMain.getScene().getWindow());
        primarystage.setScene(mainScene);
        primarystage.centerOnScreen();

    }

    public void imgSearch_Action(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/FXML/Search.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene mainScene = new Scene(root);
        Stage primarystage=(Stage)(this.anpMain.getScene().getWindow());
        primarystage.setScene(mainScene);
        primarystage.centerOnScreen();
    }
}
