package com.mapple.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.mapple.ecommerce.dao.LineaPedidoDAO;
import com.mapple.ecommerce.dao.impl.LineaPedidoDAOImpl;
import com.mapple.ecommerce.dao.util.ConnectionManager;
import com.mapple.ecommerce.dao.util.JDBCUtils;
import com.mapple.ecommerce.exceptions.DataException;
import com.mapple.ecommerce.exceptions.DuplicateInstanceException;
import com.mapple.ecommerce.exceptions.InstanceNotFoundException;
import com.mapple.ecommerce.model.LineaPedido;
import com.mapple.ecommerce.model.LineaPedidoId;
import com.mapple.ecommerce.service.LineaPedidoService;

public class LineaPedidoServiceImpl implements LineaPedidoService{
	
	private LineaPedidoDAO dao = null;
		
		public LineaPedidoServiceImpl() {
			dao = new LineaPedidoDAOImpl();
		}
		
		public LineaPedido findById(LineaPedidoId id) 
				throws InstanceNotFoundException, DataException {
					
			Connection connection = null;
			
			try {
				
				connection = ConnectionManager.getConnection();
				connection.setAutoCommit(true);
				
				return dao.findById(connection, id );	
				
			} catch (SQLException e){
				throw new DataException(e);
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						throw new DataException(e);
					}
				}
			}
			
		}

		public Boolean exists(LineaPedidoId id) 
				throws DataException {
					
			Connection connection = null;
			
			try {
				
				connection = ConnectionManager.getConnection();
				connection.setAutoCommit(true);
				
				return dao.exists(connection, id);
				
			} catch (SQLException e){
				throw new DataException(e);
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						throw new DataException(e);
					}
				}
			}
			
		}

	

		
		public List<LineaPedido> findByPedido(int startIndex, int pageSize, Long codPedido) 
				throws DataException {
			
			Connection connection = null;
			
			try {
				
				connection = ConnectionManager.getConnection();
				connection.setAutoCommit(true);
				
				return dao.findByPedido(connection, startIndex, pageSize, codPedido);
				
			} catch (SQLException e){
				throw new DataException(e);
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						throw new DataException(e);
					}
				}
			}
		}
		
		
		public LineaPedido create(LineaPedido lp) 
				throws DuplicateInstanceException, DataException {
			
		    Connection connection = null;
	        boolean commit = false;

	        try {

	          
	            connection = ConnectionManager.getConnection();

	            connection.setTransactionIsolation(
	                    Connection.TRANSACTION_READ_COMMITTED);

	            connection.setAutoCommit(false);

	            // Execute action
	            LineaPedido result = dao.create(connection, lp);
	            commit = true;
	            
	            return result;

	        } catch (SQLException e) {
	            throw new DataException(e);
	        } finally {
	        	JDBCUtils.closeConnection(connection, commit);
	        }		
		}

		

		public long delete(LineaPedidoId id) 
				throws InstanceNotFoundException, DataException {
			
		    Connection connection = null;
	        boolean commit = false;

	        try {
	          
	            connection = ConnectionManager.getConnection();

	            connection.setTransactionIsolation(
	                    Connection.TRANSACTION_READ_COMMITTED);

	            connection.setAutoCommit(false);

	            // Execute action
	            long result = dao.delete(connection, id);            
	            commit = true;            
	            return result;
	            
	        } catch (SQLException e) {
	            throw new DataException(e);
	        } finally {
	        	JDBCUtils.closeConnection(connection, commit);
	        }		
		}

		


	}
