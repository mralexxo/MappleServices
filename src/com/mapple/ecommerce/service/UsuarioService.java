package com.mapple.ecommerce.service;

import java.util.List;

import com.mapple.ecommerce.exceptions.DataException;
import com.mapple.ecommerce.exceptions.DuplicateInstanceException;
import com.mapple.ecommerce.exceptions.InstanceNotFoundException;
import com.mapple.ecommerce.model.Usuario;

public interface UsuarioService {
     
     public Usuario create(Usuario u) 
     		throws DuplicateInstanceException, DataException;

     public void update(Usuario u) 
     		throws InstanceNotFoundException, DataException;


}