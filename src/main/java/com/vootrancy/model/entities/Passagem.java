package com.vootrancy.model.entities;

public class Passagem {
    private Passageiro passageiro;
    private String codigoVoo;
    private String origem;
    private String destino;
    private String dataHora;
    private String portaoEmbarque;
    private String assento; // Adicionado para a poltrona
    private double valorFinal;
    private String duracao;

    // Construtor principal que recebe todas as informações como parâmetros
    public Passagem(Passageiro passageiro, String codigoVoo, String origem, String destino,
                    String dataHora, String portaoEmbarque, String assento,
                    double valorFinal, String duracao) {
        this.passageiro = passageiro;
        this.codigoVoo = codigoVoo;
        this.origem = origem;
        this.destino = destino;
        this.dataHora = dataHora;
        this.portaoEmbarque = portaoEmbarque;
        this.assento = assento;
        this.valorFinal = valorFinal;
        this.duracao = duracao;
    }

    // Construtor vazio para serialização (ex: com Jackson)
    public Passagem() {}

    // ----------------------------------------
    // Métodos Getters (apenas getters para imutabilidade)
    // ----------------------------------------
    
    public Passageiro getPassageiro() {
        return passageiro;
    }

    public String getCodigoVoo() {
        return codigoVoo;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public String getDataHora() {
        return dataHora;
    }

    public String getPortaoEmbarque() {
        return portaoEmbarque;
    }
    
    public String getAssento() {
        return assento;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public String getDuracao() {
        return duracao;
    }
}