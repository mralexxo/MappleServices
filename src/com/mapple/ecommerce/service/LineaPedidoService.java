package com.mapple.ecommerce.service;

import java.util.List;

import javax.management.InstanceNotFoundException;

import com.mapple.ecommerce.exceptions.DataException;
import com.mapple.ecommerce.exceptions.DuplicateInstanceException;
import com.mapple.ecommerce.model.LineaPedido;

public interface LineaPedidoService {
	
	public LineaPedido findById(Long codLineaPedido) 
			throws InstanceNotFoundException, DataException;
    
    public Boolean exists(Long codLineaPedido) 
    		throws DataException;        
     
     public List<LineaPedido> findByPedido(int startIndex, int pageSize, Long codPedido)
     		throws DataException;
     
     public LineaPedido create(LineaPedido lp) 
     		throws DuplicateInstanceException, DataException;
         
     public long delete(Long codLineaPedido) 
     		throws InstanceNotFoundException, DataException;
	
}