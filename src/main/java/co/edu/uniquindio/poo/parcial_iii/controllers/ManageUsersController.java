package co.edu.uniquindio.poo.parcial_iii.controllers;

import co.edu.uniquindio.poo.parcial_iii.Model.Clinica;
import co.edu.uniquindio.poo.parcial_iii.Model.DataBase;
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

import java.net.URL;
import java.util.ResourceBundle;

public class ManageUsersController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnDeselect;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<Paciente, String> colUserNameUser;

    @FXML
    private TableColumn<Paciente, String> columnEmailUser;

    @FXML
    private TableColumn<Paciente, String> columnIDUser;

    @FXML
    private TableColumn<Paciente, String> columnNameUser;

    @FXML
    private TableColumn<Paciente, String> columnPhoneUser;

    @FXML
    private TableView<Paciente> tableUsersView;

    @FXML
    void createUser(ActionEvent event) {
        // Navigate to add user view
        Utils.replaceScene(event, "addClient(Clinica).fxml", "Add User");
    }

    @FXML
    void deleteUser(ActionEvent event) {
        Paciente selected = tableUsersView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            DataBase db = Clinica.getInstance().getDataBase();
            db.getPacientes().remove(selected);
            loadUsers();
        }
    }

    @FXML
    void deselect(ActionEvent event) {
        tableUsersView.getSelectionModel().clearSelection();
    }

    @FXML
    void updateUser(ActionEvent event) {
        Paciente selectedUser = tableUsersView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            Utils.showAlert("WARNING", "Please select a user to update");
            return;
        }

        // Navigate to edit user view and pass the selected user
        EditUserController controller = (EditUserController) Utils.replaceScene(event, "editUser.fxml", "Edit User");
        if (controller != null) {
            controller.setUserToEdit(selectedUser);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnIDUser.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNameUser.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnEmailUser.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnPhoneUser.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colUserNameUser.setCellValueFactory(new PropertyValueFactory<>("usuario"));

        loadUsers();
    }

    private void loadUsers() {
        DataBase db = Clinica.getInstance().getDataBase();
        ObservableList<Paciente> users = FXCollections.observableArrayList();
        users.addAll(db.getPacientes());
        tableUsersView.setItems(users);
    }
}