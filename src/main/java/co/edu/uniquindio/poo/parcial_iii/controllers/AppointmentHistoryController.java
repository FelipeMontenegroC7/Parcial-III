package co.edu.uniquindio.poo.parcial_iii.controllers;

import co.edu.uniquindio.poo.parcial_iii.Model.Cita;
import co.edu.uniquindio.poo.parcial_iii.Model.Clinica;
import co.edu.uniquindio.poo.parcial_iii.Model.DataBase;
import co.edu.uniquindio.poo.parcial_iii.Model.Medico;
import co.edu.uniquindio.poo.parcial_iii.Model.Paciente;
import co.edu.uniquindio.poo.parcial_iii.Model.Utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AppointmentHistoryController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnCancelAppointment;

    @FXML
    private TableColumn<Cita, String> columnCost;

    @FXML
    private TableColumn<Cita, String> columnDate;

    @FXML
    private TableColumn<Cita, String> columnDescription;

    @FXML
    private TableColumn<Cita, String> columnDoctor;

    @FXML
    private TableColumn<Cita, String> columnStatus;

    @FXML
    private TableView<Cita> tableAppointments;

    private Paciente currentPaciente;
    private Medico currentMedico;

    @FXML
    void cancelAppointment(ActionEvent event) {
        Cita selectedCita = tableAppointments.getSelectionModel().getSelectedItem();
        if (selectedCita == null) {
            Utils.showAlert("WARNING", "Please select an appointment to cancel");
            return;
        }

        if (selectedCita.getEstadoCita().toString().equals("CANCELADA")) {
            Utils.showAlert("WARNING", "This appointment is already cancelled");
            return;
        }

        selectedCita.cancelar();
        loadAppointments();
        Utils.showAlert("VERIFIED", "Appointment cancelled successfully");
    }

    @FXML
    void goBack(ActionEvent event) {
        Object currentUser = Clinica.getInstance().getCurrentUser();
        if (currentUser instanceof Paciente) {
            Utils.replaceScene(event, "clienteDashboard.fxml", "Cliente Dashboard");
        } else if (currentUser instanceof Medico) {
            Utils.replaceScene(event, "medicoDashboard.fxml", "Medico Dashboard");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configure table columns
        columnDate.setCellValueFactory(cellData -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getFechaHora().format(formatter)
            );
        });

        columnDoctor.setCellValueFactory(cellData -> {
            Medico medico = cellData.getValue().getMedicoAsignado();
            String doctorName = medico.getNombre() + " " + medico.getApellido();
            return new javafx.beans.property.SimpleStringProperty(doctorName);
        });

        columnStatus.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getEstadoCita().toString()
            )
        );

        columnCost.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(
                "$" + String.format("%.0f", cellData.getValue().getCosto())
            )
        );

        columnDescription.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty("Medical Consultation")
        );

        // Get current user
        Object currentUser = Clinica.getInstance().getCurrentUser();
        if (currentUser instanceof Paciente) {
            currentPaciente = (Paciente) currentUser;
        } else if (currentUser instanceof Medico) {
            currentMedico = (Medico) currentUser;
        }

        loadAppointments();
    }

    private void loadAppointments() {
        DataBase db = Clinica.getInstance().getDataBase();
        ObservableList<Cita> appointments = FXCollections.observableArrayList();

        if (currentPaciente != null) {
            // Show patient's appointments
            appointments.addAll(
                db.getCitas().stream()
                    .filter(cita -> cita.getPacienteAsignado().equals(currentPaciente))
                    .collect(Collectors.toList())
            );
        } else if (currentMedico != null) {
            // Show doctor's appointments
            appointments.addAll(
                db.getCitas().stream()
                    .filter(cita -> cita.getMedicoAsignado().equals(currentMedico))
                    .collect(Collectors.toList())
            );
        }

        tableAppointments.setItems(appointments);
    }
}