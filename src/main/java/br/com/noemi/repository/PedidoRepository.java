package br.com.noemi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.noemi.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	Page<Pedido> findAllByOrderByDataPedidoDesc(Pageable pageable);
	
	Pedido findByPermalink(String permalink);

	//List<Pedido> findByCategoriasPermalink(String link);
	List<Pedido> findByClienteNome(String nome);

	List<Pedido> findByClienteNome(String nome,Pageable pageable);

	Page<Pedido> findAllByProdutosPermalinkOrderByDataPedidoDesc(Pageable pageable,  String permalink );

	Page<Pedido> findAllByClienteIdOrderByDataPedidoDesc(Pageable pageable,Long id);

	Page<Pedido> findByDataPedido(Pageable pageable, String dataPedido);

	Page<Pedido> findAllByClienteCpfOrderByDataPedidoDesc(Pageable pageable, String cpf, Long id);
	
//	Page<Pedido> findAllByTituloContainingIgnoreCaseOrderByDataPostagemDesc(Pageable pageable, String titulo);

	///Page<Pedido> findAllByAutorIdAndTituloContainingIgnoreCaseOrderByDataPostagemDesc(Pageable pageable, Long id,
//			String titulo);
 
}
