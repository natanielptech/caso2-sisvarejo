package br.com.bompreco.sisvarejo.estoque.interno;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemEstoqueRepositorio extends JpaRepository<ItemEstoque, Long> {
}
