package co.edu.uniquindio.poo.parcial_iii.Model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cita implements Subject{
    private Paciente pacienteAsignado;
    private Medico medicoAsignado;
    private LocalDateTime fechaHora;
    private double costo;
    private EstadoCita estadoCita;
    private List<Observer> observers;

    public Cita(Paciente paciente, Medico medicoAsignado, LocalDateTime fechaHora, double costo){
        this.pacienteAsignado = paciente;
        this.medicoAsignado = medicoAsignado;
        this.fechaHora = fechaHora;
        this.costo = costo;
        this.estadoCita  = EstadoCita.PENDIENTE;
        this.observers = new ArrayList();

        addObserver(pacienteAsignado);
        notifyObservers("Se ha creado su cita exitosamente para " + fechaHora + "\n con el doctor: "+medicoAsignado.getNombre());

    }

    @Override
    public void addObserver(Observer observador) {
        observers.add(observador);
    }

    @Override
    public void removeObserver(Observer observador) {
        observers.remove(observador);
    }

    @Override
    public void notifyObservers(String mensaje) {
        for(Observer observer : observers){
            observer.update(mensaje);
        }
    }

     public void cambiarFechaHora(LocalDateTime nuevaFecha){
        this.fechaHora = nuevaFecha;
        notifyObservers("La fecha fue reasignada con exito para: " +nuevaFecha);
     }

     public void cancelar (){
        setEstadoCita(EstadoCita.CANCELADA);
        notifyObservers("La Cita fue cancelada");
     }



    public EstadoCita getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(EstadoCita estadoCita) {
        this.estadoCita = estadoCita;
    }

    public Paciente getPacienteAsignado() {
        return pacienteAsignado;
    }

    public void setPacienteAsignado(Paciente pacienteAsignado) {
        this.pacienteAsignado = pacienteAsignado;
    }

    public Medico getMedicoAsignado() {
        return medicoAsignado;
    }

    public void setMedicoAsignado(Medico medicoAsignado) {
        this.medicoAsignado = medicoAsignado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }
}
