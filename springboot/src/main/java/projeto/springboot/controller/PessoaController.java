package projeto.springboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import projeto.springboot.model.Pessoa;
import projeto.springboot.model.Telefone;
import projeto.springboot.repository.PessoaRepository;
import projeto.springboot.repository.TelefoneRepository;

@Controller
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private TelefoneRepository telefoneRepository;

	
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
	 * 
	 * O código "pessoaRepository.findAll()" faz uma busca no banco de dados utilizando o método findAll() do pessoaRepository. O resultado 
	 * dessa busca é armazenado em uma variável do tipo Iterable<Pessoa>, que representa uma lista de pessoas.
	 * 
	 * A lista de pessoas é adicionada ao objeto ModelAndView com o nome "pessoas". Isso permite que a lista seja acessada e exibida na 
	 * visualização "cadastro/cadastropessoa". Ou seja, a lista vai ser criada no momento que acessar o cadastropessoa.
	 * */
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
	public ModelAndView inicio() {

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		modelAndView.addObject("pessoaobj", new Pessoa());
		
		Iterable<Pessoa> pessoaIt = pessoaRepository.findAll();
		modelAndView.addObject("pessoas", pessoaIt);
		
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
	
	/**
	 * O código abaixo trata-se de uma requisição HTTP GET para a URL "/removerpessoa/{idpessoa}", onde "{idpessoa}" é um parâmetro dinâmico 
	 * que representa o ID da pessoa a ser removida. O método realiza a exclusão da pessoa no banco de dados e retorna uma visualização 
	 * (view) chamada "cadastro/cadastropessoa" com a lista atualizada de pessoas.
	 * 
	 * A anotação @GetMapping é uma abreviação de @RequestMapping(method = RequestMethod.GET), indicando que o método excluir() será 
	 * executado apenas para requisições GET. A URL "/removerpessoa/{idpessoa}" é mapeada, onde "{idpessoa}" é um parâmetro dinâmico que 
	 * será extraído da URL.
	 * 
	 * O parâmetro @PathVariable("idpessoa") Long idpessoa indica que o valor do parâmetro "{idpessoa}" na URL será atribuído à variável 
	 * idpessoa do tipo Long.
	 * 
	 * Esse código "pessoaRepository.deleteById(idpessoa)" realiza a exclusão da pessoa no banco de dados utilizando o método deleteById() 
	 * do pessoaRepository. O ID da pessoa é passado como parâmetro para a exclusão.
	 * 
	 * A classe ModelAndView é utilizada para representar uma visualização (view) que será renderizada e retornada como resposta para o 
	 * cliente. Nesse caso, a visualização é definida como "cadastro/cadastropessoa".
	 * 
	 * A lista atualizada de pessoas é adicionada ao objeto ModelAndView com o nome "pessoas". Isso permite que a lista seja acessada e 
	 * exibida na visualização "cadastro/cadastropessoa", mostrando as pessoas restantes após a exclusão.
	 * 
	 * Um objeto vazio do tipo Pessoa é adicionado ao objeto ModelAndView com o nome "pessoaobj". Isso é utilizado na  visualização para 
	 * exibir um formulário vazio para o cadastro de uma nova pessoa.
	 * 
	 * Por fim, o objeto ModelAndView é retornado como resultado do método. Isso significa que a visualização "cadastro/cadastropessoa" 
	 * será renderizada e retornada como resposta para o cliente que fez a requisição GET. A visualização exibirá a lista atualizada de 
	 * pessoas após a exclusão e um formulário vazio para o cadastro de uma nova pessoa.
	 * */
	
	@GetMapping("/removerpessoa/{idpessoa}")
	public ModelAndView excluir(@PathVariable("idpessoa") Long idpessoa) {

		pessoaRepository.deleteById(idpessoa);

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		modelAndView.addObject("pessoas", pessoaRepository.findAll());
		modelAndView.addObject("pessoaobj", new Pessoa());

		return modelAndView;
	}
	
	
	/**
	 * O código abaixo trata-se de uma requisição HTTP POST para a URL "2asteristicos/pesquisarpessoa" e realiza uma pesquisa de pessoas 
	 * com base em um parâmetro de nome. O método retorna uma visualização (view) chamada "cadastro/cadastropessoa" com os resultados da 
	 * pesquisa.
	 * 
	 * A anotação @PostMapping indica que o método pesquisar() será executado apenas para requisições POST. A URL "/pesquisarpessoa" é 
	 * mapeada, onde "" é utilizado como um curinga para representar qualquer número de diretórios ou subdiretórios antes de "/pesquisarpessoa".
	 * 
	 * O parâmetro @RequestParam("nomepesquisa") String nomepesquisa indica que o valor do parâmetro "nomepesquisa" enviado no corpo da 
	 * requisição será atribuído à variável nomepesquisa do tipo String.
	 * 
	 * A classe ModelAndView é utilizada para representar uma visualização (view) que será renderizada e retornada como resposta para o 
	 * cliente. Nesse caso, a visualização é definida como "cadastro/cadastropessoa".
	 * 
	 * O método findPessoaByName(nomepesquisa) é chamado no objeto pessoaRepository para buscar as pessoas com base no nome informado. O 
	 * resultado da pesquisa, que é uma lista de pessoas correspondentes, é adicionado ao objeto ModelAndView com o nome "pessoas". Isso 
	 * permite que a lista seja acessada e exibida na visualização "cadastro/cadastropessoa".
	 * 
	 * Um objeto vazio do tipo Pessoa é adicionado ao objeto ModelAndView com o nome "pessoaobj". Isso provavelmente é utilizado na 
	 * visualização para exibir um formulário vazio para o cadastro de uma nova pessoa.
	 * 
	 * Por fim, o objeto ModelAndView é retornado como resultado do método. Isso significa que a visualização "cadastro/cadastropessoa" 
	 * será renderizada e retornada como resposta para o cliente que fez a requisição POST. A visualização exibirá os resultados da 
	 * pesquisa de pessoas com base no nome informado, além de um formulário vazio para o cadastro de uma nova pessoa.
	 * */
	
	@PostMapping("**/pesquisarpessoa")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		modelAndView.addObject("pessoas", pessoaRepository.findPessoaByName(nomepesquisa));
		modelAndView.addObject("pessoaobj", new Pessoa());
		
		return modelAndView;
	}
	
	
	/**
	 * O código abaixo trata-se de uma requisição HTTP GET para a URL "/telefones/{idpessoa}", onde "{idpessoa}" é um 
	 * parâmetro dinâmico que representa o ID da pessoa. O método retorna uma visualização (view) chamada "cadastro/telefones" 
	 * com os dados da pessoa.
	 * 
	 * A anotação @GetMapping é uma abreviação de @RequestMapping(method = RequestMethod.GET), indicando que o método 
	 * telefones() será executado apenas para requisições GET. A URL "/telefones/{idpessoa}" é mapeada, onde "{idpessoa}" é 
	 * um parâmetro dinâmico que será extraído da URL.
	 * 
	 * O parâmetro @PathVariable("idpessoa") Long idpessoa indica que o valor do parâmetro "{idpessoa}" na URL será atribuído 
	 * à variável idpessoa do tipo Long.
	 * 
	 * Esse código "pessoaRepository.findById(idpessoa)" busca uma pessoa no banco de dados utilizando o método findById() 
	 * do pessoaRepository. O ID da pessoa é passado como parâmetro para a busca. O resultado é armazenado em um objeto 
	 * Optional<Pessoa>, que representa uma pessoa opcional, ou seja, pode ou não existir no banco de dados.
	 * 
	 * A classe ModelAndView é utilizada para representar uma visualização (view) que será renderizada e retornada como 
	 * resposta para o cliente. Nesse caso, a visualização é definida como "cadastro/telefones".
	 * 
	 * O objeto Pessoa obtido do Optional<Pessoa> é adicionado ao objeto ModelAndView com o nome "pessoaobj". Isso permite 
	 * que os dados da pessoa sejam acessados e exibidos na visualização "cadastro/telefones".
	 * 
	 * modelAndView.addObject("telefones", telefoneRepository.getTelefones(pessoaid));
	 * Outro objeto chamado "telefones" é adicionado ao "ModelAndView". O valor desse objeto é obtido chamando um método 
	 * getTelefones(pessoaid) no repositório de "Telefone". Esse método retorna uma lista de telefones associados à pessoa com o 
	 * ID fornecido.
	 * 
	 * Por fim, o objeto ModelAndView é retornado como resultado do método. Isso significa que a visualização 
	 * "cadastro/telefones" será renderizada e retornada como resposta para o cliente que fez a requisição GET. A 
	 * visualização exibirá os dados da pessoa carregada do banco de dados, permitindo assim a visualização dos telefones 
	 * relacionados àquela pessoa.
	 * */
	@GetMapping("/telefones/{idpessoa}")
	public ModelAndView telefones(@PathVariable("idpessoa") Long idpessoa) {

		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);

		ModelAndView modelAndView = new ModelAndView("cadastro/telefones");
		modelAndView.addObject("pessoaobj", pessoa.get());
		modelAndView.addObject("telefones", telefoneRepository.getTelefones(idpessoa));

		return modelAndView;
	}
	
	
	/**
	 * O código aabaixo trata-se de uma requisição HTTP POST para a URL "2asteristicos/addfonepessoa/{pessoaid}", onde "{pessoaid}" 
	 * é um parâmetro dinâmico que representa o ID da pessoa. O método adiciona um telefone à pessoa especificada, salva o telefone no 
	 * banco de dados e retorna uma visualização (view) chamada "cadastro/telefones" com os dados atualizados da pessoa.
	 * 
	 * A anotação @PostMapping indica que o método addFonePessoa() será executado apenas para requisições POST. A URL 
	 * "/addfonepessoa/{pessoaid}" é mapeada, onde "" é utilizado como um curinga para representar qualquer número de diretórios 
	 * ou subdiretórios antes de "/addfonepessoa/{pessoaid}". "{pessoaid}" é um parâmetro dinâmico que será extraído da URL.
	 * 
	 * O parâmetro Telefone telefone representa o objeto Telefone que será enviado no corpo da requisição POST. O parâmetro 
	 * @PathVariable("pessoaid") Long pessoaid indica que o valor do parâmetro "{pessoaid}" na URL será atribuído à variável 
	 * pessoaid do tipo Long.
	 * 
	 * Esse código "pessoaRepository.findById(pessoaid).get()" busca a pessoa no banco de dados utilizando o método findById() do 
	 * pessoaRepository. O ID da pessoa é passado como parâmetro para a busca. O resultado é armazenado em um objeto 
	 * Optional<Pessoa>, e então é chamado o método get() para obter a instância de Pessoa. Em seguida, o objeto Pessoa é associado 
	 * ao objeto Telefone definindo-o como o valor do atributo pessoa.
	 * 
	 * O objeto Telefone com a pessoa associada é salvo no banco de dados utilizando o método save() do telefoneRepository.
	 * 
	 * A classe ModelAndView é utilizada para representar uma visualização (view) que será renderizada e retornada como resposta 
	 * para o cliente. Nesse caso, a visualização é definida como "cadastro/telefones".
	 * 
	 * O objeto Pessoa é adicionado ao objeto ModelAndView com o nome "pessoaobj". Isso permite que os dados da pessoa sejam
	 * acessados e exibidos na visualização "cadastro/telefones".
	 * 
	 * modelAndView.addObject("telefones", telefoneRepository.getTelefones(pessoaid));
	 * Outro objeto chamado "telefones" é adicionado ao "ModelAndView". O valor desse objeto é obtido chamando um método 
	 * getTelefones(pessoaid) no repositório de "Telefone". Esse método retorna uma lista de telefones associados à pessoa com o 
	 * ID fornecido.
	 * 
	 * Por fim, o objeto ModelAndView é retornado como resultado do método. Isso significa que a visualização "cadastro/telefones" 
	 * será renderizada e retornada como resposta para o cliente que fez a requisição POST. A visualização exibirá os dados 
	 * atualizados da pessoa, incluindo o telefone adicionado.
	 * */
	
	@PostMapping("**/addfonepessoa/{pessoaid}")
	public ModelAndView addFonePessoa(Telefone telefone, @PathVariable("pessoaid") Long pessoaid) {
		
		Pessoa pessoa = pessoaRepository.findById(pessoaid).get();
		telefone.setPessoa(pessoa);
		
		telefoneRepository.save(telefone);
	
		ModelAndView modelAndView = new ModelAndView("cadastro/telefones");
		modelAndView.addObject("pessoaobj", pessoa);
		modelAndView.addObject("telefones", telefoneRepository.getTelefones(pessoaid));
		return modelAndView;
	}
	
	
	/**
	 * @GetMapping("/removertelefone/{idtelefone}")
	 * Esta é uma anotação que mapeia uma requisição HTTP GET para o URL especificado. O URL é definido como 
	 * "/removertelefone/{idtelefone}", onde "{idtelefone}" é uma variável de caminho que será capturada e usada como um parâmetro 
	 * no método.
	 * 
	 * public ModelAndView excluirTelefone(@PathVariable("idtelefone") Long idtelefone)
	 * Este é o método em si. Ele é chamado quando a requisição GET é feita para o URL mapeado. Ele possui um parâmetro 
	 * @PathVariable("idtelefone") Long idtelefone, que indica que o valor da variável de caminho "{idtelefone}" deve ser atribuído 
	 * ao parâmetro "idtelefone".
	 * 
	 * Pessoa pessoa = telefoneRepository.findById(idtelefone).get().getPessoa();
	 * Nesta linha, o código está usando um repositório (usando o Spring Data JPA) para buscar um objeto "Telefone" com base no ID 
	 * fornecido. O método findById(idtelefone) retorna um objeto "Optional<Telefone>", e o método get() é chamado para obter a 
	 * instância real do objeto "Telefone". Em seguida, é chamado o método getPessoa() para obter a entidade "Pessoa" associada ao 
	 * telefone.
	 * 
	 * telefoneRepository.deleteById(idtelefone);
	 * Esta linha exclui o objeto "Telefone" do banco de dados com base no ID fornecido. O método deleteById(idtelefone) é chamado 
	 * no repositório para executar a exclusão.
	 * 
	 * ModelAndView modelAndView = new ModelAndView("cadastro/telefones");
	 * Uma instância de "ModelAndView" é criada com o nome da view "cadastro/telefones". Isso indica que após a execução do método, 
	 * a resposta será renderizada usando essa view.
	 * 
	 * modelAndView.addObject("pessoaobj", pessoa);
	 * Um objeto "pessoaobj" é adicionado ao "ModelAndView" com o valor do objeto "Pessoa" recuperado anteriormente. Esse objeto 
	 * estará disponível na view para ser usado na renderização.
	 * 
	 * modelAndView.addObject("telefones", telefoneRepository.getTelefones(pessoa.getId()));
	 * Outro objeto chamado "telefones" é adicionado ao "ModelAndView". O valor desse objeto é obtido chamando um método 
	 * getTelefones(pessoa.getId()) no repositório de "Telefone". Presumivelmente, esse método retorna uma lista de telefones 
	 * associados à pessoa com o ID recuperado anteriormente.
	 * 
	 * return modelAndView;
	 * Por fim, o "ModelAndView" é retornado, indicando qual view será renderizada como resposta à requisição.
	 * 
	 * Em resumo, esse script representa um método de um controlador que é acionado quando uma requisição GET é feita para o URL 
	 * "/removertelefone/{idtelefone}". Ele recebe o ID de um telefone a ser excluído, busca o objeto "Telefone" correspondente no 
	 * banco de dados, exclui-o, configura os objetos necessários para a renderização da view e retorna um "ModelAndView" com 
	 * esses objetos.
	 * */
	
	@GetMapping("/removertelefone/{idtelefone}")
	public ModelAndView excluirTelefone(@PathVariable("idtelefone") Long idtelefone) {

	    Pessoa pessoa =	telefoneRepository.findById(idtelefone).get().getPessoa();
		
		telefoneRepository.deleteById(idtelefone);

		ModelAndView modelAndView = new ModelAndView("cadastro/telefones");
		modelAndView.addObject("pessoaobj", pessoa);
		modelAndView.addObject("telefones", telefoneRepository.getTelefones(pessoa.getId()));

		return modelAndView;
	}

}
