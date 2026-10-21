package br.com.bompreco.sisvarejo.vendas.interno;

import br.com.bompreco.sisvarejo.vendas.api.ResultadoPreco;
import br.com.bompreco.sisvarejo.vendas.api.SolicitacaoPreco;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TesteDeCaracterizacaoDePrecos {
    @Autowired
    private CalculadoraDePrecoLegada legada;

    @Autowired
    private CalculadoraDePrecoModulo modulo;

    @ParameterizedTest
    @MethodSource("cenarios")
    void implementacoesPreservamOsPrecosObservaveis(
            SolicitacaoPreco solicitacao, BigDecimal precoEsperado) {
        ResultadoPreco precoLegado = legada.calcular(solicitacao);
        ResultadoPreco precoModulo = modulo.calcular(solicitacao);

        assertEquals(new ResultadoPreco(precoEsperado), precoLegado);
        assertEquals(precoLegado, precoModulo);
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> cenarios() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        new SolicitacaoPreco("PROD-001", "LOJA-001", LocalDate.of(2026, 1, 1), 1, false),
                        new BigDecimal("9.00")),
                org.junit.jupiter.params.provider.Arguments.of(
                        new SolicitacaoPreco("PROD-001", "LOJA-001", LocalDate.of(2026, 2, 1), 1, true),
                        new BigDecimal("8.50")),
                org.junit.jupiter.params.provider.Arguments.of(
                        new SolicitacaoPreco("PROD-001", "LOJA-001", LocalDate.of(2026, 3, 1), 5, true),
                        new BigDecimal("41.50")),
                org.junit.jupiter.params.provider.Arguments.of(
                        new SolicitacaoPreco("PROD-002", "LOJA-001", LocalDate.of(2026, 4, 1), 3, true),
                        new BigDecimal("73.44")),
                org.junit.jupiter.params.provider.Arguments.of(
                        new SolicitacaoPreco("PROD-001", "LOJA-002", LocalDate.of(2026, 5, 1), 2, true),
                        new BigDecimal("19.80")));
    }
}
