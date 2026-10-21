package br.com.bompreco.sisvarejo.precos.dominio;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RegraDePromocao {
    private final BigDecimal descontoPercentual;
    private final BigDecimal descontoFidelidadePercentual;
    private final BigDecimal descontoQuantidadePercentual;
    private final int quantidadeMinima;

    public RegraDePromocao(
            BigDecimal descontoPercentual,
            BigDecimal descontoFidelidadePercentual,
            BigDecimal descontoQuantidadePercentual,
            int quantidadeMinima) {
        this.descontoPercentual = descontoPercentual;
        this.descontoFidelidadePercentual = descontoFidelidadePercentual;
        this.descontoQuantidadePercentual = descontoQuantidadePercentual;
        this.quantidadeMinima = quantidadeMinima;
    }

    public BigDecimal aplicar(BigDecimal precoBase, int quantidade, boolean fidelidade) {
        BigDecimal desconto = descontoPercentual;
        if (fidelidade) {
            desconto = desconto.add(descontoFidelidadePercentual);
        }
        if (quantidade >= quantidadeMinima) {
            desconto = desconto.add(descontoQuantidadePercentual);
        }

        BigDecimal precoUnitario = precoBase.multiply(
                BigDecimal.ONE.subtract(desconto.movePointLeft(2)));
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
