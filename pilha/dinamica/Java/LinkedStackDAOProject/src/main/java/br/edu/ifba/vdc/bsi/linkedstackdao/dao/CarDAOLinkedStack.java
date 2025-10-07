package br.edu.ifba.vdc.bsi.linkedstackdao.dao;

import br.edu.ifba.vdc.bsi.linkedstackdao.dao.repository.LinkedStack;
import br.edu.ifba.vdc.bsi.linkedstackdao.dao.repository.Stackable;
import br.edu.ifba.vdc.bsi.linkedstackdao.model.Car;

public class CarDAOLinkedStack implements CarDAO {

    private Stackable<Car> cars = new LinkedStack<>(20);

    @Override
    public void addCar(Car car) {
        cars.push(car);
    }
  
    @Override
    public Car getCar(Long id){
        return cars.peek();
    }

    @Override
    public void updateCar(Car car) {
        cars.update(car);
    }
    
    @Override
    public Car deleteCar(Long id) {
        return cars.pop();
    }

    @Override
    public Car[] sortCars() {
        return null;
    }
    
    @Override
    public Car[] getAllCars() {
        return null;
    }

    @Override
    public String printCars() {
        return cars.toString();
    }
}