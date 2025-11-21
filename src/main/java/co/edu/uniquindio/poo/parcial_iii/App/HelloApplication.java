package co.edu.uniquindio.poo.parcial_iii.App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/co/edu/uniquindio/poo/parcial_iii/loginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1117, 653);
        stage.setTitle("Clinica Descanso Eterno");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}