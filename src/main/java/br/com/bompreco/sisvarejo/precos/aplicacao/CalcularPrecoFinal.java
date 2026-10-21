package br.com.bompreco.sisvarejo.precos.aplicacao;

import br.com.bompreco.sisvarejo.precos.api.CalculoDePreco;
import br.com.bompreco.sisvarejo.precos.dominio.TabelaDePreco;
import br.com.bompreco.sisvarejo.precos.dominio.TabelasDePrecoRepositorio;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CalcularPrecoFinal implements CalculoDePreco {
    private final TabelasDePrecoRepositorio repositorio;

    public CalcularPrecoFinal(TabelasDePrecoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public BigDecimal calcular(
            String produtoId, String lojaId, LocalDate data, int quantidade, boolean fidelidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        TabelaDePreco tabela = repositorio.buscarVigente(produtoId, lojaId, data)
                .orElseThrow(() -> new IllegalArgumentException("Preço vigente não encontrado."));
        return tabela.calcularPreco(quantidade, fidelidade);
    }
}
