package com.mapple.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.mapple.ecommerce.exceptions.DataException;
import com.mapple.ecommerce.exceptions.DuplicateInstanceException;
import com.mapple.ecommerce.exceptions.InstanceNotFoundException;
import com.mapple.ecommerce.model.Usuario;

public interface UsuarioDAO {

    
    public Usuario create(Connection connection, Usuario u) 
    		throws DuplicateInstanceException, DataException;

    public void update(Connection connection, Usuario u) 
    		throws InstanceNotFoundException, DataException;
}
        