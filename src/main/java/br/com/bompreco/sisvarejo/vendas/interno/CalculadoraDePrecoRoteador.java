package br.com.bompreco.sisvarejo.vendas.interno;

import br.com.bompreco.sisvarejo.vendas.api.CalculadoraDePreco;
import br.com.bompreco.sisvarejo.vendas.api.ResultadoPreco;
import br.com.bompreco.sisvarejo.vendas.api.SolicitacaoPreco;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class CalculadoraDePrecoRoteador implements CalculadoraDePreco {
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculadoraDePrecoRoteador.class);
    private final CalculadoraDePrecoLegada legada;
    private final CalculadoraDePrecoModulo modulo;
    private final ConfiguracaoRolloutPrecos configuracao;

    public CalculadoraDePrecoRoteador(
            CalculadoraDePrecoLegada legada,
            CalculadoraDePrecoModulo modulo,
            ConfiguracaoRolloutPrecos configuracao) {
        this.legada = legada;
        this.modulo = modulo;
        this.configuracao = configuracao;
    }

    @Override
    public ResultadoPreco calcular(SolicitacaoPreco solicitacao) {
        int percentual = configuracao.getRolloutPercentual();
        if (percentual == 0) {
            ResultadoPreco resultadoLegado = legada.calcular(solicitacao);
            try {
                ResultadoPreco resultadoNovo = modulo.calcular(solicitacao);
                if (!resultadoLegado.equals(resultadoNovo)) {
                    LOGGER.warn("Divergência entre cálculo legado e módulo de preços: legado={}, módulo={}",
                            resultadoLegado, resultadoNovo);
                }
            } catch (RuntimeException exception) {
                LOGGER.warn("Falha no cálculo sombra do módulo de preços; mantendo resultado legado.",
                        exception);
            }
            return resultadoLegado;
        }
        return ThreadLocalRandom.current().nextInt(100) < percentual
                ? modulo.calcular(solicitacao)
                : legada.calcular(solicitacao);
    }
}
