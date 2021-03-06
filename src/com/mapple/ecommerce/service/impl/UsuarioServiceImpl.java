package com.mapple.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


import com.mapple.ecommerce.dao.UsuarioDAO;
import com.mapple.ecommerce.dao.impl.UsuarioDAOImpl;
import com.mapple.ecommerce.dao.util.ConnectionManager;
import com.mapple.ecommerce.dao.util.JDBCUtils;
import com.mapple.ecommerce.exceptions.MailException;
import com.mapple.ecommerce.service.MailService;
import com.mapple.ecommerce.exceptions.DataException;
import com.mapple.ecommerce.exceptions.DuplicateInstanceException;
import com.mapple.ecommerce.exceptions.InstanceNotFoundException;
import com.mapple.ecommerce.model.Usuario;
import com.mapple.ecommerce.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {
	
	private UsuarioDAO dao = null;
	private static ResourceBundle dbConfiguration = ResourceBundle.getBundle("ServiceConfiguration");
		
		public UsuarioServiceImpl() {
			dao = new UsuarioDAOImpl();
		}
			
		
		public Boolean exists(String correoUsuario) 
				throws DataException {
					
			Connection connection = null;
			
			try {
				
				connection = ConnectionManager.getConnection();
				connection.setAutoCommit(true);
				
				return dao.exists(connection, correoUsuario);
				
			} catch (SQLException e){
				throw new DataException(e);
			} finally {
				JDBCUtils.closeConnection(connection);
			}
			
		}
		
		public Usuario findById(String correoUsuario) 
				throws InstanceNotFoundException, DataException {
					
			Connection connection = null;
			
			try {
				
				connection = ConnectionManager.getConnection();
				connection.setAutoCommit(true);
				
				return dao.findById(connection, correoUsuario);	
				
			} catch (SQLException e){
				throw new DataException(e);
			} finally {
				JDBCUtils.closeConnection(connection);
			}
			
		}

		public Usuario create(Usuario u) 
				throws DuplicateInstanceException, DataException, MailException {
			
		    Connection connection = null;
	        boolean commit = false;

	        try {
	          
	            connection = ConnectionManager.getConnection();

	            connection.setTransactionIsolation(
	                    Connection.TRANSACTION_READ_COMMITTED);

	            connection.setAutoCommit(false);

	            Usuario result = dao.create(connection, u);
	            MailService mail = new MailServiceImpl();
	            mail.sendMail(dbConfiguration.getString("service.mail.subject"), dbConfiguration.getString("service.mail.body"), u.getCorreoUsuario());
	            commit = true;
	            
	            return result;

	        } catch (SQLException e) {
	            throw new DataException(e);

	        } finally {
	        	JDBCUtils.closeConnection(connection, commit);
	        }		
		}

		public void update(Usuario u) 
				throws InstanceNotFoundException, DataException {
			
		    Connection connection = null;
	        boolean commit = false;

	        try {
	          
	            connection = ConnectionManager.getConnection();

	            connection.setTransactionIsolation(
	                    Connection.TRANSACTION_READ_COMMITTED);

	            connection.setAutoCommit(false);

	            dao.update(connection, u);
	            commit = true;
	            
	        } catch (SQLException e) {
	            throw new DataException(e);

	        } finally {
	        	JDBCUtils.closeConnection(connection, commit);
	        }
		}

}
