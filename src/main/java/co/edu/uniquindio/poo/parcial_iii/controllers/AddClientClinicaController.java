package co.edu.uniquindio.poo.parcial_iii.controllers;

import co.edu.uniquindio.poo.parcial_iii.Model.Clinica;
import co.edu.uniquindio.poo.parcial_iii.Model.DataBase;
import co.edu.uniquindio.poo.parcial_iii.Model.Paciente;
import co.edu.uniquindio.poo.parcial_iii.Model.Utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AddClientClinicaController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnCreateUser;

    @FXML
    private BorderPane rootPane;

    @FXML
    private TextField txtEmailClient;

    @FXML
    private TextField txtIdClient;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPasswordClient;

    @FXML
    private TextField txtPhoneNumberClient;

    @FXML
    private TextField txtUserNameClient;

    @FXML
    void cancelCreate(ActionEvent event) {
        // Go back to clinica dashboard
        Utils.replaceScene(event, "clinicaDashboard.fxml", "Clinica Dashboard");
    }

    @FXML
    void createUser(ActionEvent event) {
        String id = txtIdClient.getText();
        String nombre = txtName.getText();
        String email = txtEmailClient.getText();
        String telefono = txtPhoneNumberClient.getText();
        String usuario = txtUserNameClient.getText();
        String contrasenia = Utils.hashPassword(txtPasswordClient.getText());

        if (id.isEmpty() || nombre.isEmpty() || email.isEmpty() || telefono.isEmpty() || usuario.isEmpty() || contrasenia.isEmpty()) {
            Utils.showAlert("WARNING", "All fields are required");
            return;
        }

        Paciente paciente = new Paciente.Builder()
                .id(id)
                .nombre(nombre)
                .email(email)
                .telefono(telefono)
                .usuario(usuario)
                .contrasenia(contrasenia)
                .build();

        DataBase db = Clinica.getInstance().getDataBase();
        db.getPacientes().add(paciente);

        Utils.showAlert("VERIFIED", "User created successfully");
        // Go back to clinica dashboard
        Utils.replaceScene(event, "clinicaDashboard.fxml", "Clinica Dashboard");
    }
}