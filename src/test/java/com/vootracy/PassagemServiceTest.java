package com.vootracy;

import com.vootrancy.exception.AssentoInvalidoException;
import com.vootrancy.exception.VooLotadoException;
import com.vootrancy.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PassagemServiceTest {

    @Test
    public void testarReservaPassagem() throws Exception {
        Aviao aviao = new Aviao("Airbus", "A320", Arrays.asList("1A", "1B", "1C"), 3);
        Voo voo = new VooEconomico("V001", "Aju", "SP", "2025-08-25", 500, aviao, "G5", "2h30m");
        Passageiro p = new Passageiro("12345678900", "Rafael", LocalDate.of(2000,1,1), "M");

        PassagemService service = new PassagemService();
        Passagem passagem = service.reservarPassagem(p, voo, "1A");

        assertEquals("1A", passagem.getAssento());
        assertEquals("Rafael", passagem.getPassageiro().getNome());
        assertEquals(1, voo.getAeronave().getTotalPassageiros());
    }

    @Test
    public void testarPoltronaInvalida() {
        Aviao aviao = new Aviao("Airbus", "A320", Arrays.asList("1A"), 1);
        Voo voo = new VooEconomico("V001", "Aju", "SP", "2025-08-25", 500, aviao, "G5", "2h30m");
        Passageiro p = new Passageiro("12345678900", "Rafael", LocalDate.of(2000,1,1), "M");

        PassagemService service = new PassagemService();

        assertThrows(AssentoInvalidoException.class, () -> service.reservarPassagem(p, voo, "2B"));
    }

    @Test
    public void testarVooLotado() throws Exception {
        Aviao aviao = new Aviao("Airbus", "A320", Arrays.asList("1A"), 1);
        Voo voo = new VooEconomico("V001", "Aju", "SP", "2025-08-25", 500, aviao, "G5", "2h30m");
        Passageiro p1 = new Passageiro("12345678900", "Rafael", LocalDate.of(2000,1,1), "M");
        Passageiro p2 = new Passageiro("98765432100", "Lucas", LocalDate.of(1998,5,5), "M");

        PassagemService service = new PassagemService();
        service.reservarPassagem(p1, voo, "1A");

        assertThrows(VooLotadoException.class, () -> service.reservarPassagem(p2, voo, "1A"));
    }
}