package br.edu.ifba.vdc.bsi.linkedlistdao.dao;

import br.edu.ifba.vdc.bsi.linkedlistdao.dao.repository.list.LinkedList;
import br.edu.ifba.vdc.bsi.linkedlistdao.dao.repository.list.Listable;
import br.edu.ifba.vdc.bsi.linkedlistdao.model.Car;
import java.time.LocalDateTime;

public class CarDAOLinkedList implements CarDAO {

    private Listable<Car> cars = new LinkedList<>(20);

    // Operações básicas CRUD
    @Override
    public void addCar(Car car) {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public Car getCar(String plateLicense) {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public Car[] getAllCars() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public void updateCar(Car newCar) {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public Car deleteCar(String plateLicense) {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    // Operações de consulta específicas para carros
    @Override
    public Car getCarByLicensePlate(String licensePlate) {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public Car[] getCarsByMark(String mark) {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public Car[] getCarsByModel(String model) {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public Car[] getCarsByColor(String color) {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public Car[] getCarsByOwner(String owner) {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public Car[] getCarsByMomentArrival(LocalDateTime initialMoment, LocalDateTime finalMoment) {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    // Operações de análise e estatísticas
    @Override
    public Car getCarByNewestArrival() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public Car getCarByOldestArrival() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    // Operações de relatório e estatísticas
    @Override
    public String printCars() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public int getTotalCars() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public String getMostPopularMark() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public String getMostPopularModel() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public String getMostPopularColor() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    // Operações de gerenciamento
    @Override
    public boolean isCarInPlaced(String plateLicense) {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public void clearAllCars() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public void removeCarsOlderThan(LocalDateTime date) {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public Car[] getCarsByParkingDuration(long minHours, long maxHours) {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public int getAvailableSpaces() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public boolean isParkingEmpty() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public int getMaxCapacity() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public int getOccupancyRate() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public boolean isParkingFull() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public long getParkingDuration(String plateLicense) {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public void removeCarsByOwner(String owner) {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public long getAverageArrivalTime() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public Car[] getCarsWithLongParking(long thresholdHours) {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }
}