package com.mapple.ecommerce.service;


import java.util.List;

import com.mapple.ecommerce.exceptions.DataException;
import com.mapple.ecommerce.exceptions.InstanceNotFoundException;
import com.mapple.ecommerce.model.Categoria;


public interface CategoriaService {
	
	public Categoria findById(Long codCategoria, String idioma) 
			throws InstanceNotFoundException, DataException;

	public Boolean exists(Long codCategoria, String idioma) 
			throws DataException;
	
	public List<Categoria> findAll(int startIndex, int pageSize, String idioma)
			throws DataException;



}
