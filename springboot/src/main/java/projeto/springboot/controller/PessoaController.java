package projeto.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import projeto.springboot.model.Pessoa;
import projeto.springboot.repository.PessoaRepository;

@Controller
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
	public String inicio() {
		
		// Retorna para a tela - acessa a pasta/nomeDoArquivo
		return "cadastro/cadastropessoa";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvarpessoa")
	public String salvar(Pessoa pessoa) {
		
		pessoaRepository.save(pessoa);
		
		return "cadastro/cadastropessoa";
	}

}
