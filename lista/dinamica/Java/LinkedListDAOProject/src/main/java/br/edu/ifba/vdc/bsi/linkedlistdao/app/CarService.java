package br.edu.ifba.vdc.bsi.linkedlistdao.app;

import br.edu.ifba.vdc.bsi.linkedlistdao.dao.CarDAO;
import br.edu.ifba.vdc.bsi.linkedlistdao.dao.CarDAOLinkedList;
import br.edu.ifba.vdc.bsi.linkedlistdao.model.Car;
import java.time.LocalDateTime;

public class CarService {

    public static CarDAO getDBCar() {
        return new CarDAOLinkedList();
    }
    
    void main() {
        CarDAO dbAcessor = CarService.getDBCar();

        Car c1 = new Car("ABC-1234", "Ford", "Fiesta", "Verde", "João", LocalDateTime.now());
        Car c2 = new Car("DEF-5678", "Chevrolet", "Onix", "Azul", "Maria", LocalDateTime.now());

        dbAcessor.addCar(c1);
        dbAcessor.addCar(c2);
        Car readCar = dbAcessor.getCar("ABC-1234");
        IO.println("Carro encontrado: " + readCar.getLicensePlate());

        Car deletedCar = dbAcessor.deleteCar("DEF-5678");
        IO.println("Carro removido: " + deletedCar.getLicensePlate());
        IO.println(deletedCar.toString());
        IO.println("Carros restantes: " + dbAcessor.printCars());
    }
}