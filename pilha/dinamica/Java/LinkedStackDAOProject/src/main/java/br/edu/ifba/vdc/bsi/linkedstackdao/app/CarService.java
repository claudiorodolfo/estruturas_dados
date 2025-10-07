package br.edu.ifba.vdc.bsi.linkedstackdao.app;

import br.edu.ifba.vdc.bsi.linkedstackdao.dao.BookDAO;
import br.edu.ifba.vdc.bsi.linkedstackdao.dao.BookDAOLinkedStack;
import br.edu.ifba.vdc.bsi.linkedstackdao.model.Book;
import java.time.LocalDateTime;

public class CarService {

    public static CarDAO getDBCar() {
        return new CarDAOLinkedStack();
    }
    public static void main(String[] args) {
        CarDAO dbAcessor = CarService.getDBBook();

        Car c1 = new Car("ABC-1234", "Ford", "Fiesta", "Verde", "João", LocalDateTime.now());
        Car c2 = new Car("DEF-5678", "Chevrolet", "Onix", "Azul", "Maria", LocalDateTime.now());

        dbAcessor.addCar(c1);
        dbAcessor.addCar(c2);
        Car readCar = dbAcessor.getCar(null);
        System.out.println("Topo da pilha: " + readCar.getLicensePlate());

        Car deletedCar = dbAcessor.deleteCar(null);
        System.out.println("Desempilhei: " + deletedCar.getLicensePlate());
        System.out.println(deletedCar.toString());
        System.out.println(dbAcessor.printCars());
    }
}