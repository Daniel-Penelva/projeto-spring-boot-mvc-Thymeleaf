package projeto.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import projeto.springboot.model.Pessoa;
import projeto.springboot.repository.PessoaRepository;

@Controller
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	// URL mapeada: http://localhost:8080/cadastropessoa
	@RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
	public String inicio() {

		// Retorna para a tela - acessa a pasta/nomeDoArquivo
		return "cadastro/cadastropessoa";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvarpessoa")
	public ModelAndView salvar(Pessoa pessoa) {

		pessoaRepository.save(pessoa);

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		Iterable<Pessoa> pessoaIt = pessoaRepository.findAll();
		modelAndView.addObject("pessoas", pessoaIt);

		return modelAndView;
	}

	// URL mapeada: http://localhost:8080/listapessoas
	@RequestMapping(method = RequestMethod.GET, value = "/listapessoas")
	public ModelAndView listarPessoas() {

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");

		// Carregar no banco de dados a minha lista carregada
		Iterable<Pessoa> pessoaIt = pessoaRepository.findAll();

		// Add o modelAndView na lista
		modelAndView.addObject("pessoas", pessoaIt);

		return modelAndView;
	}

}
