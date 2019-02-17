package br.com.noemi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.noemi.entity.ItemPedido;
import br.com.noemi.repository.ItemPedidoRepository;
import br.com.noemi.util.ReplaceString;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository repository;

	public Page<ItemPedido> findByPagination(int page, int size) {

		Pageable pageable = new PageRequest(page, size);

		return repository.findAll(pageable);
	}
	
	public List<ItemPedido> findAll() {
		Sort sort = new Sort(new Order(Direction.ASC, "quantidade"));
		return repository.findAll(sort);
	}

	// @Transactional(readOnly = false)
	// public void save(ItemPedido itemPedido) {
	//
	// //comentario.setDataComentario(LocalDateTime.now());
	// itemPedido.setQuantidade(quantidade);
	// repository.save(itemPedido);
	// }

	@Transactional(readOnly = false)
	public void saveOrUpdate(ItemPedido itemPedido) {

		String permalink = ReplaceString.formatarPermalink(itemPedido.getQuantidade());

		if (itemPedido.getId() != null) {
			ItemPedido persistente = repository.findOne(itemPedido.getId());
			persistente.setPermalink(permalink);
			persistente.setQuantidade(itemPedido.getQuantidade());
			persistente.setProduto(itemPedido.getProduto());
			persistente.setPedido(itemPedido.getPedido());
			repository.save(persistente);
		} else {
			itemPedido.setPermalink(permalink);
			repository.save(itemPedido);
		}
	}
}
