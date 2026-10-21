package br.com.bompreco.sisvarejo.precos.interno.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface TabelaDePrecoJpaRepositorio extends JpaRepository<TabelaDePrecoJpa, Long> {
    Optional<TabelaDePrecoJpa> findFirstByProdutoIdAndLojaIdAndInicioVigenciaLessThanEqualAndFimVigenciaGreaterThanEqual(
            String produtoId, String lojaId, LocalDate dataInicio, LocalDate dataFim);
}
