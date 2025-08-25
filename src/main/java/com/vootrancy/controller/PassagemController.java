package com.vootrancy.controller;

import com.vootrancy.trancy.model.Passageiro;
import com.vootrancy.trancy.model.Voo;
import com.vootrancy.trancy.service.PassageiroService;
import com.vootrancy.trancy.service.PassagemService;
import com.vootrancy.trancy.service.DestinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/passagens")
public class PassagemController {

    @Autowired
    private PassagemService passagemService;

    @Autowired
    private PassageiroService passageiroService;

    @Autowired
    private DestinoService destinoService;

    // Exibir tela de cadastro/listagem de passagens
    @GetMapping
    public String formCadastro(Model model) {
        model.addAttribute("passageiros", passageiroService.listarPassageiros());
        model.addAttribute("destinos", destinoService.listarDestinos());
        model.addAttribute("passagens", passagemService.listarPassagens());
        return "passagens"; // templates/passagens.html
    }

    // Reservar passagem
    @PostMapping("/reservar")
    public String reservar(@RequestParam String documentoID,
                           @RequestParam String codigoVoo,
                           @RequestParam String assento,
                           Model model) {
        try {
            // Recupera passageiro pelo documento
            Passageiro passageiro = passageiroService.listarPassageiros()
                    .stream()
                    .filter(p -> p.getCpf().equalsIgnoreCase(documentoID))
                    .findFirst()
                    .orElse(null);

            // Recupera voo a partir de arquivo de voos (mock simplificado aqui)
            List<Voo> voos = Voo.carregarVoosDeArquivo("voo.txt");
            Voo voo = voos.stream()
                    .filter(v -> v.getCodigo().equalsIgnoreCase(codigoVoo))
                    .findFirst()
                    .orElse(null);

            if (passageiro != null && voo != null) {
                passagemService.reservarPassagem(passageiro, voo, assento);
            } else {
                model.addAttribute("erro", "Passageiro ou voo não encontrado!");
            }

        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
        }

        return "redirect:/passagens";
    }
}