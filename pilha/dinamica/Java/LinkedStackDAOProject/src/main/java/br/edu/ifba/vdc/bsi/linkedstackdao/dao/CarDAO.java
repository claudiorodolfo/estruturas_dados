package br.edu.ifba.vdc.bsi.linkedstackdao.dao;

import br.edu.ifba.vdc.bsi.linkedstackdao.model.Car;
import java.time.LocalDateTime;

public interface CarDAO {
    // Operações básicas CRUD
    void addCar(Car car);
    Car getCar(String plateLicense);
    void updateCar(Car newCar);
    Car deleteCar(String plateLicense);
    
    // Operações de consulta específicas para carros
    Car[] getCarsByMark(String mark);
    Car[] getCarsByModel(String model);
    
    // Operações de análise e estatísticas
    Car[] getCarsByMostRecentArrival(int limit);
    Car[] getCarsByOldestArrival(int limit);
    
    // Operações de ordenação específicas
    Car[] sortCarsByMark();
    Car[] sortCarsByModel();
    
    // Operações de relatório
    Car[] getAllCars();
    String printCars();
    int getTotalCars();
    LocalDateTime getNewestArrival();
}