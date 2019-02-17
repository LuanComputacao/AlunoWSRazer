package br.com.noemi.entity;

import java.util.List;

import javax.persistence.*;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "cliente")
public class Cliente extends AbstractPersistable<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Length(min = 3, max = 50)
	@Column(nullable = false, length = 50)
	private String nome;
	@NotBlank
	@Length(min = 3, max = 50)
	@Column(nullable = false, length = 50)
	private String sobrenome;
	@NotBlank(message = "Este campo não aceita valor em branco.")
	@Length(min = 5, max = 255, message = "Este campo aceita 11 numeros.")
	@Column(nullable = false, unique= true, length = 11)
	private String cpf;
	@Column(nullable = false, unique = true, length = 30)
	private String permalink;
	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;
	
	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	

}
