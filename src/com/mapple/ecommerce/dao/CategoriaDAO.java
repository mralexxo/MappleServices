package com.mapple.ecommerce.dao; 

import java.sql.Connection;
import java.util.List;

import com.mapple.ecommerce.exceptions.DataException;
import com.mapple.ecommerce.exceptions.InstanceNotFoundException;
import com.mapple.ecommerce.model.Categoria;


public interface CategoriaDAO {
    
    public Boolean exists(Connection connection, Long codCategoria, String idioma) 
    		throws DataException;
    
	public Categoria findById(Connection connection, Long codCategoria, String idioma) 
			throws InstanceNotFoundException, DataException;
	
    public List<Categoria> findAll(Connection connection, int startIndex, int pageSize, String idioma) 
        	throws DataException;

	

}