package br.com.bompreco.sisvarejo.vendas.interno;

import br.com.bompreco.sisvarejo.vendas.api.CalculadoraDePreco;
import br.com.bompreco.sisvarejo.vendas.api.ResultadoPreco;
import br.com.bompreco.sisvarejo.vendas.api.SolicitacaoPreco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/precos")
public class CalculadoraDePrecoController {
    private final CalculadoraDePreco calculadora;

    public CalculadoraDePrecoController(CalculadoraDePreco calculadora) {
        this.calculadora = calculadora;
    }

    @PostMapping("/calcular")
    public ResultadoPreco calcular(@Valid @RequestBody Requisicao request) {
        return calculadora.calcular(new SolicitacaoPreco(
                request.produtoId(), request.lojaId(), request.data(), request.quantidade(), request.fidelidade()));
    }

    public record Requisicao(
            @NotBlank String produtoId,
            @NotBlank String lojaId,
            @NotNull LocalDate data,
            @Positive int quantidade,
            boolean fidelidade) {
    }
}
