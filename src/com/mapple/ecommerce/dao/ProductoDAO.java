package com.mapple.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.mapple.ecommerce.exceptions.DataException;
import com.mapple.ecommerce.exceptions.InstanceNotFoundException;
import com.mapple.ecommerce.model.Producto;
import com.mapple.ecommerce.service.ProductoCriteria;




public interface ProductoDAO {
	
	public Producto findById(Connection connection, Long id, String idioma) 
			throws InstanceNotFoundException, DataException;
	
    public List<Producto> findByCriteria(Connection connection, ProductoCriteria c, int startIndex, int count, String idioma)
        	throws DataException;
    
}

