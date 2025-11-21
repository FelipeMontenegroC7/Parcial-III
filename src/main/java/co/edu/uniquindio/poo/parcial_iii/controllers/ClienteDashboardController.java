package co.edu.uniquindio.poo.parcial_iii.controllers;

import co.edu.uniquindio.poo.parcial_iii.Model.Clinica;
import co.edu.uniquindio.poo.parcial_iii.Model.Paciente;
import co.edu.uniquindio.poo.parcial_iii.Model.Utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ClienteDashboardController implements Initializable {

    @FXML
    private Button btnHistorialCitas;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnSolicitarCita;

    @FXML
    private Label lblWelcomeClient;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set welcome message
        Object currentUser = Clinica.getInstance().getCurrentUser();
        if (currentUser instanceof Paciente) {
            Paciente paciente = (Paciente) currentUser;
            lblWelcomeClient.setText("Welcome, " + paciente.getNombre() + "!");
        }
    }

    @FXML
    void goToCitasAsignadas(ActionEvent event) {
        // This is the same as appointment history
        goToHistorialCitas(event);
    }

    @FXML
    void goToEditProfile(ActionEvent event) {
        // TODO: Implement profile editing
        Utils.showAlert("INFO", "Profile editing not implemented yet");
    }

    @FXML
    void goToHistorialCitas(ActionEvent event) {
        Utils.replaceMainContent(mainContent, "appointmentHistory.fxml");
    }

    @FXML
    void goToHome(ActionEvent event) {
        mainContent.getChildren().clear();
        // TODO: Add welcome content
    }

    @FXML
    void goToSolicitarCita(ActionEvent event) {
        Utils.replaceMainContent(mainContent, "requestAppointment.fxml");
    }

    @FXML
    void logOut(ActionEvent event) {
        Clinica.getInstance().setCurrentUser(null);
        Utils.replaceScene(event, "loginView.fxml", "Login");
    }

    @FXML
    void slideMenu(ActionEvent event) {
        menu.setVisible(!menu.isVisible());
    }

}
