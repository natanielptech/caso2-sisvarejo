package br.com.bompreco.sisvarejo.vendas.api;

import java.time.LocalDate;

public record SolicitacaoPreco(
        String produtoId, String lojaId, LocalDate data, int quantidade, boolean fidelidade) {
}
