package br.com.noemi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.noemi.entity.Cliente;
import br.com.noemi.entity.Usuario;
import br.com.noemi.entity.UsuarioLogado;
import br.com.noemi.service.ClienteService;
import br.com.noemi.service.UsuarioService;

@Controller
@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
	public ModelAndView pageClientes(@PathVariable("page") Integer pagina, 
									   @ModelAttribute("cliente") Cliente value) {
		
		ModelAndView view = new ModelAndView("cliente/cadastro");
		
		Page<Cliente> page = service.findByPagination(pagina - 1, 5);
		
		view.addObject("page", page);
		view.addObject("urlPagination", "/cliente/page");
		
		return view;
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model) {
		
		Cliente cliente = service.findById(id);
		Usuario usuario = usuarioService.findById(id);
		
		Page<Cliente> page = service.findByPagination(0, 5);
		
		model.addAttribute("cliente", cliente);
		model.addAttribute("usuario", usuario);
		
		model.addAttribute("page", page);
		
		model.addAttribute("urlPagination", "/cliente/page");
		
		return new ModelAndView("cliente/cadastro", model);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return "redirect:/cliente/add";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("cliente") @Validated Cliente cliente, 
			                 BindingResult result, @AuthenticationPrincipal UsuarioLogado logado) {
		
		ModelAndView view = new ModelAndView();
		
		if (result.hasErrors()) {
			
			Page<Cliente> page = service.findByPagination(0, 5);
			view.addObject("page", page);
			view.addObject("urlPagination", "/cliente/page");
			view.setViewName("cliente/cadastro");
			
			return view;
		}
		
		 cliente.setUsuario(usuarioService.findById(logado.getId()));

		service.saveOrUpdate(cliente);
		
		view.setViewName("redirect:/cliente/add");
		
		return view;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView cadastro(@ModelAttribute("cliente") Cliente categoria) {
		ModelAndView view = new ModelAndView();
		
		Page<Cliente> page = service.findByPagination(0, 5);
		
		//view.addObject("cliente", service.findAll());
		view.addObject("page", page);
		
		view.addObject("urlPagination", "/cliente/page");
		
		view.setViewName("cliente/cadastro");
		
		return view;
	}
//	@Autowired
//	private ClienteService clienteService;
//	@Autowired
//	private UsuarioService usuarioService;
//	
//	@RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
//	public ModelAndView pageClientes(@PathVariable("page") Integer pagina) {
//		ModelAndView view = new ModelAndView("cliente/perfil");
//		
//		Page<Cliente> page = clienteService.findByPagination(pagina - 1, 5);
//		
//		view.addObject("page", page);
//		view.addObject("urlPagination", "/cliente/page");
//		
//		return view;
//	}
//	
//	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//	public String delete(@PathVariable("id") Long id) {
//		
//		clienteService.delete(id);
//		
//		return "redirect:/cliente/add";
//	}
//	
//	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
//	public ModelAndView preUpdate(@PathVariable("id") Long id) {
//		
//		ModelAndView view = new ModelAndView();
//		
//		Cliente autor = clienteService.findById(id);
//		
//		view.addObject("cliente", autor);
//		view.setViewName("cliente/cadastro");
//		
//		return view;
//	}
//		
//	@RequestMapping(value = {"/perfil/{id}", "/list"}, method = RequestMethod.GET)
//	public ModelAndView getCliente(@PathVariable("id") Optional<Long> id) {
//		
//		ModelAndView view = new ModelAndView("cliente/perfil");
//		
//		if (id.isPresent()) {
//			
//			Cliente cliente = clienteService.findById(id.get());		
//			view.addObject("clientes", Arrays.asList(cliente));
//		} else {
//			
//			//List<Autor> autores = autorService.findAll();
//			//view.addObject("autores", autores);
//			Page<Cliente> page = clienteService.findByPagination(0, 5);
//			view.addObject("page", page);
//			view.addObject("urlPagination", "/cliente/page");
//		}
//		
//		return view;				
//	}
//	
//	@RequestMapping(value = "/save", method = RequestMethod.POST)
//	public String save(@ModelAttribute("cliente") @Validated Cliente cliente, BindingResult result,
//			           @AuthenticationPrincipal UsuarioLogado logado) {
//		
//		if ( result.hasErrors() ) {
//			
//			return "cliente/cadastro";
//		}
//		
//		if (logado.getId() != null) {
//			Usuario usuario = usuarioService.findById(logado.getId());
//			cliente.setUsuario(usuario);
//		}
//		
//		clienteService.save(cliente);
//		
//		return "redirect:/cliente/perfil/" + cliente.getId();
//	}
//	
//	@RequestMapping(value = "/add", method = RequestMethod.GET)
//	public ModelAndView addCliente(@ModelAttribute("cliente") Cliente cliente, 
//								 @AuthenticationPrincipal() UsuarioLogado logado) { 
//		
//		cliente = clienteService.findByUsuario(logado.getId());
//		
//		if (cliente == null) {
//			
//			return new ModelAndView("cliente/cadastro");
//		}
//		
//		return new ModelAndView("redirect:/cliente/perfil/" + cliente.getId());
//	}
}
