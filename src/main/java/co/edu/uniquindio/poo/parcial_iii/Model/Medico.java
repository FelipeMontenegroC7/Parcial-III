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
    private String telefono;
    private String email;
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
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
