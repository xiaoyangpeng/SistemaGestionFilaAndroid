package com.example.queue.listaproducto.productos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Comida implements Serializable {

	
	private int id_producto;
	
	
	private String ingrediente;
	
	private String url_foto;

	private int cantidad;
	
	
	
	

	public int getId_producto() {
		return id_producto;
	}


	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	public String getIngrediente() {
		return ingrediente;
	}


	public void setIngrediente(String ingrediente) {
		this.ingrediente = ingrediente;
	}


	public String getUrl_foto() {
		return url_foto;
	}


	public void setUrl_foto(String url_foto) {
		this.url_foto = url_foto;
	}





	
	
	
	
	
}
