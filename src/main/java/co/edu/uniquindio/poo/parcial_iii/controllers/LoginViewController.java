package co.edu.uniquindio.poo.parcial_iii.controllers;

import co.edu.uniquindio.poo.parcial_iii.Model.Clinica;
import co.edu.uniquindio.poo.parcial_iii.Model.DataBase;
import co.edu.uniquindio.poo.parcial_iii.Model.Medico;
import co.edu.uniquindio.poo.parcial_iii.Model.MedicoGeneral;
import co.edu.uniquindio.poo.parcial_iii.Model.Especialista;
import co.edu.uniquindio.poo.parcial_iii.Model.Paciente;
import co.edu.uniquindio.poo.parcial_iii.Model.Utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class LoginViewController {

    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink hyperLRegister;

    @FXML
    private BorderPane rootPane;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void goToRegister(ActionEvent event) {
        Utils.replaceScene(event, "registerView.fxml", "Register");
    }

    @FXML
    void login(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        DataBase db = Clinica.getInstance().getDataBase();

        // Check pacientes
        for (Paciente p : db.getPacientes()) {
            if (p.getUsuario().equals(username) && p.getContrasenia().equals(password)) {
                Clinica.getInstance().setCurrentUser(p);
                Utils.replaceScene(event, "clienteDashboard.fxml", "Cliente Dashboard");
                return;
            }
        }

        // Check medicos generales
        for (MedicoGeneral m : db.getMedicosgenerales()) {
            if (m.getUsuario().equals(username) && m.getContrasenia().equals(password)) {
                Clinica.getInstance().setCurrentUser(m);
                Utils.replaceScene(event, "medicoDashboard.fxml", "Medico Dashboard");
                return;
            }
        }

        // Check especialistas
        for (Especialista e : db.getEspecialistas()) {
            if (e.getUsuario().equals(username) && e.getContrasenia().equals(password)) {
                Clinica.getInstance().setCurrentUser(e);
                Utils.replaceScene(event, "medicoDashboard.fxml", "Medico Dashboard");
                return;
            }
        }

        // Check for clinic admin
        if ("clinica".equals(username) && "clinica123".equals(password)) {
            Clinica.getInstance().setCurrentUser("CLINICA_ADMIN"); // Special marker
            Utils.replaceScene(event, "clinicaDashboard.fxml", "Clinica Dashboard");
            return;
        }

        Utils.showAlert("ERROR", "Invalid credentials");
    }

}
