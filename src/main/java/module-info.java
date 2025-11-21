module co.edu.uniquindio.poo.parcial_iii {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.mail;


    exports co.edu.uniquindio.poo.parcial_iii.controllers;
    opens co.edu.uniquindio.poo.parcial_iii.controllers to javafx.fxml;
    exports co.edu.uniquindio.poo.parcial_iii.App;
    opens co.edu.uniquindio.poo.parcial_iii.App to javafx.fxml;
    exports co.edu.uniquindio.poo.parcial_iii.Model;
    exports co.edu.uniquindio.poo.parcial_iii.Model.Utils;
}