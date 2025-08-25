package com.vootrancy.controller;

import com.vootrancy.model.Passageiro;
import com.vootrancy.service.PassageiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passageiros")
public class PassageiroController {

    @Autowired
    private PassageiroService passageiroService;

    @PostMapping
    public Passageiro cadastrarPassageiro(@RequestBody Passageiro passageiro) {
        return passageiroService.cadastrarPassageiro(passageiro);
    }

    @GetMapping
    public List<Passageiro> listarPassageiros() {
        return passageiroService.listarPassageiros();
    }

    @DeleteMapping("/{documentoID}")
    public boolean removerPassageiro(@PathVariable String documentoID) {
        return passageiroService.removerPassageiro(documentoID);
    }
}
