package co.edu.uniquindio.poo.parcial_iii.Model;

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
    
}
