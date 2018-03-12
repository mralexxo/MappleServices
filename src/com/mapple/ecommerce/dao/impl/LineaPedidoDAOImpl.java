package com.mapple.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mapple.ecommerce.dao.LineaPedidoDAO;
import com.mapple.ecommerce.dao.util.JDBCUtils;
import com.mapple.ecommerce.exceptions.DataException;
import com.mapple.ecommerce.exceptions.DuplicateInstanceException;
import com.mapple.ecommerce.exceptions.InstanceNotFoundException;
import com.mapple.ecommerce.model.LineaPedido;


public class LineaPedidoDAOImpl implements LineaPedidoDAO{

public LineaPedidoDAOImpl() {
	
}
	
	@Override
	public LineaPedido findById(Connection connection, Long codLineaPedido) 
			throws InstanceNotFoundException, DataException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {          
			String queryString = 
					"SELECT codLineaPedido, precioUnidad, cantidad, codPedido, codProducto " + 
							"FROM LineaPedido " +
							"WHERE codLineaPedido = ? ";
			
			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;                
			preparedStatement.setLong(i++, codLineaPedido);

			resultSet = preparedStatement.executeQuery();

			LineaPedido l = null;

			if (resultSet.next()) {
				l = loadNext(connection, resultSet);				
			} else {
				throw new InstanceNotFoundException("Pedido Details not found", LineaPedido.class.getName());
			}

			return l;

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  
	}
	
	@Override
	public Boolean exists(Connection connection, Long codLineaPedido) 
			throws DataException {
		
		boolean exist = false;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			String queryString = 
					"SELECT codLineaPedido, precioUnidad, cantidad, codPedido, codProducto " + 
							"FROM LineaPedido " +
							"WHERE codLineaPedido = ? ";

			preparedStatement = connection.prepareStatement(queryString);
			
			int i = 1;
			preparedStatement.setLong(i++, codLineaPedido);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				exist = true;
			}

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}

		return exist;
	}


	@Override
	public List<LineaPedido> findByPedido (Connection connection, int startIndex, int pageSize, Long codPedido)
			throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			String queryString = 
					"SELECT p.CodPedido, lp.codLineaPedido, lp.precioUnidad, lp.cantidad, lp.codProducto " + 
					"FROM LineaPedido lp " +
					"INNER JOIN Pedido p "+
					"ON p.codPedido = lp.codPedido " + 
					"WHERE p.codPedido = ? ";

			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;                
			preparedStatement.setLong(i++, codPedido);

			// Execute query            
			resultSet = preparedStatement.executeQuery();
			
			List<LineaPedido> results = new ArrayList<LineaPedido>();                        
			LineaPedido lp = null;
			int currentCount = 0;

			if ((startIndex >=1) && resultSet.absolute(startIndex)) {
				do {
					lp = loadNext(connection, resultSet);
					results.add(lp);               	
					currentCount++;                	
				} while ((currentCount < pageSize) && resultSet.next()) ;
			}

			return results;


		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	

	@Override
	public LineaPedido create(Connection connection, LineaPedido lp) 
			throws DuplicateInstanceException, DataException {

		PreparedStatement preparedStatement = null;
		
		try {          
			
			if (exists(connection, lp.getCodLineaPedido())) {
				throw new DuplicateInstanceException(lp.getCodLineaPedido(), LineaPedido.class.getName());
			}

			String queryString = "INSERT INTO LineaPedido (codLineaPedido, precioUnidad, cantidad, codPedido, codProducto) "
								+"VALUES (?, ?, ?, ?, ?) ";

			preparedStatement = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;     
			preparedStatement.setLong(i++, lp.getCodLineaPedido());
			preparedStatement.setDouble(i++, lp.getPrecioUnidad());
			preparedStatement.setLong(i++, lp.getCantidad());
			preparedStatement.setLong(i++, lp.getCodPedido());
			preparedStatement.setLong(i++, lp.getCodProducto());


			
			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'LineaPedido' ");
			}
			
			return lp;

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
	}


	@Override
	public long delete(Connection connection, Long codLineaPedido) 
			throws InstanceNotFoundException, DataException {
		PreparedStatement preparedStatement = null;

		try {

			String queryString =	
					  "DELETE FROM LineaPedido " 
					+ "WHERE codLineaPedido = ? ";
			
			preparedStatement = connection.prepareStatement(queryString);

			int i = 1;
			preparedStatement.setLong(i++, codLineaPedido);

			int removedRows = preparedStatement.executeUpdate();

			if (removedRows == 0) {
				throw new InstanceNotFoundException(codLineaPedido, LineaPedido.class.getName());
			} 
					
			return removedRows;

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
	}
	
	
	private LineaPedido loadNext(Connection connection, ResultSet resultSet)
		throws SQLException, DataException {

			int i = 1;
			Long codlineaPedido = resultSet.getLong(i++);	
			Double precioUnidad = resultSet.getDouble(i++);
			Integer cantidad = resultSet.getInt(i++);	                
			Long codPedido = resultSet.getLong(i++);	                
			Long codProducto = resultSet.getLong(i++);	                
                
	
			LineaPedido lp = new LineaPedido();		
			lp.setCodLineaPedido(codlineaPedido);
			lp.setPrecioUnidad(precioUnidad);
			lp.setCantidad(cantidad);
			lp.setCodPedido(codPedido);
			lp.setCodProducto(codProducto);
			
			return lp;
		}

}

