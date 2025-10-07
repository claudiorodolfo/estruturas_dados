package br.edu.ifba.vdc.bsi.linkedstackdao.dao;

import br.edu.ifba.vdc.bsi.linkedstackdao.model.Car;

public interface BookDAO {
    // cria um novo carro
    void addCar(Car car);
    // retorna um carro
    Car getCar(String plateLicense);
    // atualiza um carro
    void updateCar(Car newCar);
    // apaga um carro
    Car deleteCar(String plateLicense);

    // ordena os carros
    Car[] sortCars();
    // retorna todos os carros
    Car[] getAllCars();
    //imprime os carros armazenados
    String printBooks();
}