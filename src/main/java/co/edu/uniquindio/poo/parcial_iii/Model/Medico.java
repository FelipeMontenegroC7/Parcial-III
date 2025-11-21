package co.edu.uniquindio.poo.parcial_iii.Model;

import javafx.util.Builder;

import java.util.ArrayList;
import java.util.UUID;

public abstract class Medico {
    private String nombre;
    private String apellido;
    private String id;
    private String usuario;
    private String contrasenia;
    private ArrayList<Paciente> pacientes;

    public Medico(String nombre, String apellido, String ususario, String contrasenia){
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = String.valueOf(UUID.randomUUID());
        this.usuario = ususario;
        this.contrasenia = contrasenia;
        this.pacientes = new ArrayList<>();
    }

    public String getNombre() {
        return
    }

    public String getApellido() {
        return apellido;
    }

    public String getId() {
        return id;
    }
}
