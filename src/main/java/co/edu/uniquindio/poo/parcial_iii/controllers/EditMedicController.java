package co.edu.uniquindio.poo.parcial_iii.controllers;

import co.edu.uniquindio.poo.parcial_iii.Model.Clinica;
import co.edu.uniquindio.poo.parcial_iii.Model.DataBase;
import co.edu.uniquindio.poo.parcial_iii.Model.Medico;
import co.edu.uniquindio.poo.parcial_iii.Model.Utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class EditMedicController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnUpdateMedic;

    @FXML
    private BorderPane rootPane;

    @FXML
    private TextField txtEmailMedic;

    @FXML
    private TextField txtNameMedic;

    @FXML
    private TextField txtNumberMedic;

    @FXML
    private TextField txtUserNameMedic;

    private Medico medicoToEdit;

    @FXML
    void cancelEdit(ActionEvent event) {
        // Go back to clinica dashboard
        Utils.replaceScene(event, "clinicaDashboard.fxml", "Clinica Dashboard");
    }

    @FXML
    void updateMedic(ActionEvent event) {
        if (medicoToEdit == null) {
            Utils.showAlert("ERROR", "No medic selected for editing");
            return;
        }

        String fullName = txtNameMedic.getText().trim();
        String telefono = txtNumberMedic.getText();
        String email = txtEmailMedic.getText();
        String usuario = txtUserNameMedic.getText();

        if (fullName.isEmpty() || telefono.isEmpty() || email.isEmpty() || usuario.isEmpty()) {
            Utils.showAlert("WARNING", "All fields are required");
            return;
        }

        // Parse the full name into nombre and apellido
        String[] nameParts = fullName.split("\\s+", 2);
        String nombre = nameParts[0];
        String apellido = nameParts.length > 1 ? nameParts[1] : "";

        // Update the medic's information
        medicoToEdit.setNombre(nombre);
        medicoToEdit.setApellido(apellido);
        medicoToEdit.setTelefono(telefono);
        medicoToEdit.setEmail(email);
        // Note: usuario is not updated as it might be used for login

        Utils.showAlert("VERIFIED", "Medic updated successfully");
        cancelEdit(event); // Go back to clinica dashboard
    }

    public void setMedicToEdit(Medico medico) {
        this.medicoToEdit = medico;
        if (medico != null) {
            txtNameMedic.setText(medico.getNombre() + " " + medico.getApellido());
            txtNumberMedic.setText(medico.getTelefono());
            txtEmailMedic.setText(medico.getEmail());
            txtUserNameMedic.setText(medico.getUsuario());
        }
    }
}