package dao;

import model.Carro;
import java.time.LocalDateTime;

public interface CarroDAO {

    // Operações básicas CRUD
    void addCarro(Carro carro);
    Carro getCarroPorPlaca(String placa);
    Carro[] getAllCarros();
    void updateCarro(Carro novoCarro);
    Carro deleteCarro(String placa);
    void removeCarrosPorProprietario(String proprietario);
    void removeCarrosMaisAntigosQue(LocalDateTime data);

    // Operações de consulta específicas para carros
    Carro[] getCarrosPorMarca(String marca);
    Carro[] getCarrosPorModelo(String modelo);
    Carro[] getCarrosPorCor(String cor);
    Carro[] getCarrosPorProprietario(String proprietario);
    Carro[] getCarrosPorMomentoChegada(LocalDateTime inicialMomento, LocalDateTime finalMomento);
    Carro[] getCarrosComEstacionamentoLongo(long limiteHoras);
    long getTempoMedioChegada();

    // Operações de análise e estatísticas
    Carro getCarroMaisNovo();
    Carro getCarroMaisAntigo();

    // Operações de relatório e estatísticas
    String printCarros();
    int getTotalCarros();
    String getMarcaMaisPopular();
    String getModeloMaisPopular();
    String getCorMaisPopular();
    long getTempoEstacionamento(String placa);
    Carro[] getCarrosPorTempoEstacionamento(long minHoras, long maxHoras);

    // Operações de gerenciamento
    boolean estaCarroEstacionado(String placa);
    void clearAllCarros();

    // Capacidade do estacionamento
    int getEspacosDisponiveis();
    int getOcupacao();
    boolean estaEstacionamentoCheio();
    boolean estaEstacionamentoVazio();
    int getMaximaCapacidade();
}