package com.vootrancy.controller;

import com.vootrancy.model.entities.Destino;
import com.vootrancy.service.DestinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destinos")
public class DestinoController {

    @Autowired
    private DestinoService destinoService;

    // Cadastrar um novo destino
    @PostMapping
    public Destino adicionarDestino(@RequestBody Destino destino) {
        return destinoService.adicionarDestino(destino);
    }

    // Listar todos os destinos
    @GetMapping
    public List<Destino> listarDestinos() {
        return destinoService.listarDestinos();
    }

    // Remover destino por sigla
    @DeleteMapping("/{sigla}")
    public String removerDestino(@PathVariable String sigla) {
        boolean removido = destinoService.removerDestino(sigla);
        return removido ? "Destino removido com sucesso." : "Destino não encontrado.";
    }
}