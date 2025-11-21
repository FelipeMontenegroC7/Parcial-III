package co.edu.uniquindio.poo.parcial_iii.Model;

import java.time.LocalDateTime;

public class GestorCita {

    public Cita asignarCita(Paciente paciente, Medico medico, LocalDateTime fecha, double costo) {
        Cita cita = new Cita(paciente, medico, fecha, costo);

        DataBase.getInstance().getCitas().add(cita);

        paciente.update("Se le asigno una cita con el Doctor "+ medico.getNombre() + "\n con fecha/hora: "+fecha );

        return cita;
    }
}
