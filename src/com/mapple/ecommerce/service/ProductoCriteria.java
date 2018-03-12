package com.mapple.ecommerce.service;

public class ProductoCriteria {
	
	private Long codCategoria = null;
	private Double medidaPantalla = null;
	private Double precioDesde = null;
	private Double precioHasta = null;
	
	
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
	public Double getPrecioDesde() {
		return precioDesde;
	}
	public void setPrecioDesde(Double precioDesde) {
		this.precioDesde = precioDesde;
	}
	public Double getPrecioHasta() {
		return precioHasta;
	}
	public void setPrecioHasta(Double precioHasta) {
		this.precioHasta = precioHasta;
	}

	
	

}
