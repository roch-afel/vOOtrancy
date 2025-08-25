package com.vootrancy.model.entities;
import java.util.List;

public class Aviao {
    private String nome;
    private String modelo;
    public List<String> poltronasLivres;
    public List<String> poltronasOcupadas;
    protected int maxLotacao;
    protected Short portao;
    

    public Aviao(String nome, String modelo, List<String> poltronasLivres, List<String> poltronasOcupadas, int lotacaoMaxima){
        this.nome = nome;
        this.modelo = modelo;
        this.poltronasLivres = poltronasLivres;
        this.poltronasOcupadas = poltronasOcupadas;
        this.maxLotacao = lotacaoMaxima;
    }

    public String getNome(){
        return nome;
    }
    public String getModelo(){
        return modelo;
    }
    public List<String> getPoltronasLivres(){
        return poltronasLivres;
    }
        public List<String> getPoltronasOcupadas(){
        return poltronasOcupadas;
    }
    public int getLotacao(){
        return maxLotacao;
    }

    // Se o voo for da classe executiva, tera algumas poltras para escolher, se nao for, tera outras
    // Adiciona passageiro a lista de poltronas ocupadas.
    public void setAddPassageiro(String poltrona){
        if (poltronasOcupadas.size() < maxLotacao && poltronasLivres.contains(poltrona)) {

            // Remove a poltrona da lista de poltronas livres
            poltronasLivres.remove(poltrona);

            // Adiciona a poltrona à lista de poltronas ocupadas
            poltronasOcupadas.add(poltrona);

            System.out.println("Passageiro adicionado à poltrona " + poltrona + ".");

        } else if (poltronasOcupadas.size() >= maxLotacao) {
            System.out.println("Erro: Voo está lotado. Não é possível adicionar mais passageiros.");
        } else {
            System.out.println("Erro: A poltrona " + poltrona + " não está disponível ou não existe.");
        }
    }

    public int getTotalPassageiros(){
        return poltronasOcupadas.size();
    }
}