package br.com.bompreco.sisvarejo.vendas.interno;

import br.com.bompreco.sisvarejo.vendas.api.ResultadoPreco;
import br.com.bompreco.sisvarejo.vendas.api.SolicitacaoPreco;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

@Component
public class CalculadoraDePrecoLegada {
    private static final LocalDate INICIO = LocalDate.of(2025, 1, 1);
    private static final LocalDate FIM = LocalDate.of(2030, 12, 31);
    private static final Map<String, DadosPreco> PRECOS = Map.of(
            "PROD-001:LOJA-001", new DadosPreco(new BigDecimal("10.00"), 10, 5, 2, 5),
            "PROD-002:LOJA-001", new DadosPreco(new BigDecimal("25.50"), 0, 3, 1, 3),
            "PROD-001:LOJA-002", new DadosPreco(new BigDecimal("11.00"), 5, 5, 0, 99));

    public ResultadoPreco calcular(SolicitacaoPreco solicitacao) {
        if (solicitacao.quantidade() <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        if (solicitacao.data().isBefore(INICIO) || solicitacao.data().isAfter(FIM)) {
            throw new IllegalArgumentException("Preço vigente não encontrado.");
        }
        DadosPreco dados = PRECOS.get(solicitacao.produtoId() + ":" + solicitacao.lojaId());
        if (dados == null) {
            throw new IllegalArgumentException("Preço vigente não encontrado.");
        }

        int desconto = dados.descontoPromocional;
        if (solicitacao.fidelidade()) {
            desconto += dados.descontoFidelidade;
        }
        if (solicitacao.quantidade() >= dados.quantidadeMinima) {
            desconto += dados.descontoQuantidade;
        }
        BigDecimal fator = BigDecimal.ONE.subtract(BigDecimal.valueOf(desconto).movePointLeft(2));
        BigDecimal preco = dados.precoBase.multiply(fator)
                .multiply(BigDecimal.valueOf(solicitacao.quantidade()))
                .setScale(2, RoundingMode.HALF_UP);
        return new ResultadoPreco(preco);
    }

    private record DadosPreco(
            BigDecimal precoBase,
            int descontoPromocional,
            int descontoFidelidade,
            int descontoQuantidade,
            int quantidadeMinima) {
    }
}
