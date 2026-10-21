package br.com.bompreco.sisvarejo.precos.dominio;

import java.time.LocalDate;
import java.util.Optional;

public interface TabelasDePrecoRepositorio {
    Optional<TabelaDePreco> buscarVigente(String produtoId, String lojaId, LocalDate data);
}
