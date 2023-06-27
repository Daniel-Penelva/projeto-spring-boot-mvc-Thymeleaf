package projeto.springboot.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{
	
	// Configura as solicitações acesso por Http - http://localhost:8080/login?logout
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/* linha 27 - Desativa as configurações padrão de memória.
		 * linha 28 - Permitir restringir acessos.
		 * linha 29 - Qualquer usuário acessa a página principal.
		 * linha 31 - Permite qualquer usuário
		 * linha 32 - Mapeia URL de logout e invalida usuário autenticado  */
		
		http.csrf()
		.disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	// Cria autenticação do usuário com banco de dados ou em memória
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
		.withUser("admin")
		.password("123")
		.roles("ADMIN");
	}
	
	// Ignora URL específicas
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/materialize/**");
	}
}
