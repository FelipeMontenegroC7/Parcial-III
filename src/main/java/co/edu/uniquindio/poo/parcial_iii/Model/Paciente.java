package co.edu.uniquindio.poo.parcial_iii.Model;

import java.util.ArrayList;
import java.util.List;
//Builder

public class Paciente implements Observer{
    private String id;
    private String nombre;
    private String email;
    private String telefono;
    private String usuario;
    private String contrasenia;
    private List<Cita> citasPendientes = new ArrayList<Cita>();

    private Paciente(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.email = builder.email;
        this.telefono = builder.telefono;
        this.usuario = builder.usuario;
        this.contrasenia = builder.contrasenia;
    }

    public static class Builder {
            private String id;
            private String nombre;
            private String email;
            private String telefono;
            private String usuario;
            private String contrasenia;

            public Builder id(String id) {
                this.id = id;
                return this;
            }

            public Builder nombre(String nombre) {
                this.nombre = nombre;
                return this;
            }
            public Builder telefono(String telefono) {
                this.telefono = telefono;
                return this;
            }

            public Builder email(String email) {
                this.email = email;
                return this;
            }
            public Builder usuario(String usuario) {
                this.usuario = usuario;
                return this;
            }
            public Builder contrasenia(String contrasenia) {
                this.contrasenia = contrasenia;
                return this;
            }

            public Paciente build() {
                return new Paciente(this);
            }
    }

    @Override
    public void update(String mensaje) {
        EmailService.sendEmail(email, "Estado de tu cita", mensaje);
        System.out.println("[Notificacion] "+mensaje);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public List<Cita> getCitasPendientes() {
        return citasPendientes;
    }

    public void setCitasPendientes(List<Cita> citasPendientes) {
        this.citasPendientes = citasPendientes;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", usuario='" + usuario + '\'' +
                ", contrasenia='" + contrasenia +
                '}';
    }
}
