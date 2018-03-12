package com.mapple.ecommerce.model;

public class LineaPedidoId {
	
	private Long idPedido;
	private Long idProducto;
	
	public LineaPedidoId() {
	}

	public Long getIdTicket() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
}
