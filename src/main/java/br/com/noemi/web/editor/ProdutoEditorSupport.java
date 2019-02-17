package br.com.noemi.web.editor;

import java.util.Collection;

import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import br.com.noemi.entity.Produto;
import br.com.noemi.service.ProdutoService;

public class ProdutoEditorSupport extends CustomCollectionEditor {

	private ProdutoService service;
	
	public ProdutoEditorSupport(Class<? extends Collection> collectionType, ProdutoService service) {
		super(collectionType);
		this.service = service;
	}

	@Override
	protected Object convertElement(Object element) {

		Long id = Long.valueOf((String) element);
		
		Produto produto = service.findById(id);
		
		return super.convertElement(produto);
	}

	
}
