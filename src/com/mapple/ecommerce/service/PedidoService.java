package com.mapple.ecommerce.service;

import java.util.List;

import javax.management.InstanceNotFoundException;

import com.mapple.ecommerce.exceptions.DataException;
import com.mapple.ecommerce.exceptions.DuplicateInstanceException;
import com.mapple.ecommerce.model.Pedido;

public interface PedidoService {
	
	public Pedido findById(Long id) 
			throws InstanceNotFoundException, DataException;
    
    public Boolean exists(Long id) 
    		throws DataException;        
     
     public List<Pedido> findByUsuario(int startIndex, int pageSize, String correoUsuario)
     		throws DataException;
     
     public Pedido create(Pedido p) 
     		throws DuplicateInstanceException, DataException;
         
	
}