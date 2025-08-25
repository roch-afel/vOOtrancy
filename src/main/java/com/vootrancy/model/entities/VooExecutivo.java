package com.vootrancy.model.entities;

public class VooExecutivo extends Voo {

    // Construtor que repassa todos os parâmetros para o construtor da classe pai (Voo)
    public VooExecutivo(String codigo, String origem, String destino, String data,
                        double valorBase, Aviao aeronave, String portao, String duracao) {
        super(codigo, origem, destino, data, valorBase, aeronave, portao, duracao);
    }

    // Construtor vazio para Jackson, assim como na classe pai
    public VooExecutivo() {
        super();
    }

    // Implementa o método abstrato para definir a taxa específica do voo executivo
    @Override
    protected double aplicarTaxa(double valor) {
        // Exemplo de taxa: aumenta o valor em 30%
        return valor * 1.3;
    }

    // Sobrescreve o método da classe pai para adicionar um valor extra
    @Override
    protected double aplicarExtras(double valor) {
        // Exemplo de valor extra para serviços da executiva
        return valor + 500;
    }
}