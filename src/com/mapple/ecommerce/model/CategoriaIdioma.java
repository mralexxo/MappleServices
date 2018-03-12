package com.mapple.ecommerce.model;

public class CategoriaIdioma {
	
	private String codIdioma;
	private Integer codCategoria;
	private String nombre;

	
	
	public CategoriaIdioma () {
		
	}
	
	
	public String getCodIdioma() {
		return codIdioma;
	}
	public void setCodIdioma(String codIdioma) {
		this.codIdioma = codIdioma;
	}
	public Integer getCodCategoria() {
		return codCategoria;
	}
	public void setCodCategoria(Integer codCategoria) {
		this.codCategoria = codCategoria;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
