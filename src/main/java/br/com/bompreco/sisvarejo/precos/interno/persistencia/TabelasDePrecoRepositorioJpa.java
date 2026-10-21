package br.com.bompreco.sisvarejo.precos.interno.persistencia;

import br.com.bompreco.sisvarejo.precos.dominio.Produto;
import br.com.bompreco.sisvarejo.precos.dominio.RegraDePromocao;
import br.com.bompreco.sisvarejo.precos.dominio.TabelaDePreco;
import br.com.bompreco.sisvarejo.precos.dominio.TabelasDePrecoRepositorio;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public class TabelasDePrecoRepositorioJpa implements TabelasDePrecoRepositorio {
    private final TabelaDePrecoJpaRepositorio repositorio;

    public TabelasDePrecoRepositorioJpa(TabelaDePrecoJpaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Optional<TabelaDePreco> buscarVigente(String produtoId, String lojaId, LocalDate data) {
        return repositorio.findFirstByProdutoIdAndLojaIdAndInicioVigenciaLessThanEqualAndFimVigenciaGreaterThanEqual(
                        produtoId, lojaId, data, data)
                .map(tabela -> new TabelaDePreco(
                        new Produto(tabela.getProdutoId()),
                        tabela.getLojaId(),
                        tabela.getInicioVigencia(),
                        tabela.getFimVigencia(),
                        tabela.getPrecoBase(),
                        new RegraDePromocao(
                                tabela.getDescontoPercentual(),
                                tabela.getDescontoFidelidadePercentual(),
                                tabela.getDescontoQuantidadePercentual(),
                                tabela.getQuantidadeMinima())));
    }
}
