package com.mapple.ecommerce.model;

import java.util.Date;
import java.util.List;

import com.mapple.ecommerce.model.LineaPedido;

public class Pedido {
	
	private Long codPedido = null;
	private Date fecha = null;
	private Double importeTotal = (Double) null;
	private String correoUsuario = null;
	
	public Pedido() {
		
	}
	
	private List<LineaPedido> lineas = null;

	
	
	public Long getCodPedido() {
		return codPedido;
	}
	public void setCodPedido(Long codPedido) {
		this.codPedido = codPedido;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}
	public String getCorreoUsuario() {
		return correoUsuario;
	}
	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}
	
	public List<LineaPedido> getLineas() {
		return lineas;
	}

	public void setLineas(List<LineaPedido> lineas) {
		this.lineas = lineas;
	}
	
	
}
