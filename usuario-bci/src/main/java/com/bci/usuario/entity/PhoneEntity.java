package com.bci.usuario.entity;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PhoneEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "phone_id")
	private Integer id;
	
	@Basic(optional = false)
	@Column(name = "phone_number")
	private String number;

	@Basic(optional = false)
	@Column(name = "phone_citycode")
	private String citycode;

	@Basic(optional = false)
	@Column(name = "phone_contrycode")
	private String contrycode;

    @JoinColumn(name = "phone_usuario_id", referencedColumnName = "user_id")
    @ManyToOne
    private UserEntity phoneUsuarioId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getContrycode() {
		return contrycode;
	}

	public void setContrycode(String contrycode) {
		this.contrycode = contrycode;
	}

	public UserEntity getPhoneUsuarioId() {
		return phoneUsuarioId;
	}

	public void setPhoneUsuarioId(UserEntity phoneUsuarioId) {
		this.phoneUsuarioId = phoneUsuarioId;
	}
	
}
