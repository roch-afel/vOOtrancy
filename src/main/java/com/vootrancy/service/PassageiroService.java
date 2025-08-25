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
public class PassageiroService {

    private final String FILE_PATH = "passageiros.txt"; // arquivo de persistência
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Passageiro> passageiros;

    public PassageiroService() {
        this.passageiros = carregarPassageiros();
    }

    // Cadastrar passageiro
    public Passageiro cadastrarPassageiro(Passageiro passageiro) {
        passageiros.add(passageiro);
        salvarPassageiros();
        return passageiro;
    }

    // Listar passageiros
    public List<Passageiro> listarPassageiros() {
        return passageiros;
    }

    // Remover passageiro por documento (usando equals para evitar erro de comparação)
    public boolean removerPassageiro(String documentoID) {
        boolean removido = passageiros.removeIf(p -> String.valueOf(p.getCpf()).equalsIgnoreCase(documentoID));
        if (removido) {
            salvarPassageiros();
        }
        return removido;
    }

    // Persistir lista no arquivo
    private void salvarPassageiros() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), passageiros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carregar lista do arquivo
    private List<Passageiro> carregarPassageiros() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                return mapper.readValue(file, new TypeReference<List<Passageiro>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}