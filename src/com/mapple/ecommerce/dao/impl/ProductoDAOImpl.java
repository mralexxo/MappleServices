package com.mapple.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mapple.ecommerce.dao.ProductoDAO;
import com.mapple.ecommerce.dao.util.JDBCUtils;
import com.mapple.ecommerce.exceptions.DataException;
import com.mapple.ecommerce.exceptions.InstanceNotFoundException;
import com.mapple.ecommerce.model.Producto;
import com.mapple.ecommerce.service.ProductoCriteria;

public class ProductoDAOImpl implements ProductoDAO {
	
	private static Logger logger = LogManager.getLogger(ProductoDAOImpl.class.getName());


	public ProductoDAOImpl() {
	}

	@Override
	public Producto findById(Connection connection, Long id, String idioma)
			throws InstanceNotFoundException, DataException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			String queryString = " SELECT  p.codProducto, pi.nombre, pi.descripcion, p.precioUnitario, p.unidadesEnStock, p.codCategoria, p.medidaPantalla "
					+ " FROM Producto p " + " INNER JOIN Producto_Idioma pi ON p.codProducto = pi.codProducto "
					+ " WHERE p.codProducto = ? AND pi.codIdioma = ? ";

			preparedStatement = connection.prepareStatement(queryString, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setLong(i++, id);
			preparedStatement.setString(i++, idioma);

			// Execute query
			resultSet = preparedStatement.executeQuery();

			Producto e = null;

			if (resultSet.next()) {
				e = loadNext(resultSet);
			} else {
				throw new InstanceNotFoundException("Products with id " + id + "not found", Producto.class.getName());
			}

			return e;

		} catch (SQLException e) {
			logger.error(e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public List<Producto> findByCriteria(Connection connection, ProductoCriteria producto, int startIndex, int count,
			String idioma) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;

		try {
			queryString = new StringBuilder(
					"SELECT  p.codProducto, pi.nombre, pi.descripcion, p.precioUnitario, p.unidadesEnStock, p.codCategoria, p.medidaPantalla "
							+ " FROM Producto p "
							+ " INNER JOIN Producto_Idioma pi ON p.codProducto = pi.codProducto ");

			// Marca (flag) de primera clausula, que se desactiva en la primera
			boolean first = true;

			if (producto.getCodCategoria() != null) {
				addClause(queryString, first, " p.codCategoria = ? ");
				first = false;
			}

			if (producto.getMedidaPantalla() != null) {
				addClause(queryString, first, " p.medidaPantalla LIKE ? "); // TODO
				first = false;
			}

			if (producto.getPrecioDesde() != null) {
				addClause(queryString, first, " p.precioUnitario > ? ");
				first = false;
			}

			if (producto.getPrecioHasta() != null) {
				addClause(queryString, first, " p.precioUnitario < ? ");
				first = false;
			}
			
			if (!StringUtils.isEmpty(producto.getNombre())) {
				addClause(queryString, first, " UPPER(pi.nombre) LIKE UPPER(?) ");
				first = false;
			}

			if (idioma != null) {
				addClause(queryString, first, " pi.codIdioma = ? ");
				first = false;
			}

			logger.info("Query: "+queryString);
			preparedStatement = connection.prepareStatement(queryString.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			int i = 1;

			if (producto.getCodCategoria() != null)
				preparedStatement.setLong(i++, producto.getCodCategoria());

			if (!StringUtils.isEmpty(producto.getNombre()))
				preparedStatement.setString(i++, "'%"+producto.getNombre()+"%'");

			if (producto.getMedidaPantalla() != null)
				preparedStatement.setDouble(i++, producto.getMedidaPantalla());

			if (producto.getPrecioDesde() != null)
				preparedStatement.setDouble(i++, producto.getPrecioDesde());

			if (producto.getPrecioHasta() != null)
				preparedStatement.setDouble(i++, producto.getPrecioHasta());
			
			if (idioma != null)
				preparedStatement.setString(i++, idioma);

			resultSet = preparedStatement.executeQuery();
			List<Producto> results = new ArrayList<Producto>();
			Producto e = null;
			int currentCount = 0;

			if ((startIndex >= 1) && resultSet.absolute(startIndex)) {
				do {
					e = loadNext(resultSet);
					results.add(e);
					currentCount++;
				} while ((currentCount < count) && resultSet.next());
			}

			return results;

		} catch (SQLException e) {
			logger.error("Error",e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	private void addClause(StringBuilder queryString, boolean first, String clause) {
		queryString.append(first ? "WHERE " : " AND ").append(clause);
	}

	private Producto loadNext(ResultSet resultSet) throws SQLException, DataException {
		int i = 1;

		Long codProducto = resultSet.getLong(i++);
		String nombre = resultSet.getString(i++);
		String descripcion = resultSet.getString(i++);
		Double precioUnitario = resultSet.getDouble(i++);
		Integer unidadesEnStock = resultSet.getInt(i++);
		Long codCategoria = resultSet.getLong(i++);
		Double medidaPantalla = resultSet.getDouble(i++);

		Producto p = new Producto();
		p.setCodProducto(codProducto);
		p.setNombre(nombre);
		p.setDescripcion(descripcion);
		p.setPrecioUnitario(precioUnitario);
		p.setUnidadesEnStock(unidadesEnStock);
		p.setCodCategoria(codCategoria);
		p.setMedidaPantalla(medidaPantalla);
		

		return p;
	}

}
