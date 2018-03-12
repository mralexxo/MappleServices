package com.mapple.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.mapple.ecommerce.exceptions.DataException;
import com.mapple.ecommerce.exceptions.DuplicateInstanceException;
import com.mapple.ecommerce.model.Pedido;

public interface PedidoDAO {

	public Pedido findById(Connection connection, Long id) 
        	throws DataException;
	
	public Boolean exists(Connection connection, Long id) 
    		throws DataException;
	    
    public List<Pedido> findByUsuario(Connection connection, int startIndex, int pageSize, String correoUsuario) 
        	throws DataException;
  
    public Pedido create(Connection connection, Pedido p) 
    		throws DuplicateInstanceException, DataException;
}
        
