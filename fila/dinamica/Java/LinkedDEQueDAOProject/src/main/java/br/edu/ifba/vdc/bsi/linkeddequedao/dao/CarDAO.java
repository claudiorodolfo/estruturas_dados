package br.edu.ifba.vdc.bsi.linkeddequedao.dao;

import br.edu.ifba.vdc.bsi.linkeddequedao.model.Car;
import java.time.LocalDateTime;

public interface CarDAO {
    // Operações básicas CRUD
    void addCar(Car car);
    Car getCar(String plateLicense);
    Car[] getAllCars();
    void updateCar(Car newCar);
    Car deleteCar(String plateLicense);
    void removeCarsByOwner(String owner);
    void removeCarsOlderThan(LocalDateTime date);

    // Operações de consulta específicas para carros
    Car getCarByLicensePlate(String licensePlate);
    Car[] getCarsByMark(String mark);
    Car[] getCarsByModel(String model);
    Car[] getCarsByColor(String color);
    Car[] getCarsByOwner(String owner);
    Car[] getCarsByMomentArrival(LocalDateTime initialMoment, LocalDateTime finalMoment);
    Car[] getCarsWithLongParking(long thresholdHours);
    long getAverageArrivalTime();

    // Operações de análise e estatísticas
    Car getCarByNewestArrival();
    Car getCarByOldestArrival();
    
    // Operações de relatório e estatísticas
    String printCars();
    int getTotalCars();
    String getMostPopularMark();
    String getMostPopularModel();
    String getMostPopularColor();
    long getParkingDuration(String plateLicense);
    Car[] getCarsByParkingDuration(long minHours, long maxHours);
    
    // Operações de gerenciamento
    boolean isCarInPlaced(String plateLicense);
    void clearAllCars();

    // Capacidade do estacionamento
    int getAvailableSpaces();
    int getOccupancyRate();
    boolean isParkingFull();
    boolean isParkingEmpty();
    int getMaxCapacity();
}