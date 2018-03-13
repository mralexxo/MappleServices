package com.mapple.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mapple.ecommerce.dao.LineaPedidoDAO;
import com.mapple.ecommerce.dao.PedidoDAO;
import com.mapple.ecommerce.dao.util.JDBCUtils;
import com.mapple.ecommerce.exceptions.DataException;
import com.mapple.ecommerce.exceptions.DuplicateInstanceException;
import com.mapple.ecommerce.exceptions.InstanceNotFoundException;
import com.mapple.ecommerce.model.LineaPedido;
import com.mapple.ecommerce.model.LineaPedidoId;
import com.mapple.ecommerce.model.Pedido;

public class PedidoDAOImpl implements PedidoDAO{

	
	private LineaPedidoDAO lineaPedidoDAO = null;

	
public PedidoDAOImpl() {
	
}	
	
	@Override
	public Pedido findById(Connection connection, Long id) 
			throws InstanceNotFoundException, DataException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {          
			String queryString = 
					"SELECT codPedido, fecha, importeTotal, correoUsuario " + 
							"FROM Pedido " +
							"WHERE codPedido = ? ";
			
			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;                
			preparedStatement.setLong(i++, id);

			resultSet = preparedStatement.executeQuery();

			Pedido p = null;

			if (resultSet.next()) {
				p = loadNext(connection, resultSet);				
			} else {
				throw new InstanceNotFoundException("Pedido Details not found", Pedido.class.getName());
			}

			return p;

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  
	}
	
	@Override
	public Boolean exists(Connection connection, Long id) 
			throws DataException {
		
		boolean exist = false;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			String queryString = 
					"SELECT codPedido, fecha, importeTotal, correoUsuario " + 
							"FROM Pedido " +
							"WHERE codPedido = ? ";

			preparedStatement = connection.prepareStatement(queryString);
			
			int i = 1;
			preparedStatement.setLong(i++, id);

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
	public List<Pedido> findByUsuario (Connection connection, int startIndex, int pageSize, String correoUsuario)
			throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			String queryString = 
					"SELECT p.codPedido, p.fecha, p.importeTotal, p.correoUsuario " + 
					"FROM Pedido p " +
					"INNER JOIN Usuario u "+
					"ON p.correoUsuario = u.correoUsuario " + 
					"WHERE p.correoUsuario LIKE ? ";

			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setString(i++, correoUsuario);
			
			// Execute query.
			resultSet = preparedStatement.executeQuery();
			
			List<Pedido> results = new ArrayList<Pedido>();                        
			Pedido p = null;
			int currentCount = 0;

			if ((startIndex >=1) && resultSet.absolute(startIndex)) {
				do {
					p = loadNext(connection, resultSet);
					results.add(p);               	
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
	public Pedido create(Connection connection, Pedido p) 
			throws DuplicateInstanceException, DataException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		
		try {          
			
			if (p.getCodPedido()!=null && exists(connection, p.getCodPedido())) {
				throw new DuplicateInstanceException(p.getCodPedido(), Pedido.class.getName());
			}

			String queryString = "INSERT INTO Pedido (fecha, ImporteTotal, correoUsuario) "
								+"VALUES (?, ?, ?) ";

			preparedStatement = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;     
			preparedStatement.setDate(i++, new java.sql.Date(p.getFecha().getTime()));
			preparedStatement.setDouble(i++, p.getImporteTotal());
			preparedStatement.setString(i++, p.getCorreoUsuario());


			
			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Pedido'");
			} 
			
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				Long pk = resultSet.getLong(1); 
				p.setCodPedido(pk);
			} else {
				throw new DataException("Unable to fetch autogenerated primary key");
			}
			
			List<LineaPedido> lineas = p.getLineas();
			LineaPedidoId id = new LineaPedidoId();				


			for (LineaPedido lp: lineas) {
				id.setCodPedido(p.getCodPedido());
				lineaPedidoDAO.create(connection, lp);
			}
			
			return p;

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
	}


	
	private Pedido loadNext(Connection connection, ResultSet resultSet)
		throws SQLException, DataException {

			int i = 1;
			Long codPedido = resultSet.getLong(i++);	
			Date fecha = resultSet.getDate(i++);	  
			Double importeTotal = resultSet.getDouble(i++);
			String correoUsuario = resultSet.getString(i++);	                
                
	
			Pedido p = new Pedido();		
			p.setCodPedido(codPedido);
			p.setFecha(fecha);
			p.setImporteTotal(importeTotal);
			p.setCorreoUsuario(correoUsuario);
			
			return p;
		}

}

