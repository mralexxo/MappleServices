package com.mapple.ecommerce.service;

import java.util.List;

import com.mapple.ecommerce.exceptions.DataException;
import com.mapple.ecommerce.exceptions.InstanceNotFoundException;
import com.mapple.ecommerce.model.Producto;

public interface ProductoService {
	
    public Producto findById(Long id, String idioma) 
	    	throws InstanceNotFoundException, DataException;
	
	    public List<Producto> findByCriteria(ProductoCriteria c, int startIndex, int count, String idioma)
	   	    throws DataException;
	    
} 
