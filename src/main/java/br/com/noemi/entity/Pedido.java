package br.com.noemi.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "pedido")
public class Pedido extends AbstractPersistable<Long> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false, unique = true, length = 60)
	private String permalink;
	
//	@DateTimeFormat(iso = ISO.DATE_TIME, pattern = "yyyy-MM-dd'T'HH:mm:ss") 
//	@Column(name = "dataPedido", nullable = false)
//	private LocalDateTime dataPedido;
	@Column(name = "dataPedido", nullable = false)
	private String dataPedido;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;	
	
	//@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "pedidos_has_produtos",	
		joinColumns = @JoinColumn(name = "pedido_id"),
		inverseJoinColumns = @JoinColumn(name = "produto_id")
	)
	private List<Produto> produtos;
	
//	@OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
//	private List<ItemPedido> intensPedido;

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}


//	public LocalDateTime getDataPedido() {
//		return dataPedido;
//	}
//
//	public void setDataPedido(LocalDateTime dataPedido) {
//		this.dataPedido = dataPedido;
//	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}

//	public List<ItemPedido> getIntensPedido() {
//		return intensPedido;
//	}
//
//	public void setIntensPedido(List<ItemPedido> intensPedido) {
//		this.intensPedido = intensPedido;
//	}
	
}
