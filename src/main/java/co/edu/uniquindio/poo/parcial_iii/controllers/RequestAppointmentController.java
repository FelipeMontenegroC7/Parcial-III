package co.edu.uniquindio.poo.parcial_iii.controllers;

import co.edu.uniquindio.poo.parcial_iii.Model.Cita;
import co.edu.uniquindio.poo.parcial_iii.Model.Clinica;
import co.edu.uniquindio.poo.parcial_iii.Model.DataBase;
import co.edu.uniquindio.poo.parcial_iii.Model.Especialista;
import co.edu.uniquindio.poo.parcial_iii.Model.Medico;
import co.edu.uniquindio.poo.parcial_iii.Model.MedicoGeneral;
import co.edu.uniquindio.poo.parcial_iii.Model.Paciente;
import co.edu.uniquindio.poo.parcial_iii.Model.Utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class RequestAppointmentController implements Initializable {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnRequest;

    @FXML
    private ComboBox<String> comboMedicos;

    @FXML
    private ComboBox<String> comboUrgency;

    @FXML
    private DatePicker datePicker;

    @FXML
    private BorderPane rootPane;

    @FXML
    private TextArea txtDescription;

    private Paciente currentPaciente;

    @FXML
    void cancelRequest(ActionEvent event) {
        // Go back to cliente dashboard
        Utils.replaceScene(event, "clienteDashboard.fxml", "Cliente Dashboard");
    }

    @FXML
    void requestAppointment(ActionEvent event) {
        if (currentPaciente == null) {
            Utils.showAlert("ERROR", "No patient logged in");
            return;
        }

        String selectedMedicoName = comboMedicos.getValue();
        LocalDate selectedDate = datePicker.getValue();
        String description = txtDescription.getText();
        String urgency = comboUrgency.getValue();

        if (selectedMedicoName == null || selectedDate == null || description.isEmpty() || urgency == null) {
            Utils.showAlert("WARNING", "Please fill all fields");
            return;
        }

        // Find the selected medico
        DataBase db = Clinica.getInstance().getDataBase();
        Medico selectedMedico = null;

        for (MedicoGeneral mg : db.getMedicosgenerales()) {
            if ((mg.getNombre() + " " + mg.getApellido()).equals(selectedMedicoName)) {
                selectedMedico = mg;
                break;
            }
        }

        if (selectedMedico == null) {
            for (Especialista e : db.getEspecialistas()) {
                if ((e.getNombre() + " " + e.getApellido()).equals(selectedMedicoName)) {
                    selectedMedico = e;
                    break;
                }
            }
        }

        if (selectedMedico == null) {
            Utils.showAlert("ERROR", "Selected doctor not found");
            return;
        }

        // Create appointment (assuming 9 AM as default time)
        LocalDateTime appointmentDateTime = LocalDateTime.of(selectedDate, LocalTime.of(9, 0));
        double costo = calculateCost(urgency);

        Cita newCita = new Cita(currentPaciente, selectedMedico, appointmentDateTime, costo);
        db.getCitas().add(newCita);

        Utils.showAlert("VERIFIED", "Appointment requested successfully");
        cancelRequest(event);
    }

    private double calculateCost(String urgency) {
        return switch (urgency.toLowerCase()) {
            case "low" -> 50000.0;
            case "medium" -> 75000.0;
            case "high" -> 100000.0;
            case "emergency" -> 150000.0;
            default -> 50000.0;
        };
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Get current patient
        Object currentUser = Clinica.getInstance().getCurrentUser();
        if (currentUser instanceof Paciente) {
            currentPaciente = (Paciente) currentUser;
        }

        // Load medicos into combo box
        loadMedicos();

        // Set default urgency
        comboUrgency.getItems().addAll("Low", "Medium", "High", "Emergency");
        comboUrgency.setValue("Low");
    }

    private void loadMedicos() {
        DataBase db = Clinica.getInstance().getDataBase();

        for (MedicoGeneral mg : db.getMedicosgenerales()) {
            comboMedicos.getItems().add(mg.getNombre() + " " + mg.getApellido() + " (General)");
        }

        for (Especialista e : db.getEspecialistas()) {
            comboMedicos.getItems().add(e.getNombre() + " " + e.getApellido() + " (Specialist)");
        }
    }
}