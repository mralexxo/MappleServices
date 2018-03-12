package com.mapple.ecommerce.service;

import java.util.List;

import com.mapple.ecommerce.exceptions.DataException;
import com.mapple.ecommerce.exceptions.DuplicateInstanceException;
import com.mapple.ecommerce.exceptions.InstanceNotFoundException;
import com.mapple.ecommerce.model.Usuario;

public interface UsuarioService {
	
	public Boolean exists(String correoUsuario) 
			throws DataException;
	
	public Usuario findById(String correoUsuario) 
			throws InstanceNotFoundException, DataException;
     
     public Usuario create(Usuario u) 
     		throws DuplicateInstanceException, DataException;

     public void update(Usuario u) 
     		throws InstanceNotFoundException, DataException;


}