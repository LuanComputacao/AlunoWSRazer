package br.com.noemi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.noemi.entity.Cliente;
import br.com.noemi.repository.ClienteRepository;
import br.com.noemi.util.ReplaceString;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public Page<Cliente> findByPagination(int page, int size) {
		Pageable pageable = new PageRequest(page, size);
		return repository.findAllByOrderByNomeAsc(pageable);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		
		repository.delete(id);		
	}
	
	@Transactional(readOnly = false)
	public void save(Cliente cliente) {
		
		if(cliente.getId() == null) {
			
			repository.save(cliente);
		} else {
			
			repository.updateNomeAndSobrenome(
					cliente.getNome(), cliente.getSobrenome(), cliente.getId()
			);
		}
		
	}
	@Transactional(readOnly = false)
	public void saveOrUpdate(Cliente cliente) {
		
		String permalink = ReplaceString.formatarPermalink(cliente.getNome());
		
		if (cliente.getId() != null) {
			Cliente persistente = repository.findOne(cliente.getId());
			persistente.setPermalink(permalink);
			persistente.setNome(cliente.getNome());
			persistente.setSobrenome(cliente.getNome());
			persistente.setCpf(cliente.getCpf());
			repository.save(persistente);
		} else {
			cliente.setPermalink(permalink);			
			repository.save(cliente);
		}
	}
	
	public List<Cliente> findAll() {
		
		return repository.findAll();
	}
	
	public Cliente findByNome(String nome) {
		
		return repository.findByNome(nome);
	}
	
	public Cliente findById(Long id) {
		
		return repository.findOne(id);
	}

	public Cliente findByUsuario(Long id) {
		
		return repository.findByUsuarioId(id);
	}
}
