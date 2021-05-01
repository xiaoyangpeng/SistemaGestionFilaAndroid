package com.example.queue.listaproducto.productos;

import java.io.Serializable;
import java.math.BigDecimal;

public class Mercancia implements Serializable {
	
	
	

	private int id_producto;
	

	private String url_foto;
	
	
	private int stock;
	
	
	private String descripcion;

	private int cantidad;
	
	

	public String getUrl_foto() {
		return url_foto;
	}


	public void setUrl_foto(String url_foto) {
		this.url_foto = url_foto;
	}




	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public int getId_producto() {
		return id_producto;
	}


	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
	
}
