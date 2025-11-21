package co.edu.uniquindio.poo.parcial_iii.Model;

import java.util.UUID;

public class Clinica {
    private UUID nit;
    private DataBase dataBase;
    private Object currentUser; // Paciente or Medico
    private static Clinica instance;

    private Clinica() {
        dataBase = DataBase.getInstance();
        nit = UUID.randomUUID();
    }
    public static Clinica getInstance() {
        if (instance == null) {
            instance = new Clinica();
        }
        return instance;
    }

    public Object getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Object currentUser) {
        this.currentUser = currentUser;
    }

    public DataBase getDataBase() {
        return dataBase;
    }
}
