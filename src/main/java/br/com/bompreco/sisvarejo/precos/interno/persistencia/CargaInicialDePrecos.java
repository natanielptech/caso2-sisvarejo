package br.com.bompreco.sisvarejo.precos.interno.persistencia;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class CargaInicialDePrecos implements CommandLineRunner {
    private final TabelaDePrecoJpaRepositorio repositorio;

    public CargaInicialDePrecos(TabelaDePrecoJpaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void run(String... args) {
        if (repositorio.count() == 0) {
            repositorio.saveAll(List.of(
                    tabela("PROD-001", "LOJA-001", "10.00", "10", "5", "2", 5),
                    tabela("PROD-002", "LOJA-001", "25.50", "0", "3", "1", 3),
                    tabela("PROD-001", "LOJA-002", "11.00", "5", "5", "0", 99)));
        }
    }

    private TabelaDePrecoJpa tabela(
            String produtoId,
            String lojaId,
            String preco,
            String desconto,
            String descontoFidelidade,
            String descontoQuantidade,
            int quantidadeMinima) {
        return new TabelaDePrecoJpa(
                produtoId,
                lojaId,
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2030, 12, 31),
                new BigDecimal(preco),
                new BigDecimal(desconto),
                new BigDecimal(descontoFidelidade),
                new BigDecimal(descontoQuantidade),
                quantidadeMinima);
    }
}
