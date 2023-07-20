package projeto.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;
	
	// Configura as solicitações acesso por Http - http://localhost:8080/login?logout
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf()
		.disable()  // Desativa as configurações padrão de memória.
		.authorizeRequests() // Permitir restringir acessos.
		.antMatchers(HttpMethod.GET, "/").permitAll()  // Qualquer usuário acessa a página principal.
		//.antMatchers("/materialize/**").permitAll()  
		.antMatchers(HttpMethod.GET, "/cadastropessoa").hasAnyRole("ADMIN")  // Permite somente que o usuário admin acesse a página '/cadastropesssoa'
		.anyRequest().authenticated()
		.and().formLogin().permitAll()  // permite qualquer usuário
		.loginPage("/login")  // página de login
		.defaultSuccessUrl("/cadastropessoa")  // página padrão se efetuou o login
		.failureUrl("/login?error=true")  // página padrão se falhou o login
		.and().logout()  // Mapeia URL de Logout e invalida usuário autenticado
		.logoutSuccessUrl("/login")  // página padrão após fazer o logout
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));  // Diz que o processo de logout será acionado.
	}
	
	// Cria autenticação do usuário com banco de dados ou em memória
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(implementacaoUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		
		/*
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
		.withUser("admin")
		.password("$2a$10$t73SPdIn7BadIcSACXLsp.nHwwtTXZnY/4bRZFZCy5.kwZQo7CiZW")
		.roles("ADMIN");*/
	}
	
	// Ignora URL específicas
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/materialize/**");
	}
	
	
}
