package com.mapple.ecommerce.model;

public class LineaPedido {
	
	private Long codLineaPedido = null;
	private Double precioUnidad = (Double) null;
	private Integer cantidad = null;
	private Long codPedido = null;
	private Long codProducto = null;

	public LineaPedido () {
		
	}

	
	public Long getCodLineaPedido() {
		return codLineaPedido;
	}


	public void setCodLineaPedido(Long codLineaPedido) {
		this.codLineaPedido = codLineaPedido;
	}


	public Double getPrecioUnidad() {
		return precioUnidad;
	}


	public void setPrecioUnidad(Double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public Long getCodPedido() {
		return codPedido;
	}


	public void setCodPedido(Long codPedido) {
		this.codPedido = codPedido;
	}


	public Long getCodProducto() {
		return codProducto;
	}


	public void setCodProducto(Long codProducto) {
		this.codProducto = codProducto;
	}


	
	

}
