package br.com.bompreco.sisvarejo.vendas.interno;

import br.com.bompreco.sisvarejo.precos.api.CalculoDePreco;
import br.com.bompreco.sisvarejo.vendas.api.ResultadoPreco;
import br.com.bompreco.sisvarejo.vendas.api.SolicitacaoPreco;
import org.springframework.stereotype.Component;

@Component
public class CalculadoraDePrecoModulo {
    private final CalculoDePreco calculoDePreco;

    public CalculadoraDePrecoModulo(CalculoDePreco calculoDePreco) {
        this.calculoDePreco = calculoDePreco;
    }

    public ResultadoPreco calcular(SolicitacaoPreco solicitacao) {
        return new ResultadoPreco(calculoDePreco.calcular(
                solicitacao.produtoId(),
                solicitacao.lojaId(),
                solicitacao.data(),
                solicitacao.quantidade(),
                solicitacao.fidelidade()));
    }
}
