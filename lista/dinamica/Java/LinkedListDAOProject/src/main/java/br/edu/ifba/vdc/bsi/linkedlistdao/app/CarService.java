package br.edu.ifba.vdc.bsi.linkeddequedao.app;

import br.edu.ifba.vdc.bsi.linkeddequedao.dao.CarDAO;
import br.edu.ifba.vdc.bsi.linkedlistdao.dao.CarDAOLinkedList;
import br.edu.ifba.vdc.bsi.linkeddequedao.model.Car;
import java.time.LocalDateTime;

public class CarService {

    public static CarDAO getDBCar() {
        return new CarDAOLinkedList();
    }
    
    public static void main(String[] args) {
        CarDAO dbAcessor = CarService.getDBCar();

        Car c1 = new Car("ABC-1234", "Ford", "Fiesta", "Verde", "João", LocalDateTime.now());
        Car c2 = new Car("DEF-5678", "Chevrolet", "Onix", "Azul", "Maria", LocalDateTime.now());

        dbAcessor.addCar(c1);
        dbAcessor.addCar(c2);
        Car readCar = dbAcessor.getCar("ABC-1234");
        System.out.println("Carro encontrado: " + readCar.getLicensePlate());

        Car deletedCar = dbAcessor.deleteCar("DEF-5678");
        System.out.println("Carro removido: " + deletedCar.getLicensePlate());
        System.out.println(deletedCar.toString());
        System.out.println("Carros restantes: " + dbAcessor.printCars());
    }
}