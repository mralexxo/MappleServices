package com.mapple.ecommerce.model;


public class Categoria {
	

	private Long codCategoria = null;
	private String Nombre = null;
		
	
	public Categoria() {
		
	}

	public Long getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(Long codCategoria) {
		this.codCategoria = codCategoria;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	

}
