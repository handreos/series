package br.edu.ifrs.series.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifrs.series.dao.JogoDAO;
import br.edu.ifrs.series.dominio.Jogo;
import jakarta.validation.Valid;


@Controller
public class JogoPaginaController {
	
	private final JogoDAO dao;
	
	public JogoPaginaController(JogoDAO dao) {
		this.dao = dao;
	}
	
	@GetMapping("/paginas/jogos")
	public String lista(Model model) {
		model.addAttribute("jogos", dao.listarJogos());
		return "jogos/lista";
	}
	
	@GetMapping("/paginas/jogos/novo")
	public String formulario(Model model) {
		model.addAttribute("jogo", new Jogo());
		return "jogos/formulario";
	}
	
	@PostMapping("/paginas/jogos/novo")
	public String cadastrar(@Valid @ModelAttribute Jogo jogo, BindingResult resultado) {
		if (resultado.hasErrors()) {
			return "jogos/formulario";
		}
	    dao.salvar(jogo);
	    return "redirect:/paginas/jogos";
	}

}
