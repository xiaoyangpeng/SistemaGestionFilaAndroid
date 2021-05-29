package com.example.queue.guardarDatoSharedPre.guardarDatoCuenta.api;

import java.math.BigDecimal;

public class Usuario {

	private String nombre;

	private String sexo;

	private String email;

	private BigDecimal telefono;


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public BigDecimal getTelefono() {
		return telefono;
	}


	public void setTelefono(BigDecimal telefono) {
		this.telefono = telefono;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}







}
