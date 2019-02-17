package br.com.noemi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.noemi.entity.Produto;
import br.com.noemi.service.ProdutoService;

@Controller
@RequestMapping("produto")
public class ProdutoController {

	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
	public ModelAndView pageProdutoss(@PathVariable("page") Integer pagina, 
									   @ModelAttribute("produto") Produto value) {
		
		ModelAndView view = new ModelAndView("produto/cadastro");
		
		Page<Produto> page = service.findByPagination(pagina - 1, 5);
		
		view.addObject("page", page);
		view.addObject("urlPagination", "/produto/page");
		
		return view;
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model) {
		
		Produto produto = service.findById(id);
		
		Page<Produto> page = service.findByPagination(0, 5);
		
		model.addAttribute("produto", produto);
		
		//model.addAttribute("categorias", service.findAll());
		
		model.addAttribute("page", page);
		
		model.addAttribute("urlPagination", "/produto/page");
		
		return new ModelAndView("produto/cadastro", model);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return "redirect:/produto/add";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("produto") @Validated Produto produto, 
			                 BindingResult result) {
		
		ModelAndView view = new ModelAndView();
		
		if (result.hasErrors()) {
			
			Page<Produto> page = service.findByPagination(0, 5);
			
			view.addObject("page", page);
			view.addObject("urlPagination", "/produto/page");
			view.setViewName("produto/cadastro");
			
			return view;
		}
		
		service.saveOrUpdate(produto);
		
		view.setViewName("redirect:/produto/add");
		
		return view;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView cadastro(@ModelAttribute("produto") Produto categoria) {
		ModelAndView view = new ModelAndView();
		
		Page<Produto> page = service.findByPagination(0, 5);
		
		//view.addObject("produto", service.findAll());
		view.addObject("page", page);
		
		view.addObject("urlPagination", "/produto/page");
		
		view.setViewName("produto/cadastro");
		
		return view;
	}
}
