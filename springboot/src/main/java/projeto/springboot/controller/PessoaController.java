package projeto.springboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import projeto.springboot.model.Pessoa;
import projeto.springboot.repository.PessoaRepository;

@Controller
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	
	/**  O código abaixo trata-se de uma requisição HTTP GET para a URL "/cadastropessoa" e retorna uma visualização (view) chamada 
	 * "cadastro/cadastropessoa" usando o objeto ModelAndView.
	 * 
	 * A anotação @RequestMapping é usada para mapear uma URL a um método em um controlador. Nesse caso, o método inicio() será executado 
	 * quando uma requisição GET for feita para a URL "/cadastropessoa".
	 * 
	 * A classe ModelAndView é utilizada para representar uma visualização (view) que será renderizada e retornada como resposta para o 
	 * cliente. Nesse caso, a visualização é definida como "cadastro/cadastropessoa".
	 * 
	 * Esse código adiciona um objeto chamado "pessoaobj" ao ModelAndView. O objeto Pessoa() está sendo criado e atribuído a esse objeto, 
	 * possivelmente para ser utilizado posteriormente na visualização.
	 * */
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
	public ModelAndView inicio() {

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		modelAndView.addObject("pessoaobj", new Pessoa());
		return modelAndView;
	}

	
	/** O código abaixo trata-se de uma requisição HTTP POST para a URL "2asteristicos/salvarpessoa" e realiza algumas operações para salvar 
	 * uma entidade "Pessoa" no repositório.
	 * 
	 * A anotação @RequestMapping é usada para mapear uma URL a um método em um controlador. Nesse caso, o método salvar() será executado 
	 * quando uma requisição POST for feita para a URL "2asteristicos/salvarpessoa".
	 * 
	 * O parâmetro do método Pessoa pessoa indica que ele espera receber um objeto do tipo Pessoa no corpo da requisição. O framework 
	 * Spring MVC irá realizar a conversão dos parâmetros da requisição para o objeto Pessoa.
	 * 
	 * Esse código "pessoaRepository.save(pessoa);" salva a entidade Pessoa no repositório, utilizando o objeto pessoaRepository. 
	 * que é uma instância de um objeto que implementa a interface PessoaRepository, responsável por acessar e manipular os dados de pessoas
	 * no banco de dados.
	 * 
	 * Após salvar a pessoa no repositório, é criado um objeto ModelAndView com a visualização "cadastro/cadastropessoa". Em seguida, é 
	 * feita uma busca por todas as pessoas no repositório através do método findAll() do pessoaRepository. O resultado dessa busca é 
	 * adicionado ao objeto ModelAndView com o nome "pessoas". Isso é utilizado na visualização para exibir a lista de pessoas cadastradas.
	 * 
	 * Um objeto vazio do tipo Pessoa é adicionado ao objeto ModelAndView com o nome "pessoaobj". Isso é utilizado na visualização para 
	 * exibir um formulário vazio para o cadastro de uma nova pessoa.
	 * 
	 * Por fim, o objeto ModelAndView é retornado como resultado do método. Isso significa que a visualização "cadastro/cadastropessoa" 
	 * será renderizada e retornada como resposta para o cliente que fez a requisição POST. A visualização exibirá a lista de pessoas 
	 * cadastradas e um formulário vazio para o cadastro de uma nova pessoa.
	 * 
	 * IMPORTANTE!!!
	 * Os dois asteriscos (), no contexto da URL "/salvarpessoa", são utilizados como um curinga para representar qualquer número de 
	 * diretórios ou subdiretórios antes do segmento "/salvarpessoa".
	 * 
	 * Isso significa que a URL pode ter qualquer caminho antes de "/salvarpessoa" e ainda será mapeada para o método correspondente. Por 
	 * exemplo, as seguintes URLs seriam correspondidas por essa configuração:
	 * 
	 *  -> "/salvarpessoa"
	 *  -> /app/salvarpessoa"
	 *  -> "/usuarios/cadastro/salvarpessoa"
	 *  
	 *  O uso dos asteriscos permite maior flexibilidade no mapeamento das URLs e ajuda a tratar diferentes possibilidades de caminhos que 
	 *  podem ser utilizados na aplicação. É útil quando você deseja criar um mapeamento genérico para determinada rota, independentemente 
	 *  do número de diretórios anteriores.
	 * */
	
	@RequestMapping(method = RequestMethod.POST, value = "**/salvarpessoa")
	public ModelAndView salvar(Pessoa pessoa) {

		pessoaRepository.save(pessoa);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		Iterable<Pessoa> pessoaIt = pessoaRepository.findAll();
		
		modelAndView.addObject("pessoas", pessoaIt);
		modelAndView.addObject("pessoaobj", new Pessoa());

		return modelAndView;
	}

	
	/**
	 * O código abaixo trata-se de uma requisição HTTP GET para a URL "/listapessoas" e retorna uma visualização (view) chamada 
	 * "cadastro/cadastropessoa" com uma lista de pessoas carregadas do banco de dados.
	 * 
	 * A anotação @RequestMapping é usada para mapear uma URL a um método em um controlador. Nesse caso, o método listarPessoas() será 
	 * executado quando uma requisição GET for feita para a URL "/listapessoas".
	 * 
	 * A classe ModelAndView é utilizada para representar uma visualização (view) que será renderizada e retornada como resposta para o 
	 * cliente. Nesse caso, a visualização é definida como "cadastro/cadastropessoa".
	 * 
	 * O código "pessoaRepository.findAll()" faz uma busca no banco de dados utilizando o método findAll() do pessoaRepository. O resultado 
	 * dessa busca é armazenado em uma variável do tipo Iterable<Pessoa>, que representa uma lista de pessoas.
	 * 
	 * A lista de pessoas é adicionada ao objeto ModelAndView com o nome "pessoas". Isso permite que a lista seja acessada e exibida na 
	 * visualização "cadastro/cadastropessoa".
	 * 
	 * Um objeto vazio do tipo Pessoa é adicionado ao objeto ModelAndView com o nome "pessoaobj". Isso é utilizado na visualização para 
	 * exibir um formulário vazio para o cadastro de uma nova pessoa.
	 * 
	 * Por fim, o objeto ModelAndView é retornado como resultado do método. Isso significa que a visualização "cadastro/cadastropessoa" 
	 * será renderizada e retornada como resposta para o cliente que fez a requisição GET. A visualização exibirá a lista de pessoas 
	 * carregadas do banco de dados e um formulário vazio para o cadastro de uma nova pessoa.
	 * */
	
	@RequestMapping(method = RequestMethod.GET, value = "/listapessoas")
	public ModelAndView listarPessoas() {

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");

		Iterable<Pessoa> pessoaIt = pessoaRepository.findAll();

		modelAndView.addObject("pessoas", pessoaIt);
		modelAndView.addObject("pessoaobj", new Pessoa());

		return modelAndView;
	}

	
	/**
	 * O código abaixo trata-se de uma requisição HTTP GET para a URL "/editarpessoa/{idpessoa}", onde "{idpessoa}" é um parâmetro dinâmico 
	 * que representa o ID da pessoa a ser editada. O método retorna uma visualização (view) chamada "cadastro/cadastropessoa" com os dados 
	 * da pessoa carregada do banco de dados.
	 * 
	 * A anotação @GetMapping é uma abreviação de @RequestMapping(method = RequestMethod.GET), indicando que o método editar() será executado 
	 * apenas para requisições GET. A URL "/editarpessoa/{idpessoa}" é mapeada, onde "{idpessoa}" é um parâmetro dinâmico que será extraído 
	 * da URL.
	 * 
	 * O parâmetro @PathVariable("idpessoa") Long idpessoa indica que o valor do parâmetro "{idpessoa}" na URL será atribuído à variável 
	 * idpessoa do tipo Long.
	 * 
	 * Esse código "pessoaRepository.findById(idpessoa)" busca uma pessoa no banco de dados utilizando o método findById() do pessoaRepository. 
	 * O ID da pessoa é passado como parâmetro para a busca. O resultado é armazenado em um objeto Optional<Pessoa>, que representa uma 
	 * pessoa opcional, ou seja, pode ou não existir no banco de dados.
	 * 
	 * A classe ModelAndView é utilizada para representar uma visualização (view) que será renderizada e retornada como resposta para o 
	 * cliente. Nesse caso, a visualização é definida como "cadastro/cadastropessoa".
	 * 
	 * O objeto Pessoa obtido do Optional<Pessoa> é adicionado ao objeto ModelAndView com o nome "pessoaobj". Isso permite que os dados da 
	 * pessoa sejam acessados e exibidos na visualização "cadastro/cadastropessoa".
	 * 
	 * Por fim, o objeto ModelAndView é retornado como resultado do método. Isso significa que a visualização "cadastro/cadastropessoa" será 
	 * renderizada e retornada como resposta para o cliente que fez a requisição GET. A visualização exibirá os dados da pessoa carregada do 
	 * banco de dados, permitindo assim a edição dos mesmos.
	 * */
	
	@GetMapping("/editarpessoa/{idpessoa}")
	public ModelAndView editar(@PathVariable("idpessoa") Long idpessoa) {

		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		modelAndView.addObject("pessoaobj", pessoa.get());

		return modelAndView;
	}

}
