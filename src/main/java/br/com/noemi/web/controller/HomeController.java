package br.com.noemi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import br.com.noemi.entity.ItemPedido;
import br.com.noemi.entity.Pedido;
import br.com.noemi.service.PedidoService;

@Controller
public class HomeController {

	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value = "/search/texto/{texto}/page/{page}", method = RequestMethod.GET)
	public ModelAndView search(@PathVariable("texto") String nome, 
			                   @PathVariable("page") Integer pagina, ModelMap model) {
		
		Page<Pedido> page = pedidoService.findByClienteByNome(pagina - 1, 5, nome);
		model.addAttribute("page", page);
		model.addAttribute("urlPagination", "/search/nome/"+ nome +"/page");
		return new ModelAndView("posts", model);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(@RequestParam("texto") String texto, ModelMap model) {
		
		Page<Pedido> page = pedidoService.findByClienteByNome(0, 5, texto);
		model.addAttribute("page", page);
		model.addAttribute("urlPagination", "/search/nome/"+ texto +"/page");
		return new ModelAndView("posts", model);
	}
	
	@RequestMapping(value = "/{permalink}", method = RequestMethod.GET)
	public ModelAndView openPedido(
			@ModelAttribute("itemPedido") ItemPedido ItemPedido,
			@PathVariable("permalink") String permalink, ModelMap model) throws NoHandlerFoundException {
		
		Pedido pedido = pedidoService.findByPermalink(permalink);
		
		if (pedido == null) {
			throw new NoHandlerFoundException(null, null, null);
		}
		
		model.addAttribute("pedido", pedido);
		
		return new ModelAndView("posts", model);
	}
	
	@RequestMapping(value = "/cliente/{id}/page/{page}", method = RequestMethod.GET)
	public ModelAndView postsByCliente(
			@PathVariable("id") Long id, @PathVariable("page") Integer pagina, ModelMap model) {
		
		//List<Postagem> pedidos = postagemService.findByAutor(nome);		
		//model.addAttribute("pedidos", pedidos);
		
		Page<Pedido> page = pedidoService.findByPaginationByClientes(pagina - 1, 5, id);
				//findByPaginationByClientes(pagina - 1, 5, id);
		model.addAttribute("page", page);
		model.addAttribute("urlPagination", "/cliente/" + id + "/page");
		
		return new ModelAndView("posts", model);
	}
	
	@RequestMapping(value = "/produto/{link}/page/{page}", method = RequestMethod.GET)
	public ModelAndView postsByCategoria(
			@PathVariable("page") Integer pagina,
			@PathVariable("link") String link, ModelMap model) {
		
		//List<Postagem> postagens = postagemService.findByCategoria(link);		
		//model.addAttribute("postagens", postagens);
		
		 Page<Pedido> page = pedidoService.findByPaginationByProdutos(pagina - 1, 5, link);		
		 model.addAttribute("page", page);
		 model.addAttribute("urlPagination", "/produto/" + link + "/page");
		
		return new ModelAndView("posts", model);
	}
	
	@RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
	public ModelAndView pageHome(@PathVariable("page") Integer pagina, 
			                     ModelMap model) throws NoHandlerFoundException {
		
		Page<Pedido> page = pedidoService.findByPagination(pagina-1, 5);
		
		if (page.getContent().isEmpty()) {
			throw new NoHandlerFoundException(null, null, null);
		}
		
		model.addAttribute("page", page);
		model.addAttribute("urlPagination", "/page");
		return new ModelAndView("posts", model);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(ModelMap model) {
		
		//List<Postagem> postagens = postagemService.findAll();		
		//model.addAttribute("postagens", postagens);
		
        Page<Pedido> page = pedidoService.findByPagination(0, 5);		
		model.addAttribute("page", page);
		model.addAttribute("urlPagination", "/page");
		
		return new ModelAndView("posts", model);
	}
}
