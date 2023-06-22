package projeto.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import projeto.springboot.model.Telefone;

@Repository
@Transactional
public interface TelefoneRepository extends CrudRepository<Telefone, Long> {
	
	/**
	 * select t from Telefone t
	 * Esta é a cláusula SELECT da consulta JPQL. Ela indica que queremos selecionar uma entidade chamada "Telefone" 
	 * (que é uma entidade mapeada em um banco de dados) e atribuí-la a um alias "t". Esse alias "t" será usado para se referir à 
	 * entidade "Telefone" nas cláusulas subsequentes da consulta.
	 * 
	 * where t.pessoa.id = ?1
	 * Esta é a cláusula WHERE da consulta JPQL. Ela é usada para filtrar os resultados com base em uma condição específica. Neste 
	 * caso, estamos filtrando os telefones com base no ID de uma entidade "Pessoa" associada. A expressão t.pessoa.id se refere ao 
	 * atributo "id" da entidade "Pessoa" associada à entidade "Telefone". O ?1 é um marcador de posição que será substituído por 
	 * um valor real quando a consulta for executada. O valor correspondente ao marcador de posição será passado como argumento para 
	 * o método getTelefones.
	 * 
	 * public List<Telefone> getTelefones(Long pessoaid);
	 * Esta é a declaração do método que contém a consulta personalizada. O método é definido para retornar uma lista de objetos 
	 * "Telefone". Ele possui um parâmetro chamado "pessoaid" do tipo "Long", que será usado para fornecer o valor do ID da pessoa 
	 * na cláusula WHERE da consulta. O valor do parâmetro será vinculado ao marcador de posição ?1 na consulta.
	 * 
	 * Em resumo, esse script representa um método em algum repositório de dados (usando o Spring Data JPA) que busca telefones com 
	 * base no ID de uma pessoa específica. A consulta JPQL é usada para recuperar os telefones associados à pessoa com o ID 
	 * fornecido.
	 * */
	
	@Query("select t from Telefone t where t.pessoa.id = ?1")
	public List<Telefone> getTelefones(Long pessoaid);

}
