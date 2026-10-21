package br.com.bompreco.sisvarejo.vendas.interno;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "sisvarejo.precos")
public class ConfiguracaoRolloutPrecos {
    @Min(0)
    @Max(100)
    private int rolloutPercentual;

    public int getRolloutPercentual() {
        return rolloutPercentual;
    }

    public void setRolloutPercentual(int rolloutPercentual) {
        this.rolloutPercentual = rolloutPercentual;
    }
}
