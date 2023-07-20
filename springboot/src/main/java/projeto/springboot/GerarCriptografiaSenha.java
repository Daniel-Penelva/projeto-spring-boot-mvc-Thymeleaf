package projeto.springboot;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GerarCriptografiaSenha {

	public static void main(String[] args) {
		
		//OBS. Rodar no java application
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String result = encoder.encode("123");
		System.out.println(result);
	}
}
