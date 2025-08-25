package com.vootrancy.model.entities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Voo {
    protected String codigo;
    protected String origem; // Adicionado, já que a classe de destino tem
    protected String destino;
    protected Aviao aeronave;
    protected String data; // Adicionado para representar a data do voo
    protected String duracao; // Adicionado para a duração do voo
    protected double valorBase;
    protected String portaoEmbarque; // Adicionado para o portão de embarque
    
    // Construtor principal para inicializar os atributos
    public Voo(String codigo, String origem, String destino, String data,
               double valorBase, Aviao aeronave, String portao, String duracao) {
        this.codigo = codigo;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.valorBase = valorBase;
        this.aeronave = aeronave;
        this.portaoEmbarque = portao;
        this.duracao = duracao;
    }

    // Construtor vazio para Jackson/serialização
    public Voo() {}

    // ----------------------------------------
    // Métodos de Lógica e Preço
    // ----------------------------------------

    // Método final para calcular o valor total da passagem
    public final double calcularValorPassagem() {
        double valor = valorBase;
        valor = aplicarTaxa(valor);
        valor = aplicarExtras(valor);
        return valor;
    }

    // Método abstrato que as subclasses devem implementar para aplicar a taxa específica
    protected abstract double aplicarTaxa(double valor);

    // Método que pode ser sobrescrito por subclasses para aplicar extras (opcional)
    protected double aplicarExtras(double valor) {
        return valor;
    }

    // ----------------------------------------
    // Métodos Getters
    // ----------------------------------------

    public String getCodigo() {
        return codigo;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }
    
    public String getData() {
        return data;
    }

    public double getValorBase() {
        return valorBase;
    }
    
    public Aviao getAeronave() {
        return aeronave;
    }
    
    public String getPortaoEmbarque() {
        return portaoEmbarque;
    }
    
    public String getDuracao() {
        return duracao;
    }

    // ----------------------------------------
    // Persistência em arquivo (igual ao modelo)
    // ----------------------------------------
    
    private static final String FILE_PATH = "voo.txt";

    public static void salvarVoos(List<Voo> voos) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), voos);
    }

    public static List<Voo> carregarVoosDeArquivo(String caminho) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(caminho);
        if (!file.exists()) return new ArrayList<>();
        try {
            return mapper.readValue(file, new TypeReference<List<Voo>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}