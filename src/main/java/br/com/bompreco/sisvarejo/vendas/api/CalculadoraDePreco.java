package br.com.bompreco.sisvarejo.vendas.api;

public interface CalculadoraDePreco {
    ResultadoPreco calcular(SolicitacaoPreco solicitacao);
}
