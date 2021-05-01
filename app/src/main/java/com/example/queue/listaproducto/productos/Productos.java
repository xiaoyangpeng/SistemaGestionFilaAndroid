package com.example.queue.listaproducto.productos;

import java.io.Serializable;
import java.util.ArrayList;

public class Productos implements Serializable {
	
	
	private int id_producto;
	
	private double precio;
	
	private String nombre;

	
	private String categoria;
	
	
	
	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
	
	
	
	

}




