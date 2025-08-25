package com.vootrancy.model.entities;

public class VooEconomico extends Voo {

    // Construtor que repassa todos os parâmetros para o construtor da classe pai (Voo)
    public VooEconomico(String codigo, String origem, String destino, String data,
                        double valorBase, Aviao aeronave, String portao, String duracao) {
        super(codigo, origem, destino, data, valorBase, aeronave, portao, duracao);
    }
    
    // Construtor vazio para Jackson, assim como na classe pai
    public VooEconomico() {
        super();
    }

    // Implementação do método abstrato da classe pai para definir a taxa específica
    @Override
    protected double aplicarTaxa(double valor) {
        // Exemplo de taxa: aumenta o valor em 10%
        return valor * 1.1; 
    }
    
    // Nota: O método calcularValorPassagem() da classe pai já se encarrega
    // de chamar este método e de retornar o preço final.
}