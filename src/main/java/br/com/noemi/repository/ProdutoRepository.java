package br.com.noemi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.noemi.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Produto findByDescricao(String descricao);
	
	Page<Produto> findAllByOrderByDescricaoAsc(Pageable pageable);
}
