package com.mapple.ecommerce.model;


import java.util.List;

public class Usuario {
	
	private String correoUsuario = null;
	private String nombre = null;
	private String apellidos =null;
	private Integer telefono = null;
	private String clave = null;
	private Integer codDireccion = null;
	
	private List<Usuario> usuarios = null;

	public String getCorreoUsuario() {
		return correoUsuario;
	}

	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Integer getCodDireccion() {
		return codDireccion;
	}

	public void setCodDireccion(Integer codDireccion) {
		this.codDireccion = codDireccion;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	

}