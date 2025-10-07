package br.edu.ifba.vdc.bsi.linkedstackdao.model;

import java.time.LocalDateTime;

public class Car {
    private final String licensePlate;            // obrigatório, imutável
    private String mark;             // obrigatório
    private String model;
    private String color;
    private String ownerName;
    private LocalDateTime arrived;

    public Car(String licensePlate, String mark, String model) {
        required(licensePlate);
        required(mark);
        required(model);
        this.licensePlate = licensePlate;
        this.mark = mark;
        this.model = model;
    }

    public Car(String licensePlate, String mark, String model, String color, String ownerName, LocalDateTime arrived) {
        this(licensePlate, mark, model);
        this.color = color;
        this.ownerName = ownerName;
        this.arrived = arrived;
    }

    private void required(Object obj) {
        if (obj == null)
            throw new IllegalArgumentException("campo obrigatório não pode ser nulo!");
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }
    
    public String getColor() {
        return color;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public LocalDateTime getArrived() {
        return arrived;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setArrived(LocalDateTime arrived) {
        this.arrived = arrived;
    }

    @Override
    public String toString() {
        return "Car{" +
                "licensePlate='" + licensePlate + '\'' +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", arrived=" + arrived +
                '}';
    }
}