package com.vootrancy.model.entities;
import java.util.ArrayList;
import java.util.List;

public class Aviao {
    private String nome;
    private String modelo;
    private List<String> poltronasLivres;
    private List<String> poltronasOcupadas;
    private int maxLotacao;
    private boolean classeExecutiva; // Novo atributo para a classe do voo

    public Aviao(String nome, String modelo, boolean classeExecutiva) {
        this.nome = nome;
        this.modelo = modelo;
        this.classeExecutiva = classeExecutiva;

        this.poltronasLivres = new ArrayList<>();
        this.poltronasOcupadas = new ArrayList<>();

        // Lógica para inicializar as poltronas com base na classe do voo
        if (this.classeExecutiva) {
            this.maxLotacao = 20; // Defina a lotação para voos executivos
            inicializarPoltronasExecutivas();
        } else {
            this.maxLotacao = 50; // Defina a lotação para voos normais
            inicializarPoltronasEconomicas();
        }
    }
    
    // Método auxiliar para inicializar as poltronas da classe executiva
    private void inicializarPoltronasExecutivas() {
        for (int i = 1; i <= maxLotacao; i++) {
            poltronasLivres.add("E" + i); // Poltronas da executiva, e.g., "E1", "E2"
        }
    }

    // Método auxiliar para inicializar as poltronas da classe econômica
    private void inicializarPoltronasEconomicas() {
        for (int i = 1; i <= maxLotacao; i++) {
            poltronasLivres.add("C" + i); // Poltronas da econômica, e.g., "C1", "C2"
        }
    }

    public String getNome() {
        return nome;
    }

    public String getModelo() {
        return modelo;
    }

    public List<String> getPoltronasLivres() {
        return poltronasLivres;
    }

    public List<String> getPoltronasOcupadas() {
        return poltronasOcupadas;
    }

    public int getMaxLotacao() {
        return maxLotacao;
    }
    
    public boolean isClasseExecutiva() {
        return classeExecutiva;
    }

    public void reservarPoltrona(String poltrona) {
        if (poltronasLivres.contains(poltrona)) {
            poltronasLivres.remove(poltrona);
            poltronasOcupadas.add(poltrona);
            System.out.println("Passageiro adicionado à poltrona " + poltrona + ".");
        } else {
            System.out.println("Erro: A poltrona " + poltrona + " não está disponível ou não existe.");
        }
    }

    public int getTotalPassageiros() {
        return poltronasOcupadas.size();
    }
}