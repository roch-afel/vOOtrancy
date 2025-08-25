package com.vootrancy.model.entities;
import java.time.LocalDate;
import java.util.Objects; // Adicionado para usar o Objects.equals

public class Passageiro {
    private String cpf;
    private String nome;
    private int qtdeBagagens;
    private LocalDate nascimento;
    private String genero;

    // Construtor principal para inicializar os atributos
    public Passageiro(String cpf, String nome, LocalDate nascimento, String genero) {
        this.cpf = cpf;
        this.nome = nome;
        this.nascimento = nascimento;
        this.genero = genero;
        this.qtdeBagagens = 0; // Inicializa com 0, pois as bagagens são adicionadas depois
    }

    // Construtor vazio (sem argumentos) para ser utilizado por frameworks de serialização, como o Jackson
    public Passageiro() {}

    // ----------------------------------------
    // Métodos Getters e Setter
    // ----------------------------------------

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public int getQtdeBagagens() {
        return qtdeBagagens;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setQtdeBagagens(int qtde) {
        // Validação para garantir que a quantidade de bagagens não seja negativa
        if (qtde >= 0) {
            this.qtdeBagagens = qtde;
        }
    }

    // ----------------------------------------
    // Métodos utilitários
    // ----------------------------------------
    
    @Override
    public String toString() {
        return nome + " | Doc: " + cpf + " | Bagagens: " + qtdeBagagens;
    }
    
    // Sobrescreve o método equals para permitir comparação de objetos Passageiro
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Passageiro that = (Passageiro) obj;
        return Objects.equals(cpf, that.cpf);
    }
    
    // Sobrescreve o método hashCode para ser compatível com equals
    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}