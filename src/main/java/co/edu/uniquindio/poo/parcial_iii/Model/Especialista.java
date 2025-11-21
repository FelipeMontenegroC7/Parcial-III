package co.edu.uniquindio.poo.parcial_iii.Model;

public class Especialista extends Medico{
    private TipoEspecialidad especialidad;

    public Especialista(TipoEspecialidad especialidad, String nombre, String apellido, String usuario , String contrasenia) {
        super(nombre , apellido, usuario , contrasenia);
        this.especialidad = especialidad;
    }

}
