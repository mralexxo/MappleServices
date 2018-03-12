package com.mapple.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.mapple.ecommerce.dao.PedidoDAO;
import com.mapple.ecommerce.dao.impl.PedidoDAOImpl;
import com.mapple.ecommerce.dao.util.ConnectionManager;
import com.mapple.ecommerce.dao.util.JDBCUtils;
import com.mapple.ecommerce.exceptions.DataException;
import com.mapple.ecommerce.exceptions.DuplicateInstanceException;
import com.mapple.ecommerce.exceptions.InstanceNotFoundException;
import com.mapple.ecommerce.model.Pedido;
import com.mapple.ecommerce.service.PedidoService;

public class PedidoServiceImpl implements PedidoService{
	
	private PedidoDAO dao = null;
		
		public PedidoServiceImpl() {
			dao = new PedidoDAOImpl();
		}
		
		public Pedido findById(Long id) 
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

		public Boolean exists(Long id) 
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

	

		
		public List<Pedido> findByUsuario(int startIndex, int pageSize, String correoUsuario) 
				throws DataException {
			
			Connection connection = null;
			
			try {
				
				connection = ConnectionManager.getConnection();
				connection.setAutoCommit(true);
				
				return dao.findByUsuario(connection, startIndex, pageSize, correoUsuario);	
				
			} catch (SQLException e){
				throw new DataException(e);
			} finally {
				JDBCUtils.closeConnection(connection);
			}
			
		}

		
		
		public Pedido create(Pedido p) 
				throws DuplicateInstanceException, DataException {
			
		    Connection connection = null;
	        boolean commit = false;

	        try {

	          
	            connection = ConnectionManager.getConnection();

	            connection.setTransactionIsolation(
	                    Connection.TRANSACTION_READ_COMMITTED);

	            connection.setAutoCommit(false);

	            // Execute action
	            Pedido result = dao.create(connection, p);
	            commit = true;
	            
	            return result;

	        } catch (SQLException e) {
	            throw new DataException(e);
	        } finally {
	        	JDBCUtils.closeConnection(connection, commit);
	        }		
		}

		
	}
