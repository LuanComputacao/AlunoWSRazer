package br.com.noemi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.noemi.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Page<Cliente> findAllByOrderByNomeAsc(Pageable pageable);
	
	Cliente findByNome(String nome);

	@Modifying
	@Query("update Cliente c set c.nome = ?1, c.sobrenome = ?2 where c.id = ?3")
	void updateNomeAndSobrenome(String nome, String sobrenome, Long id);

	Cliente findByUsuarioId(Long id);

}