package co.edu.uniquindio.poo.parcial_iii.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DataBase {
    private ArrayList<MedicoGeneral> medicosgenerales;
    private ArrayList<Especialista> especialistas;
    private ArrayList<Paciente> Pacientes;
    private ArrayList<Cita> Citas;
    private static DataBase instance;

    private DataBase(){
        this.medicosgenerales = new ArrayList<>();
        this.Pacientes = new ArrayList<>();
        this.especialistas = new ArrayList<>();
        this.Citas = new ArrayList<>();
        initializeData();
    }
    public static DataBase getInstance(){
        if(instance == null){
            instance = new DataBase();
        }
        return instance;
    }

    public ArrayList<Cita> getCitas() {
            return Citas;
    }

    public ArrayList<MedicoGeneral> getMedicosgenerales() {
        return medicosgenerales;
    }
    public ArrayList<Paciente> getPacientes() {
        return Pacientes;
    }

    public ArrayList<Especialista> getEspecialistas() {
        return especialistas;
    }

    private void initializeData() {
        // Initialize sample patients
        Paciente paciente1 = new Paciente.Builder()
                .id("123456789")
                .nombre("Juan Perez")
                .email("juan@example.com")
                .telefono("3001234567")
                .usuario("juanp")
                .contrasenia("123")
                .build();

        Paciente paciente2 = new Paciente.Builder()
                .id("987654321")
                .nombre("Maria Garcia")
                .email("maria@example.com")
                .telefono("3019876543")
                .usuario("mariag")
                .contrasenia("456")
                .build();

        Pacientes.add(paciente1);
        Pacientes.add(paciente2);

        // Initialize sample medicos
        MedicoGeneral medico1 = new MedicoGeneral("Dr. Carlos Rodriguez", "", "carlosr", "123");
        medico1.setTelefono("3105551234");
        medico1.setEmail("carlos@clinica.com");

        MedicoGeneral medico2 = new MedicoGeneral("Dra. Ana Lopez", "", "anal", "456");
        medico2.setTelefono("3115555678");
        medico2.setEmail("ana@clinica.com");

        medicosgenerales.add(medico1);
        medicosgenerales.add(medico2);

        // Initialize sample especialista
        Especialista especialista1 = new Especialista(TipoEspecialidad.CARDIOLOGIA, "Dr. Pedro Martinez", "", "pedrom", "789");
        especialista1.setTelefono("3125559012");
        especialista1.setEmail("pedro@clinica.com");

        especialistas.add(especialista1);

        // Initialize sample cita
        LocalDateTime fechaCita = LocalDateTime.now().plusDays(1);
        Cita cita1 = new Cita(paciente1, medico1, fechaCita, 50000.0);
        Citas.add(cita1);
    }
}
