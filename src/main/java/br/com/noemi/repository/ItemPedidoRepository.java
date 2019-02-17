package br.com.noemi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.noemi.entity.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository< ItemPedido, Long> {

}
