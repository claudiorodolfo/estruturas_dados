package br.edu.ifba.vdc.bsi.linkedstackdao.dao;

import br.edu.ifba.vdc.bsi.linkedstackdao.dao.repository.LinkedStack;
import br.edu.ifba.vdc.bsi.linkedstackdao.dao.repository.Stackable;
import br.edu.ifba.vdc.bsi.linkedstackdao.model.Car;
import java.time.LocalDateTime;

public class CarDAOLinkedStack implements CarDAO {

    private Stackable<Car> cars = new LinkedStack<>(20);

    // Operações básicas CRUD
    @Override
    public void addCar(Car car) {
        cars.push(car);
    }
  
    @Override
    public Car getCar(String plateLicense) {
        Car[] allCars = getAllCars();
        for (Car car : allCars) {
            if (car.getLicensePlate().equals(plateLicense)) {
                return car;
            }
        }
        return null;
    }

    @Override
    public void updateCar(Car newCar) {
        // Para atualizar um carro específico, precisamos reconstruir a pilha
        Stackable<Car> tempStack = new LinkedStack<>(20);
        
        // Desempilhar todos os carros
        while (!cars.isEmpty()) {
            Car car = cars.pop();
            if (car.getLicensePlate().equals(newCar.getLicensePlate())) {
                tempStack.push(newCar);
            } else {
                tempStack.push(car);
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            cars.push(tempStack.pop());
        }
    }
    
    @Override
    public Car deleteCar(String plateLicense) {
        Stackable<Car> tempStack = new LinkedStack<>(20);
        Car deletedCar = null;
        
        // Desempilhar todos os carros
        while (!cars.isEmpty()) {
            Car car = cars.pop();
            if (car.getLicensePlate().equals(plateLicense)) {
                deletedCar = car;
            } else {
                tempStack.push(car);
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            cars.push(tempStack.pop());
        }
        
        return deletedCar;
    }
    
    // Operações de consulta específicas para carros
    @Override
    public Car[] getCarsByMark(String mark) {
        Stackable<Car> tempStack = new LinkedStack<>(20);
        Stackable<Car> resultStack = new LinkedStack<>(20);
        
        // Desempilhar todos os carros
        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempStack.push(car);
            if (car.getMark() != null && car.getMark().equalsIgnoreCase(mark)) {
                resultStack.push(car);
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            cars.push(tempStack.pop());
        }
        
        // Converter resultado para array
        Car[] result = new Car[countElements(resultStack)];
        int index = 0;
        while (!resultStack.isEmpty()) {
            result[index++] = resultStack.pop();
        }
        
        return result;
    }

    @Override
    public Car[] getCarsByModel(String model) {
        Stackable<Car> tempStack = new LinkedStack<>(20);
        Stackable<Car> resultStack = new LinkedStack<>(20);
        
        // Desempilhar todos os carros
        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempStack.push(car);
            if (car.getModel() != null && car.getModel().equalsIgnoreCase(model)) {
                resultStack.push(car);
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            cars.push(tempStack.pop());
        }
        
        // Converter resultado para array
        Car[] result = new Car[countElements(resultStack)];
        int index = 0;
        while (!resultStack.isEmpty()) {
            result[index++] = resultStack.pop();
        }
        
        return result;
    }
    
    // Operações de análise e estatísticas
    @Override
    public Car[] getCarsByMostRecentArrival(int limit) {
        Stackable<Car> tempStack = new LinkedStack<>(20);
        Stackable<Car> carsWithArrival = new LinkedStack<>(20);
        
        // Desempilhar todos os carros e filtrar os que têm data de chegada
        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempStack.push(car);
            if (car.getArrived() != null) {
                carsWithArrival.push(car);
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            cars.push(tempStack.pop());
        }
        
        // Ordenar por data de chegada (mais recentes primeiro) usando bubble sort
        Car[] carsArray = stackToArray(carsWithArrival);
        bubbleSortByArrivalDesc(carsArray);
        
        // Retornar apenas o limite solicitado
        int resultSize = Math.min(limit, carsArray.length);
        Car[] result = new Car[resultSize];
        for (int i = 0; i < resultSize; i++) {
            result[i] = carsArray[i];
        }
        
        return result;
    }

    @Override
    public Car[] getCarsByOldestArrival(int limit) {
        Stackable<Car> tempStack = new LinkedStack<>(20);
        Stackable<Car> carsWithArrival = new LinkedStack<>(20);
        
        // Desempilhar todos os carros e filtrar os que têm data de chegada
        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempStack.push(car);
            if (car.getArrived() != null) {
                carsWithArrival.push(car);
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            cars.push(tempStack.pop());
        }
        
        // Ordenar por data de chegada (mais antigos primeiro) usando bubble sort
        Car[] carsArray = stackToArray(carsWithArrival);
        bubbleSortByArrivalAsc(carsArray);
        
        // Retornar apenas o limite solicitado
        int resultSize = Math.min(limit, carsArray.length);
        Car[] result = new Car[resultSize];
        for (int i = 0; i < resultSize; i++) {
            result[i] = carsArray[i];
        }
        
        return result;
    }
    
    // Operações de ordenação específicas
    @Override
    public Car[] sortCarsByMark() {
        Car[] allCars = getAllCars();
        bubbleSortByMark(allCars);
        return allCars;
    }

    @Override
    public Car[] sortCarsByModel() {
        Car[] allCars = getAllCars();
        bubbleSortByModel(allCars);
        return allCars;
    }
    
    // Operações de relatório
    @Override
    public Car[] getAllCars() {
        Stackable<Car> tempStack = new LinkedStack<>(20);
        
        // Desempilhar todos os carros
        while (!cars.isEmpty()) {
            tempStack.push(cars.pop());
        }
        
        // Reempilhar na ordem original e criar array
        Car[] result = new Car[countElements(tempStack)];
        int index = 0;
        while (!tempStack.isEmpty()) {
            Car car = tempStack.pop();
            result[index++] = car;
            cars.push(car);
        }
        
        return result;
    }

    @Override
    public String printCars() {
        return cars.toString();
    }

    @Override
    public int getTotalCars() {
        return countElements(cars);
    }

    @Override
    public LocalDateTime getNewestArrival() {
        Stackable<Car> tempStack = new LinkedStack<>(20);
        LocalDateTime newest = null;
        
        // Desempilhar todos os carros
        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempStack.push(car);
            if (car.getArrived() != null) {
                if (newest == null || car.getArrived().isAfter(newest)) {
                    newest = car.getArrived();
                }
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            cars.push(tempStack.pop());
        }
        
        return newest;
    }
    
    // Métodos auxiliares usando apenas Stackable
    private int countElements(Stackable<Car> stack) {
        Stackable<Car> tempStack = new LinkedStack<>(20);
        int count = 0;
        
        while (!stack.isEmpty()) {
            tempStack.push(stack.pop());
            count++;
        }
        
        // Restaurar a pilha original
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
        
        return count;
    }
    
    private Car[] stackToArray(Stackable<Car> stack) {
        Stackable<Car> tempStack = new LinkedStack<>(20);
        Car[] array = new Car[countElements(stack)];
        int index = 0;
        
        while (!stack.isEmpty()) {
            Car car = stack.pop();
            array[index++] = car;
            tempStack.push(car);
        }
        
        // Restaurar a pilha original
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
        
        return array;
    }
    
    // Algoritmos de ordenação usando apenas arrays (sem List)
    private void bubbleSortByArrivalDesc(Car[] cars) {
        int n = cars.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (cars[j].getArrived().isBefore(cars[j + 1].getArrived())) {
                    Car temp = cars[j];
                    cars[j] = cars[j + 1];
                    cars[j + 1] = temp;
                }
            }
        }
    }
    
    private void bubbleSortByArrivalAsc(Car[] cars) {
        int n = cars.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (cars[j].getArrived().isAfter(cars[j + 1].getArrived())) {
                    Car temp = cars[j];
                    cars[j] = cars[j + 1];
                    cars[j + 1] = temp;
                }
            }
        }
    }
    
    private void bubbleSortByMark(Car[] cars) {
        int n = cars.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                String mark1 = cars[j].getMark() != null ? cars[j].getMark() : "";
                String mark2 = cars[j + 1].getMark() != null ? cars[j + 1].getMark() : "";
                if (mark1.compareToIgnoreCase(mark2) > 0) {
                    Car temp = cars[j];
                    cars[j] = cars[j + 1];
                    cars[j + 1] = temp;
                }
            }
        }
    }
    
    private void bubbleSortByModel(Car[] cars) {
        int n = cars.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                String model1 = cars[j].getModel() != null ? cars[j].getModel() : "";
                String model2 = cars[j + 1].getModel() != null ? cars[j + 1].getModel() : "";
                if (model1.compareToIgnoreCase(model2) > 0) {
                    Car temp = cars[j];
                    cars[j] = cars[j + 1];
                    cars[j + 1] = temp;
                }
            }
        }
    }
}