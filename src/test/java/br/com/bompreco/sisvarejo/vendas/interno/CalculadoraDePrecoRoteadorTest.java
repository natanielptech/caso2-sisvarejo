package br.com.bompreco.sisvarejo.vendas.interno;

import br.com.bompreco.sisvarejo.vendas.api.ResultadoPreco;
import br.com.bompreco.sisvarejo.vendas.api.SolicitacaoPreco;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalculadoraDePrecoRoteadorTest {

    @Test
    void mantemResultadoLegadoSeModuloFalharEmModoSombra() {
        CalculadoraDePrecoLegada legada = mock(CalculadoraDePrecoLegada.class);
        CalculadoraDePrecoModulo modulo = mock(CalculadoraDePrecoModulo.class);
        ConfiguracaoRolloutPrecos configuracao = new ConfiguracaoRolloutPrecos();
        var solicitacao = new SolicitacaoPreco(
                "PROD-001", "LOJA-001", LocalDate.of(2026, 1, 1), 1, false);
        var resultadoLegado = new ResultadoPreco(new BigDecimal("9.00"));
        when(legada.calcular(solicitacao)).thenReturn(resultadoLegado);
        when(modulo.calcular(solicitacao)).thenThrow(new IllegalStateException("Módulo indisponível"));

        ResultadoPreco resultado = new CalculadoraDePrecoRoteador(legada, modulo, configuracao)
                .calcular(solicitacao);

        assertEquals(resultadoLegado, resultado);
    }
}
