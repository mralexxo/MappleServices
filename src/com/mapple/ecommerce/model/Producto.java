package com.mapple.ecommerce.model;

public class Producto {
	
	private Long codProducto = null;
	private String nombre = null;
	private String descripcion = null;
	private Double precioUnitario = (Double) null;
	private Integer unidadesEnStock = null;
	private Long codCategoria =null;
	private Double medidaPantalla = (Double) null;
	
	
	public Producto () {
		
	}

	public Long getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(Long codProducto) {
		this.codProducto = codProducto;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Integer getUnidadesEnStock() {
		return unidadesEnStock;
	}

	public void setUnidadesEnStock(Integer unidadesEnStock) {
		this.unidadesEnStock = unidadesEnStock;
	}

	public Long getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(Long codCategoria) {
		this.codCategoria = codCategoria;
	}

	public Double getMedidaPantalla() {
		return medidaPantalla;
	}

	public void setMedidaPantalla(Double medidaPantalla) {
		this.medidaPantalla = medidaPantalla;
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