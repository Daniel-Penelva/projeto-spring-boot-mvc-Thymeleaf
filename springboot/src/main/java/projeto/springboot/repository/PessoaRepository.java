package projeto.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import projeto.springboot.model.Pessoa;

@Repository
@Transactional
public interface PessoaRepository extends CrudRepository<Pessoa, Long>{
	
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

}
