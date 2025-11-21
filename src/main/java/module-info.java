module co.edu.uniquindio.poo.parcial_iii {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.mail;


    opens co.edu.uniquindio.poo.parcial_iii to javafx.fxml;
    exports co.edu.uniquindio.poo.parcial_iii;
    exports co.edu.uniquindio.poo.parcial_iii.Controllers;
    opens co.edu.uniquindio.poo.parcial_iii.Controllers to javafx.fxml;
    exports co.edu.uniquindio.poo.parcial_iii.App;
    opens co.edu.uniquindio.poo.parcial_iii.App to javafx.fxml;
}