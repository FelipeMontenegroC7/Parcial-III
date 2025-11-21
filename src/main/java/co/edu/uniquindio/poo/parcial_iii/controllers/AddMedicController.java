package co.edu.uniquindio.poo.parcial_iii.controllers;

import co.edu.uniquindio.poo.parcial_iii.Model.Clinica;
import co.edu.uniquindio.poo.parcial_iii.Model.DataBase;
import co.edu.uniquindio.poo.parcial_iii.Model.MedicoGeneral;
import co.edu.uniquindio.poo.parcial_iii.Model.Utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AddMedicController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnCreateMedic;

    @FXML
    private BorderPane rootPane;

    @FXML
    private TextField txtEmailMedic;

    @FXML
    private TextField txtNameMedic;

    @FXML
    private TextField txtNumberMedic;

    @FXML
    private PasswordField txtPasswordMedic;

    @FXML
    void cancelCreate(ActionEvent event) {
        // Go back to clinica dashboard
        Utils.replaceScene(event, "clinicaDashboard.fxml", "Clinica Dashboard");
    }

    @FXML
    void createAdmin(ActionEvent event) {
        String nombre = txtNameMedic.getText();
        String telefono = txtNumberMedic.getText();
        String email = txtEmailMedic.getText();
        String contrasenia = txtPasswordMedic.getText();

        if (nombre.isEmpty() || telefono.isEmpty() || email.isEmpty() || contrasenia.isEmpty()) {
            Utils.showAlert("WARNING", "All fields are required");
            return;
        }

        // Assume apellido = "", usuario = nombre
        MedicoGeneral medico = new MedicoGeneral(nombre, "", nombre, contrasenia);
        medico.setTelefono(telefono);
        medico.setEmail(email);

        DataBase db = Clinica.getInstance().getDataBase();
        db.getMedicosgenerales().add(medico);

        Utils.showAlert("VERIFIED", "Medic created successfully");
        // Go back to clinica dashboard
        Utils.replaceScene(event, "clinicaDashboard.fxml", "Clinica Dashboard");
    }

}
