package projeto.springboot.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String login;
	private String senha;

	// Um usuário tem um ou vários acessos.
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuarios_role", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id", table = "usuário"), 
	inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", table = "role"))
	private List<Role> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return roles;
	}

	// Retorna a senha do usuário
	@Override
	public String getPassword() {

		return senha;
	}

	// Retorna o nome de usuário do usuário autenticado
	@Override
	public String getUsername() {

		return login;
	}

	// Indica se a conta do usuário não está expirada.
	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	// Indica se a conta do usuário não está bloqueada.
	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	// Indica se as credenciais do usuário (por exemplo, senha) não estão expiradas.
	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	// Indica se a conta do usuário está habilitada.
	@Override
	public boolean isEnabled() {

		return true;
	}

}
