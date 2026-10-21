package br.com.bompreco.sisvarejo.precos.dominio;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TabelaDePreco {
    private final Produto produto;
    private final String lojaId;
    private final LocalDate inicioVigencia;
    private final LocalDate fimVigencia;
    private final BigDecimal precoBase;
    private final RegraDePromocao regraDePromocao;

    public TabelaDePreco(
            Produto produto,
            String lojaId,
            LocalDate inicioVigencia,
            LocalDate fimVigencia,
            BigDecimal precoBase,
            RegraDePromocao regraDePromocao) {
        this.produto = produto;
        this.lojaId = lojaId;
        this.inicioVigencia = inicioVigencia;
        this.fimVigencia = fimVigencia;
        this.precoBase = precoBase;
        this.regraDePromocao = regraDePromocao;
    }

    public boolean vigenteEm(LocalDate data) {
        return !data.isBefore(inicioVigencia) && !data.isAfter(fimVigencia);
    }

    public BigDecimal calcularPreco(int quantidade, boolean fidelidade) {
        return regraDePromocao.aplicar(precoBase, quantidade, fidelidade);
    }

    public Produto produto() {
        return produto;
    }

    public String lojaId() {
        return lojaId;
    }
}
