package br.com.noemi.web.validator;

import org.springframework.validation.BindingResult;

import br.com.noemi.entity.Pedido;

public class PedidoAjaxValidator {

	private Pedido pedido;
	
	private String status;
	
	private String tituloError;
	
	private String textoError;
	
	public void validar(BindingResult result) {
		
		if (result.hasFieldErrors("titulo")) {
			
			this.tituloError = result.getFieldError("titulo").getDefaultMessage();
		}
		
		if (result.hasFieldErrors("texto")) {
			
			this.textoError = result.getFieldError("texto").getDefaultMessage();
		}
	}

	public Pedido getPostagem() {
		return pedido;
	}

	public void setPostagem(Pedido pedido) {
		this.pedido = pedido;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTituloError() {
		return tituloError;
	}

	public void setTituloError(String tituloError) {
		this.tituloError = tituloError;
	}

	public String getTextoError() {
		return textoError;
	}

	public void setTextoError(String textoError) {
		this.textoError = textoError;
	}
	
	
}
