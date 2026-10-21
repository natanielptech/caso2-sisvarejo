package br.com.bompreco.sisvarejo.precos.interno.config;

import br.com.bompreco.sisvarejo.precos.aplicacao.CalcularPrecoFinal;
import br.com.bompreco.sisvarejo.precos.dominio.TabelasDePrecoRepositorio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracaoPrecos {

    @Bean
    CalcularPrecoFinal calcularPrecoFinal(TabelasDePrecoRepositorio repositorio) {
        return new CalcularPrecoFinal(repositorio);
    }
}
