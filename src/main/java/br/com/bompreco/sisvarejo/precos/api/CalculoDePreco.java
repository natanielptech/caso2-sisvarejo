package br.com.bompreco.sisvarejo.precos.api;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CalculoDePreco {
    BigDecimal calcular(String produtoId, String lojaId, LocalDate data, int quantidade, boolean fidelidade);
}
