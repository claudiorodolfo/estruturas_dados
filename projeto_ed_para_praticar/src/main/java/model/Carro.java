package model;

import java.time.LocalDateTime;

public class Carro {
    private final String placa;       // obrigatório, imutável
    private String marca;             // obrigatório
    private String modelo;
    private String cor;
    private String nomeProprietario;
    private LocalDateTime chegada;

    public Carro(String placa, String marca, String modelo) {
        obrigatorio(placa);
        obrigatorio(marca);
        this.modelo = modelo;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
    }

    public Carro(String placa, String marca, String modelo, String cor, String nomeProprietario, LocalDateTime chegada) {
        this(placa, marca, modelo);
        this.cor = cor;
        this.nomeProprietario = nomeProprietario;
        this.chegada = chegada;
    }

    private void obrigatorio(Object obj) {
        if (obj == null)
            throw new IllegalArgumentException("campo obrigatório não pode ser nulo!");
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }
    
    public String getCor() {
        return cor;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public LocalDateTime getChegada() {
        return chegada;
    }

    //Não tem setPlaca porque é imutável
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public void setChegada(LocalDateTime chegada) {
        this.chegada = chegada;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", cor='" + cor + '\'' +
                ", nomeProprietario='" + nomeProprietario + '\'' +
                ", chegada=" + chegada +
                '}';
    }
}