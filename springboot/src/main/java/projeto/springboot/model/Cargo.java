package projeto.springboot.model;

public enum Cargo {
	JUNIOR("Júnior"), 
	PLENO("Pleno"), 
	SENIOR("Sênio");
	
	private String nome;
	private String valor;
	
	private Cargo(String nome) {
		this.nome = nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return valor = this.name();
	}
	
	
   /* Para o Exemplo 2 (github)
   
	@Override
	public String toString() {
		return this.name();
	}
	*/
}


/**
 * Apenas para didática:
 * 
 * O método name() é um método da classe Enum em Java e é herdado por todas as enumerações, incluindo a enumeração que você apresentou no script ("Cargo").
 * 
 * O método name() é usado para obter o nome da constante de uma enumeração como uma string. Em outras palavras, quando você chama name() em uma constante de enumeração, ele 
 * retorna o nome dessa constante como uma string.
 * 
 * Por exemplo, suponha que você tenha a enumeração "Cargo" definida como mostrado no script. Se você chamar o método name() em uma constante da enumeração, como Cargo.JUNIOR, o 
 * resultado será a string "JUNIOR", que é o nome da constante. Da mesma forma, se você chamar o método name() em Cargo.PLENO, o resultado será a string "PLENO", e assim por 
 * diante. Ou seja, se atribuido o valor PLENO na variável cargo, e em seguida, o método name() é chamado na variável cargo, retornando a string "PLENO", que é o nome da 
 * constante da enumeração.
 * 
 * */
 