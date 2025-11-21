package co.edu.uniquindio.poo.parcial_iii.Model;

public class MedicoFactory {

    public static Medico createMedico(boolean tipo,
            String nombre,
            String apellido,
            String usuario,
            String contraseña,
            TipoEspecialidad especialidad){
        if(tipo){
            return new Especialista(especialidad, nombre, apellido, usuario, contraseña);
        }
        return new MedicoGeneral(nombre, apellido, usuario, contraseña);
    }
}
