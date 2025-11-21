package co.edu.uniquindio.poo.parcial_iii.controllers;

import co.edu.uniquindio.poo.parcial_iii.Model.Utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ClinicaDashboardController {

    @FXML
    private Button btnContratarMedico;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btngestionarMedicos;

    @FXML
    private Button btngestionarUsuarios;

    @FXML
    private AnchorPane mainContent;

    @FXML
    private AnchorPane menu;

    @FXML
    private Button menuButton;

    @FXML
    private ImageView profileImageView;

    @FXML
    private BorderPane root;

    @FXML
    void goToContratarMedicos(ActionEvent event) {
        Utils.replaceMainContent(mainContent, "addMedic.fxml");
    }

    @FXML
    void goToGestionarMedicos(ActionEvent event) {
        Utils.replaceMainContent(mainContent, "manegeMedics(Clinica).fxml");
    }

    @FXML
    void goToGestionarUsuarios(ActionEvent event) {
        Utils.replaceMainContent(mainContent, "manageUsers(Clinica).fxml");
    }

    @FXML
    void goToHome(ActionEvent event) {
        // Load home view, perhaps a welcome label
        mainContent.getChildren().clear();
        // For now, clear
    }

    @FXML
    void logOut(ActionEvent event) {
        Utils.replaceScene(event, "loginView.fxml", "Login");
    }

    @FXML
    void slideMenu(ActionEvent event) {
        // Toggle menu visibility
        menu.setVisible(!menu.isVisible());
    }

}
