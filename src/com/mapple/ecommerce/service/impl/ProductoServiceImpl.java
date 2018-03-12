package com.mapple.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.mapple.ecommerce.dao.ProductoDAO;
import com.mapple.ecommerce.dao.impl.ProductoDAOImpl;
import com.mapple.ecommerce.dao.util.ConnectionManager;
import com.mapple.ecommerce.dao.util.JDBCUtils;
import com.mapple.ecommerce.exceptions.DataException;
import com.mapple.ecommerce.exceptions.InstanceNotFoundException;
import com.mapple.ecommerce.model.Producto;
import com.mapple.ecommerce.service.ProductoCriteria;
import com.mapple.ecommerce.service.ProductoService;

public class ProductoServiceImpl implements ProductoService{

	private ProductoDAO dao = null;
	
	public ProductoServiceImpl() {
		dao = new ProductoDAOImpl();
	}

	
	
	
	public Producto findById(Long id, String idioma) 
			throws InstanceNotFoundException, DataException {
		
		Connection connection = null;
		
		try {
			
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);
			
			return dao.findById(connection, id, idioma);	
			
		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
		
	}
	
     public List<Producto> findByCriteria(ProductoCriteria c, int startIndex, int count, String idioma)
			throws DataException {
			
		Connection connection = null;
		
		try {
			
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);
			
			return dao.findByCriteria(connection, c, startIndex, count, idioma);
			
		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}


}


