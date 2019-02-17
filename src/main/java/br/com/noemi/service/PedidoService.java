package br.com.noemi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.noemi.entity.Pedido;
import br.com.noemi.repository.PedidoRepository;
import br.com.noemi.util.ReplaceString;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	// public Page<Pedido> findByTituloAndAutor(int page, int size, String titulo,
	// Long id) {
	// Pageable pageable = new PageRequest(page, size);
	//
	// return
	// repository.findAllByAutorIdAndTituloContainingIgnoreCaseOrderByDataPostagemDesc(pageable,
	// id, titulo);
	// }

	public Page<Pedido> findByPaginationByCliente(int page, int size, Long id) {
		Pageable pageable = new PageRequest(page, size);
		return repository
				.findAllByClienteIdOrderByDataPedidoDesc(pageable, id);
	}
	
	public Page<Pedido> findByClienteByNome(int page, int size, String texto) {

		return (Page<Pedido>) repository.findByClienteNome(texto, new PageRequest(page, size));
	}

	public Page<Pedido> findByPaginationByProdutos(int page, int size, String permalink) {
		Pageable pageable = new PageRequest(page, size);
		return repository.findAllByProdutosPermalinkOrderByDataPedidoDesc(pageable, permalink);
	}

	public Page<Pedido> findByPaginationByClientes(int page, int size, Long id) {
		Pageable pageable = new PageRequest(page, size);
		return repository.findAllByClienteIdOrderByDataPedidoDesc(pageable, id);
	}
	
	public Page<Pedido> findByPagination(int page, int size) {

		Pageable pageable = new PageRequest(page, size);
		return repository.findAllByOrderByDataPedidoDesc(pageable);
	}

	public List<Pedido> findAll() {

		return repository.findAll();
	}

	public List<Pedido> findByCliente(String nome) {

		return repository.findByClienteNome(nome);
	}

	// public List<Pedido> findByProdutos(String link) {
	//
	// return repository.findByProdutosPermalink(link);
	// }

	public Pedido findByPermalink(String permalink) {

		return repository.findByPermalink(permalink);
	}

	public Pedido findById(Long id) {

		return repository.findOne(id);
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {

		repository.delete(id);
	}

	@Transactional(readOnly = false)
	public void saveOrUpdate(Pedido pedido) {
		if (pedido.getId() == null) {
			save(pedido);
		} else {
			update(pedido);
		}
	}

	private void update(Pedido pedido) {

		Pedido persistente = repository.findOne(pedido.getId());

		if (!persistente.getDataPedido().equals(pedido.getDataPedido())) {
			persistente.setDataPedido(pedido.getDataPedido());
		}

		if (!persistente.getCliente().equals(pedido.getCliente())) {
			persistente.setCliente(pedido.getCliente());
		}
		
		if (persistente.getProdutos() != pedido.getProdutos()) {
			persistente.setProdutos(pedido.getProdutos());
		}

//		if (persistente.getIntensPedido() != pedido.getIntensPedido()) {
//			persistente.setIntensPedido(pedido.getIntensPedido());
//		}

		repository.save(persistente);
	}

	private void save(Pedido pedido) {

		String permalink = ReplaceString.formatarPermalink(pedido.getDataPedido().toString());
       //  List<Produto> produtos = new ArrayList<Produtos>();
		pedido.setPermalink(permalink);

		pedido.setDataPedido(LocalDateTime.now().toString());
		//pedido.setProdutos(produtos);

		repository.save(pedido);
	}

//public Page<Postagem> findByTitulo(int page, int size, String titulo) {
//		
//		Pageable pageable = new PageRequest(page, size);
//		
//		return repository
//				.findAllByTituloContainingIgnoreCaseOrderByDataPostagemDesc(pageable, titulo);
//	}
	public Page<Pedido> findByDataPedido(int page, int size, String dataPedido) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(page, size);
//		
		return repository.findByDataPedido(pageable,dataPedido);
	}

	public Page<Pedido> findByPaginationByCliente(int page, int size, String cpf, Long id) {
		Pageable pageable = new PageRequest(page, size);
		return repository.findAllByClienteCpfOrderByDataPedidoDesc(pageable,cpf, id);
	}
	
//	@Transactional(readOnly = false)
//	public void saveOrUpdate(Postagem postagem) {
//		if (postagem.getId() == null) {
//			save(postagem);
//		} else {
//			update(postagem);
//		}
//	}
//
//	private void update(Postagem postagem) {
//		
//		Postagem persistente = repository.findOne(postagem.getId());
//		
//		if (!persistente.getTitulo().equals(postagem.getTitulo())) {
//			persistente.setTitulo(postagem.getTitulo());
//		}
//		
//		if (!persistente.getTexto().equals(postagem.getTexto())) {
//			persistente.setTexto(postagem.getTexto());
//		}
//		
//		if (persistente.getCategorias() != postagem.getCategorias()) {
//			persistente.setCategorias(postagem.getCategorias());;
//		}
//		
//		repository.save(persistente);		
//	}
//
//	private void save(Postagem postagem) {
//		
//		String permalink = ReplaceString.formatarPermalink(postagem.getTitulo());
//		
//		postagem.setPermalink(permalink);
//		
//		postagem.setDataPostagem(LocalDateTime.now());
//		
//		repository.save(postagem);
//	}

}
