package com.hackaton.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UsersDTO {
	@Id
	//@Column(name = "code", columnDefinition = "INT NOT NULL AUTO_INCREMENT")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long code;
	
	@Column(columnDefinition = "INT NOT NULL DEFAULT 2")
	private int code_role_user;
	
	private String name;
	private String lastName;
	
	@Column(unique=true)
	private String email;
	private String password;
	
	@Column(columnDefinition = "INT(1) NOT NULL DEFAULT 0")
	private int disabled;

	public UsersDTO() {
		
	}
	
	public UsersDTO(long code, int code_role_user, String name, String lastName, String email, String password,
			int disabled) {
		super();
		this.code = code;
		this.code_role_user = code_role_user;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.disabled = disabled;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public int getCode_role_user() {
		return code_role_user;
	}

	public void setCode_role_user(int code_role_user) {
		this.code_role_user = code_role_user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDisabled() {
		return disabled;
	}

	public void setDisabled(int disabled) {
		this.disabled = disabled;
	}
	
	
	
}
