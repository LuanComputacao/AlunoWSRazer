package br.com.noemi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "iItemPedido")
public class ItemPedido extends AbstractAuditoria<Long> {
	// implements Comparable<Comentario>
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Length(min = 5, max = 255)
	@Column(nullable = false)
	private String quantidade;

	@ManyToOne(fetch = FetchType.EAGER)
	private Pedido pedido;

	@ManyToOne(fetch = FetchType.EAGER)
	private Produto produto;

	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuario;

	@Column(nullable = false, unique = true, length = 30)
	private String permalink;

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

}
