package co.edu.uniquindio.poo.parcial_iii.controllers;

import co.edu.uniquindio.poo.parcial_iii.Model.Clinica;
import co.edu.uniquindio.poo.parcial_iii.Model.DataBase;
import co.edu.uniquindio.poo.parcial_iii.Model.Medico;
import co.edu.uniquindio.poo.parcial_iii.Model.MedicoGeneral;
import co.edu.uniquindio.poo.parcial_iii.Model.Especialista;
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

import java.net.URL;
import java.util.ResourceBundle;

public class ManageMedicsController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnDeselect;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<Medico, String> colPasswordMedic;

    @FXML
    private TableColumn<Medico, String> colUserNameMedic;

    @FXML
    private TableColumn<Medico, String> columnEmailMedic;

    @FXML
    private TableColumn<Medico, String> columnIDMedic;

    @FXML
    private TableColumn<Medico, String> columnNameMedic;

    @FXML
    private TableView<Medico> tableMedicsView;

    @FXML
    void createMedic(ActionEvent event) {
        // Navigate to add medic view
        Utils.replaceScene(event, "addMedic.fxml", "Add Medic");
    }

    @FXML
    void deletemedic(ActionEvent event) {
        Medico selected = tableMedicsView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            DataBase db = Clinica.getInstance().getDataBase();
            if (selected instanceof MedicoGeneral) {
                db.getMedicosgenerales().remove(selected);
            } else if (selected instanceof Especialista) {
                db.getEspecialistas().remove(selected);
            }
            loadMedics();
        }
    }

    @FXML
    void deselect(ActionEvent event) {
        tableMedicsView.getSelectionModel().clearSelection();
    }

    @FXML
    void updateMedic(ActionEvent event) {
        Medico selectedMedic = tableMedicsView.getSelectionModel().getSelectedItem();
        if (selectedMedic == null) {
            Utils.showAlert("WARNING", "Please select a medic to update");
            return;
        }

        // Navigate to edit medic view and pass the selected medic
        EditMedicController controller = (EditMedicController) Utils.replaceScene(event, "editMedic.fxml", "Edit Medic");
        if (controller != null) {
            controller.setMedicToEdit(selectedMedic);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnIDMedic.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNameMedic.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnEmailMedic.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUserNameMedic.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colPasswordMedic.setCellValueFactory(new PropertyValueFactory<>("contrasenia"));

        loadMedics();
    }

    private void loadMedics() {
        DataBase db = Clinica.getInstance().getDataBase();
        ObservableList<Medico> medics = FXCollections.observableArrayList();
        medics.addAll(db.getMedicosgenerales());
        medics.addAll(db.getEspecialistas());
        tableMedicsView.setItems(medics);
    }

}
