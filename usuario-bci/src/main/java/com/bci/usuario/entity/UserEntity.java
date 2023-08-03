package com.bci.usuario.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;


import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;

@Entity
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Basic(optional = false)
	@Column(name = "user_id")
	private UUID id;

	@Basic(optional = false)
	@Column(name = "user_name")
	private String name;

	@Basic(optional = false)
	@Column(name = "user_email", unique = true)
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "El correo electrónico no es válido")
	private String email;

	@Basic(optional = false)
	@Column(name = "user_password")
	@Pattern(regexp = "^(?=.*[0-9]).{8,}$", message = "La clave debe contener al menos un dígito y tener una longitud mínima de 8 caracteres.")
	private String password;

	@Basic(optional = false)
	@Column(name = "user_created")
	private Date created;

	@Basic(optional = false)
	@Column(name = "user_modified")
	private Date modified;

	@Basic(optional = true)
	@Column(name = "user_last_login")
	private Date last_login;

	@Basic(optional = true)
	@Column(name = "user_token")
	private String token;

	@Basic(optional = false)
	@Column(name = "user_isactive")
	private boolean isactive;

	@Basic(optional = false)
	@OneToMany(mappedBy = "phoneUsuarioId", cascade = CascadeType.ALL, orphanRemoval = true)
	List<PhoneEntity> phones;
	
	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	 
	public List<PhoneEntity> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneEntity> phones) {
		this.phones = phones;
	}

	

}
