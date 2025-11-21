package co.edu.uniquindio.poo.parcial_iii.controllers;

import co.edu.uniquindio.poo.parcial_iii.Model.Clinica;
import co.edu.uniquindio.poo.parcial_iii.Model.DataBase;
import co.edu.uniquindio.poo.parcial_iii.Model.Paciente;
import co.edu.uniquindio.poo.parcial_iii.Model.Utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class EditUserController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnUpdateUser;

    @FXML
    private BorderPane rootPane;

    @FXML
    private TextField txtEmailUser;

    @FXML
    private TextField txtNameUser;

    @FXML
    private TextField txtPhoneUser;

    @FXML
    private TextField txtUserNameUser;

    private Paciente userToEdit;

    @FXML
    void cancelEdit(ActionEvent event) {
        // Go back to clinica dashboard
        Utils.replaceScene(event, "clinicaDashboard.fxml", "Clinica Dashboard");
    }

    @FXML
    void updateUser(ActionEvent event) {
        if (userToEdit == null) {
            Utils.showAlert("ERROR", "No user selected for editing");
            return;
        }

        String fullName = txtNameUser.getText().trim();
        String telefono = txtPhoneUser.getText();
        String email = txtEmailUser.getText();
        String usuario = txtUserNameUser.getText();

        if (fullName.isEmpty() || telefono.isEmpty() || email.isEmpty() || usuario.isEmpty()) {
            Utils.showAlert("WARNING", "All fields are required");
            return;
        }

        // Parse the full name into nombre and apellido
        String[] nameParts = fullName.split("\\s+", 2);
        String nombre = nameParts[0];
        String apellido = nameParts.length > 1 ? nameParts[1] : "";

        // Update the user's information
        userToEdit.setNombre(nombre);
        // Note: Paciente doesn't have apellido setter, so we skip that
        userToEdit.setEmail(email);
        userToEdit.setTelefono(telefono);
        // Note: usuario is not updated as it might be used for login

        Utils.showAlert("VERIFIED", "User updated successfully");
        cancelEdit(event); // Go back to clinica dashboard
    }

    public void setUserToEdit(Paciente user) {
        this.userToEdit = user;
        if (user != null) {
            txtNameUser.setText(user.getNombre());
            txtPhoneUser.setText(user.getTelefono());
            txtEmailUser.setText(user.getEmail());
            txtUserNameUser.setText(user.getUsuario());
        }
    }
}