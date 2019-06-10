package com.polaroid.chatweb.model.user;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails {

	// need this constant for serialize within session's
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	@NotNull
	private String username;

	@Column(unique = true)
	@Email
	@NotNull
	private String email;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	@Column
	@NotNull
	private String ownerName;

	@Column
	private String password;
	
	@Column(nullable = false)
	private boolean isValidated;

	User() {

	}

	public User(Long id, @NotNull String username, @Email @NotNull String email, @NotNull String ownerName,
			@NotNull String password) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.ownerName = ownerName;
		this.password = password;
		this.isValidated = false;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public String getPassword() {
		return password;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void validate() { 
		this.isValidated = true;
	}
	
	public boolean isEmailVerified() {
		return this.isValidated;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.isValidated;
	}

}