package ua.dto.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import ua.entity.Role;

public class UserForm {
	
	private int id;

	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	@NotEmpty
	private String email;
	@NotEmpty
	private String fone;
	@NotNull
	private Role role = Role.ROLE_USER;
	
	private String count;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}	
}
