package com.vootrancy.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vootrancy.exception.*;
import com.vootrancy.model.*;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PassagemService {

    private static final String FILE_PATH = "passagens.txt";
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Reservar passagem
    public Passagem reservarPassagem(Passageiro passageiro, Voo voo, String assento)
            throws AssentoInvalidoException, VooLotadoException, IOException {

        // Verifica e reserva poltrona no avião
        voo.getAeronave().reservarPoltrona(assento);

        // Calcula valor
        double valor = voo.calcularValorPassagem();

        // Cria passagem
        Passagem p = new Passagem(
                passageiro,
                voo.getCodigo(),
                voo.getOrigem(),
                voo.getDestino(),
                voo.getData(),
                voo.getPortaoEmbarque(),
                assento,
                valor,
                voo.getDuracao()
        );

        salvarPassagem(p);
        return p;
    }

    // Salvar passagem em arquivo
    public void salvarPassagem(Passagem p) throws IOException {
        List<Passagem> lista = listarPassagens();
        lista.add(p);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), lista);
    }

    // Listar passagens do arquivo
    public List<Passagem> listarPassagens() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                return objectMapper.readValue(file, new TypeReference<List<Passagem>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}