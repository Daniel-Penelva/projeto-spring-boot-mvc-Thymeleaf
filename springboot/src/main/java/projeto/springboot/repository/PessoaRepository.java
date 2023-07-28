package projeto.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import projeto.springboot.model.Pessoa;

@Repository
@Transactional
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	/**
	 * O código abaixo é uma declaração de método em uma interface de repositório no Spring Data JPA. Ele define uma consulta personalizada 
	 * utilizando a anotação @Query para buscar pessoas pelo nome.
	 * 
	 * A anotação @Query é usada para definir consultas personalizadas no Spring Data JPA. No caso desse método, a consulta está sendo 
	 * escrita em JPQL (Java Persistence Query Language), que é uma linguagem de consulta orientada a objetos.
	 * 
	 * A consulta em si é "select p from Pessoa p where p.nome like %?1%". Vamos quebrar essa consulta em partes:
	 * -> select p: Define a seleção da entidade Pessoa (a classe mapeada pelo repositório).
	 * -> from Pessoa p: Especifica a entidade na qual a consulta será realizada.
	 * -> where p.nome like %?1%: Estabelece a condição de pesquisa, onde p.nome se refere ao atributo nome da entidade Pessoa. O operador 
	 *    like é usado para buscar registros que contenham um determinado padrão. O %?1% é um placeholder que será substituído pelo primeiro 
	 *    parâmetro do método, no caso, String nome. O uso de % antes e depois do valor do parâmetro permite que a consulta busque registros 
	 *    que tenham o nome informado em qualquer posição.
	 *    
	 * O método findPessoaByName(String nome) declara a assinatura do método. Ele espera receber uma String contendo o nome que será 
	 * utilizado na consulta. A lista de Pessoa que corresponde ao resultado da consulta é retornada como resultado desse método.
	 * 
	 * Dessa forma, ao chamar esse método em um objeto do repositório, ele executará a consulta personalizada definida e retornará a lista 
	 * de pessoas cujo nome contenha o valor fornecido.
	 * */
	
	@Query("select p from Pessoa p where p.nome like %?1% ")
	List<Pessoa> findPessoaByName(String nome);
	
	
	
	/**
	 * O mesmo exemplo do de cima, porém a query vai consultar o sexo da pessoa.
	 * */
	
	@Query("select p from Pessoa p where p.sexopessoa = ?1")
	List<Pessoa> findPessoaBySexo(String sexo);
	
	
	
	/** Esse script representa um método de consulta personalizada usando a anotação `@Query` do Spring Data JPA. Ele é usado para definir uma consulta personalizada em vez de 
	 * usar as convenções de nomenclatura padrão do Spring Data JPA.
	 * 
	 * `@Query("select p from Pessoa p where p.nome like %?1% and p.sexopessoa = ?2")` é a anotação `@Query` que especifica a consulta personalizada. Neste caso, a consulta 
	 * seleciona objetos `Pessoa` da entidade `Pessoa` onde o nome corresponde parcialmente ao primeiro parâmetro (`?1`) usando o operador `like` com a expressão `%` para 
	 * representar qualquer parte do nome. Além disso, a consulta filtra também pelo valor exato do segundo parâmetro (`?2`) na propriedade `sexopessoa`.
	 * 
	 * `List<Pessoa>` indica que o método retornará uma lista de objetos do tipo `Pessoa`. Essa lista contém as pessoas que correspondem aos critérios definidos na consulta 
	 * personalizada.
	 * 
	 * `findPessoaByNameSexo` é o nome do método. Esse nome sugere que o método é usado para buscar pessoas com base em seus nomes e sexo.
	 * 
	 * `(String nome, String sexopessoa)` são os parâmetros do método. Esses parâmetros são usados para preencher os valores dos parâmetros `?1` e `?2` na consulta personalizada. 
	 * O primeiro parâmetro (`nome`) é usado para buscar pessoas cujos nomes correspondem parcialmente ao valor fornecido. O segundo parâmetro (`sexopessoa`) é usado para buscar 
	 * pessoas com um valor exato correspondente na propriedade `sexopessoa`.
	 * 
	 * Em resumo, esse script define um método de consulta personalizada que retorna uma lista de pessoas com base em critérios de nome e sexo. Ele usa a anotação `@Query` para 
	 * especificar a consulta personalizada e os parâmetros fornecidos são usados para preencher os valores na consulta. */
	
	@Query("select p from Pessoa p where p.nome like %?1% and p.sexopessoa = ?2")
	List<Pessoa> findPessoaByNameSexo(String nome, String sexopessoa);
	
	
	
	/**
	 * O método abaixo faz parte de um repositório que estende a interface `JpaRepository` ou `PagingAndSortingRepository` do Spring Data JPA. O objetivo desse método é realizar uma 
	 * consulta paginada de pessoas por parte do nome usando o recurso de `Example` do Spring Data JPA.
	 * 
	 * Vamos entender o que cada parte do script faz:
	 * 
	 * 1. `default Page<Pessoa> findPessoaByNamePage(String nome, Pageable pageable) {`: Essa é a assinatura do método. Ele recebe um parâmetro `nome` para buscar pessoas cujo 
	 *    nome contenha uma determinada parte e um parâmetro `pageable`, que define a paginação dos resultados.
	 *    
	 * 2. `Pessoa pessoa = new Pessoa(); pessoa.setNome(nome);`: Aqui, é criada uma instância da classe `Pessoa` e definido o atributo `nome` com o valor passado como parâmetro.
	 * 
	 * 3. `ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher(nome, ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());`: É criado um 
	 *    `ExampleMatcher` para configurar a pesquisa por partes do nome, usando o operador SQL `LIKE`. O método `matchingAny()` indica que a pesquisa pode ser realizada em 
	 *    qualquer uma das propriedades da entidade. `withMatcher(nome, ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())` especifica que a pesquisa deve ser feita 
	 *    na propriedade `nome` e que ela deve conter a parte do nome ignorando maiúsculas e minúsculas.
	 *    
	 * 4. `Example<Pessoa> example = Example.of(pessoa, exampleMatcher);`: A partir do objeto `pessoa` e do `exampleMatcher`, é criada uma instância de `Example<Pessoa>`. O 
	 *    `Example` representa um exemplo da entidade que será utilizado na consulta para definir as condições da busca.
	 *    
	 * 5. `Page<Pessoa> pessoas = findAll(example, pageable);`: É feita a consulta usando o método `findAll()` do repositório com base no `Example` criado e nas configurações 
	 *    de paginação definidas pelo `pageable`.
	 *    
	 * 6. `return pessoas;`: O método retorna o resultado da consulta paginada de pessoas.
	 * 
	 * Com esse método, você pode buscar pessoas cujo nome contenha uma parte específica e obter os resultados paginados, o que é útil para apresentar grandes conjuntos de dados 
	 * de forma mais organizada e eficiente em uma interface de usuário.
	 * */
	default Page<Pessoa> findPessoaByNamePage(String nome, Pageable pageable){
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		
		// Configurando a pesquisa para a consulta por partes do nome no banco de dados, igual a LIKE do SQL.
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		// Une o objeto com o valor e a configuração para a consulta 
		Example<Pessoa> example = Example.of(pessoa, exampleMatcher);
		
		Page<Pessoa> pessoas = findAll(example, pageable);
		
		return pessoas;
	}
	
	
	default Page<Pessoa> findPessoaBySexoPage(String nome, String sexo, Pageable pageable){
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		pessoa.setSexopessoa(sexo);
		
		// Configurando a pesquisa para a consulta por partes do nome no banco de dados, igual a LIKE do SQL.
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("sexopessoa", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		// Une o objeto com o valor e a configuração para a consulta 
		Example<Pessoa> example = Example.of(pessoa, exampleMatcher);
		
		Page<Pessoa> pessoas = findAll(example, pageable);
		
		return pessoas;
	}
	
}
