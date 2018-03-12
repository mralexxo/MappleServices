package com.mapple.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.mapple.ecommerce.exceptions.DataException;
import com.mapple.ecommerce.exceptions.DuplicateInstanceException;
import com.mapple.ecommerce.exceptions.InstanceNotFoundException;
import com.mapple.ecommerce.model.LineaPedido;
import com.mapple.ecommerce.model.LineaPedidoId;

public interface LineaPedidoDAO {
	
	public LineaPedido findById(Connection connection, LineaPedidoId id) 
        	throws DataException;
	
	public Boolean exists(Connection connection, LineaPedidoId id) 
    		throws DataException;
	    
    public List<LineaPedido> findByPedido(Connection connection, int startIndex, int pageSize, Long codPedido) 
        	throws DataException;
  
    public LineaPedido create(Connection connection, LineaPedido lp) 
    		throws DuplicateInstanceException, DataException;
        
    public long delete(Connection connection, LineaPedidoId id) 
    		throws InstanceNotFoundException, DataException;

}
