package com.bci.usuario.vo;

public class ErrorVO {

	private String mensaje;
	
	public ErrorVO(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
