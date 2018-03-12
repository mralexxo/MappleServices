package com.mapple.ecommerce.model;

public class LineaPedido {

	private LineaPedidoId id = null;
	private Double precioUnidad = (Double) null;
	private Integer cantidad = null;
	
	

	public LineaPedido () {
		
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



	public LineaPedidoId getId() {
		return id;
	}



	public void setId(LineaPedidoId id) {
		this.id = id;
	}


	
	

}
