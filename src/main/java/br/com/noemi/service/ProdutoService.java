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

import br.com.noemi.entity.Produto;
import br.com.noemi.repository.ProdutoRepository;
import br.com.noemi.util.ReplaceString;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	public Page<Produto> findByPagination(int page, int size) {
		
		Pageable pageable = new PageRequest(page, size);
		
		return repository.findAllByOrderByDescricaoAsc(pageable);
	}
	
	public List<Produto> findAll() {
		Sort sort = new Sort(new Order(Direction.ASC, "descricao"));
		return repository.findAll(sort);
	}
	
	public Produto findByDescricao(String descricao) {
		
		return repository.findByDescricao(descricao);
	}
	
	public Produto findById(Long id) {
		
		return repository.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		
		repository.delete(id);
	}
	
	@Transactional(readOnly = false)
	public void saveOrUpdate(Produto produto) {
		
		String permalink = ReplaceString.formatarPermalink(produto.getDescricao());
		
		if (produto.getId() != null) {
			Produto persistente = repository.findOne(produto.getId());
			persistente.setPermalink(permalink);
			persistente.setDescricao(produto.getDescricao());
			repository.save(persistente);
		} else {
			produto.setPermalink(permalink);			
			repository.save(produto);
		}
	}
}
