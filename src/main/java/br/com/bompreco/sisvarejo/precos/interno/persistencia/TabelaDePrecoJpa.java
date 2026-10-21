package br.com.bompreco.sisvarejo.precos.interno.persistencia;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tabela_de_preco", schema = "precos")
public class TabelaDePrecoJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String produtoId;
    private String lojaId;
    private LocalDate inicioVigencia;
    private LocalDate fimVigencia;
    private BigDecimal precoBase;
    private BigDecimal descontoPercentual;
    private BigDecimal descontoFidelidadePercentual;
    private BigDecimal descontoQuantidadePercentual;
    private int quantidadeMinima;

    protected TabelaDePrecoJpa() {
    }

    public TabelaDePrecoJpa(
            String produtoId,
            String lojaId,
            LocalDate inicioVigencia,
            LocalDate fimVigencia,
            BigDecimal precoBase,
            BigDecimal descontoPercentual,
            BigDecimal descontoFidelidadePercentual,
            BigDecimal descontoQuantidadePercentual,
            int quantidadeMinima) {
        this.produtoId = produtoId;
        this.lojaId = lojaId;
        this.inicioVigencia = inicioVigencia;
        this.fimVigencia = fimVigencia;
        this.precoBase = precoBase;
        this.descontoPercentual = descontoPercentual;
        this.descontoFidelidadePercentual = descontoFidelidadePercentual;
        this.descontoQuantidadePercentual = descontoQuantidadePercentual;
        this.quantidadeMinima = quantidadeMinima;
    }

    public String getProdutoId() {
        return produtoId;
    }

    public String getLojaId() {
        return lojaId;
    }

    public LocalDate getInicioVigencia() {
        return inicioVigencia;
    }

    public LocalDate getFimVigencia() {
        return fimVigencia;
    }

    public BigDecimal getPrecoBase() {
        return precoBase;
    }

    public BigDecimal getDescontoPercentual() {
        return descontoPercentual;
    }

    public BigDecimal getDescontoFidelidadePercentual() {
        return descontoFidelidadePercentual;
    }

    public BigDecimal getDescontoQuantidadePercentual() {
        return descontoQuantidadePercentual;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }
}
