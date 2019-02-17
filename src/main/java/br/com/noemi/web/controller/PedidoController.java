package br.com.noemi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.noemi.entity.Pedido;
import br.com.noemi.entity.UsuarioLogado;
import br.com.noemi.service.ClienteService;
import br.com.noemi.service.PedidoService;
import br.com.noemi.service.ProdutoService;
import br.com.noemi.web.editor.ProdutoEditorSupport;
import br.com.noemi.web.validator.PedidoAjaxValidator;

@Controller
@RequestMapping("pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ClienteService clienteService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(List.class, new ProdutoEditorSupport(List.class, produtoService));
	}

	@RequestMapping(value = "/ajax/dataPedido/{dataPedido}/page/{page}", method = RequestMethod.GET) 
	public ModelAndView searchByAjax(@PathVariable("dataPedido") String dataPedido,
									 @PathVariable("page") Integer pagina) {
		
		ModelAndView view = new ModelAndView("postagem/table-rows");
		
		Page<Pedido> page = pedidoService.findByDataPedido(pagina - 1, 5, dataPedido);
		
		view.addObject("page", page);
		
		return view;
	}

	@RequestMapping(value = "/ajax/cliente/{id}/cpf/{cpf}/page/{page}", method = RequestMethod.GET)
	public ModelAndView searchByAjaxByCliente(@PathVariable("id") Long id, @PathVariable("cpf") String cpf,
			@PathVariable("page") Integer pagina) {

		ModelAndView view = new ModelAndView("pedido/table-rows");
		Page<Pedido> page = pedidoService.findByPaginationByCliente(pagina - 1, 5, cpf, id);

		view.addObject("page", page);

		return view;
	}

	@RequestMapping(value = "/ajax/cliente/{id}/page/{page}", method = RequestMethod.GET)
	public ModelAndView pagePedidos(@PathVariable("id") Long id, @PathVariable("page") Integer pagina) {

		ModelAndView view = new ModelAndView("pedido/table-rows");

		Page<Pedido> page = pedidoService.findByPaginationByCliente(pagina - 1, 5, id);

		view.addObject("page", page);

		return view;
	}

	@RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
	public ModelAndView listPostagensByCliente(@PathVariable("id") Long id, ModelMap model) {

		Long clienteId = clienteService.findByUsuario(id).getId();

		Page<Pedido> page = pedidoService.findByPaginationByCliente(0, 5, clienteId);
		model.addAttribute("page", page);
		model.addAttribute("clienteId", clienteId);

		return new ModelAndView("pedido/list", model);
	}

	@RequestMapping(value = "/ajax/save", method = RequestMethod.POST)
	public @ResponseBody PedidoAjaxValidator saveAjax(@Validated Pedido pedido, BindingResult result,
			@AuthenticationPrincipal UsuarioLogado logado) {

		PedidoAjaxValidator validator = new PedidoAjaxValidator();

		if (result.hasErrors()) {

			validator.setStatus("FAIL");

			validator.validar(result);

			return validator;
		}

		pedido.setCliente(clienteService.findByUsuario(logado.getId()));

		pedidoService.saveOrUpdate(pedido);

		validator.setPostagem(pedido);

		return validator;
	}

	@RequestMapping(value = "/ajax/add", method = RequestMethod.GET)
	public ModelAndView cadastroAjax() {
		ModelAndView view = new ModelAndView("pedido/cadastro-ajax");
		view.addObject("categorias", produtoService.findAll());
		return view;
	}

	

	@RequestMapping(value = "/ajax/page/{page}", method = RequestMethod.GET)
	public ModelAndView pagePedidos(@PathVariable("page") Integer pagina) {

		ModelAndView view = new ModelAndView("pedido/table-rows");

		Page<Pedido> page = pedidoService.findByPagination(pagina - 1, 5);

		view.addObject("page", page);
		// view.addObject("urlPagination", "/pedido/page");

		return view;
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model) {

		Pedido pedido = pedidoService.findById(id);
		model.addAttribute("pedido", pedido);
		model.addAttribute("produtos", produtoService.findAll());
		model.addAttribute("cliente", clienteService.findAll());

		return new ModelAndView("pedido/cadastro", model);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id) {

		pedidoService.delete(id);

		return "redirect:/pedido/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listPedidos(ModelMap model) {

		// model.addAttribute("postagens", pedidoService.findAll());
		Page<Pedido> page = pedidoService.findByPagination(0, 5);

		model.addAttribute("page", page);
		// model.addAttribute("urlPagination", "/pedido/page");

		return new ModelAndView("pedido/list", model);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("pedido") @Validated Pedido pedido, BindingResult result,
			@AuthenticationPrincipal UsuarioLogado logado) {

		if (result.hasErrors()) {

			return new ModelAndView("pedido/cadastro", "produtos", produtoService.findAll());
		}
		pedido.setCliente(clienteService.findByUsuario(logado.getId()));
		 //pedido.setCliente(clienteService.findById(cliente.getId()));
		//pedido.setCliente(usuarioService.findById(logado.getId()));
		pedidoService.saveOrUpdate(pedido);

		// return new ModelAndView("redirect:/pedido/list/" + logado.getId());
		return new ModelAndView("redirect:/pedido/list/");
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView cadastro(@ModelAttribute("pedido") Pedido pedido) {
		ModelAndView view = new ModelAndView("pedido/cadastro");
		view.addObject("produtos", produtoService.findAll());
		 view.addObject("cliente", clienteService.findAll());
		return view;
	}

}
