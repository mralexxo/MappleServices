package com.mapple.ecommerce.model;

public class ProductoIdioma {
	
	private String codIdioma;
	private Integer codProducto;
	private String nombre;
	private String descripcion;

	
	public ProductoIdioma() {
		
	}
	
	public String getCodIdioma() {
		return codIdioma;
	}

	public void setCodIdioma(String codIdioma) {
		this.codIdioma = codIdioma;
	}

	public Integer getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(Integer codProducto) {
		this.codProducto = codProducto;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	

}
