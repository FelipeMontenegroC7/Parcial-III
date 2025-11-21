package co.edu.uniquindio.poo.parcial_iii.Model;

public interface Subject {
    void addObserver(Observer observador);
    void removeObserver(Observer observador);
    void notifyObservers(String mensaje);
}
