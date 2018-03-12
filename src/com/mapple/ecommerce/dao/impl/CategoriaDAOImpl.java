package com.mapple.ecommerce.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mapple.ecommerce.dao.CategoriaDAO;
import com.mapple.ecommerce.dao.util.JDBCUtils;
import com.mapple.ecommerce.exceptions.DataException;
import com.mapple.ecommerce.exceptions.InstanceNotFoundException;
import com.mapple.ecommerce.model.Categoria;

public class CategoriaDAOImpl implements CategoriaDAO {
		
	
		public CategoriaDAOImpl() {
		}
		
		
		@Override
		public Boolean exists(Connection connection, Long codCategoria, String idioma) 
				throws DataException {
			
			boolean exist = false;

			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {

				String queryString = 
								"SELECT c.codCategoria, ci.nombre " + 
								"FROM Categoria c " +
								"INNER JOIN Categoria_Idioma ci " +
								"ON c.codCategoria = ci.codCategoria " +
								"WHERE c.codCategoria = ? AND ci.codIdioma = ? ";


				preparedStatement = connection.prepareStatement(queryString);

				int i = 1;
				preparedStatement.setLong(i++, codCategoria);
				preparedStatement.setString(i++, idioma);


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
		public Categoria findById(Connection connection, Long codCategoria, String idioma) 
				throws InstanceNotFoundException, DataException {

			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {          

				String queryString = 
							"SELECT c.codCategoria, ci.nombre " + 
									"FROM Categoria c " +
									"INNER JOIN Categoria_Idioma ci " +
									"ON c.codCategoria = ci.codCategoria " +
									"WHERE c.codCategoria = ? AND ci.codIdioma = ? ";


				preparedStatement = connection.prepareStatement(queryString,
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

				int i = 1;                
				preparedStatement.setLong(i++, codCategoria);
				preparedStatement.setString(i++, idioma);


				// Execute query            
				resultSet = preparedStatement.executeQuery();

				Categoria e = null;

				if (resultSet.next()) {
					e = loadNext(connection, resultSet);				
				} else {
					throw new InstanceNotFoundException("Categories with id " + codCategoria + 
							"not found", Categoria.class.getName());
				}

				return e;

			} catch (SQLException e) {
				throw new DataException(e);
			} finally {            
				JDBCUtils.closeResultSet(resultSet);
				JDBCUtils.closeStatement(preparedStatement);
			}  
		}
		
		
		@Override
		public List<Categoria> findAll(Connection connection, int startIndex, int pageSize, String idioma) 
						throws DataException {

			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			
			
			try {

				String queryString = 
						"SELECT c.codCategoria, ci.nombre " + 
						"FROM Categoria c " +
						"INNER JOIN Categoria_Idioma ci " +
						"ON c.codCategoria = ci.codCategoria " +
						"WHERE ci.codIdioma = ? " +
						"ORDER BY ci.nombre asc ";

				preparedStatement = connection.prepareStatement(queryString,
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

				int i = 1;
				preparedStatement.setString(i++, idioma);
				
				// Execute query.
				resultSet = preparedStatement.executeQuery();
				
				List<Categoria> results = new ArrayList<Categoria>();                        
				Categoria e = null;
				int currentCount = 0;

				if ((startIndex >=1) && resultSet.absolute(startIndex)) {
					do {
						e = loadNext(connection, resultSet);
						results.add(e);               	
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

		
	
		private Categoria loadNext(Connection connection, ResultSet resultSet) 
				throws SQLException, DataException {

			int i = 1;
			Long codCategoria = resultSet.getLong(i++);
			String nombre = resultSet.getString(i++);


			Categoria c = new Categoria();
			c.setCodCategoria(codCategoria);
			c.setNombre(nombre);


			return c;
		}

}