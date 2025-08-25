package com.vootrancy.service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vootrancy.model.entities.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DestinoService {

    private final String FILE_PATH = "destinos.txt"; // arquivo de persistência
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Destino> destinos;

    public DestinoService() {
        this.destinos = carregarDestinos();
    }

    // Adicionar destino
    public Destino adicionarDestino(Destino destino) {
        destinos.add(destino);
        salvarDestinos();
        return destino;
    }

    // Listar destinos
    public List<Destino> listarDestinos() {
        return destinos;
    }

    // Remover destino por sigla
    public boolean removerDestino(String sigla) {
        boolean removido = destinos.removeIf(d -> d.getSigla().equalsIgnoreCase(sigla));
        if (removido) {
            salvarDestinos();
        }
        return removido;
    }

    // Persistir lista no arquivo
    private void salvarDestinos() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), destinos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carregar lista do arquivo
    private List<Destino> carregarDestinos() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                return mapper.readValue(file, new TypeReference<List<Destino>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}