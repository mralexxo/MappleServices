package com.mapple.ecommerce.service;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mapple.ecommerce.model.Producto;
import com.mapple.ecommerce.service.impl.ProductoServiceImpl;
import com.mapple.ecommerce.util.ToStringUtil;


public class ProductoServiceTest {
	
	private static Logger logger = LogManager.getLogger(ProductoServiceTest.class.getName());

	private ProductoService productoService = null;
	
	public ProductoServiceTest() {
		productoService = new ProductoServiceImpl();
	}
	 
	
	protected void testFindById() {
		logger.info("Testing findById ...");
		
		Long id = (long) 2;
		String idioma = "es";
		
		try {			
			Producto pro = productoService.findById(id, idioma);	
			
			System.out.println("Found: "+ToStringUtil.toString(pro));
			
		} catch (Throwable t) {
			logger.info("id = "+id, t);
		}
		
		logger.info("Test testFindById finished.\n");		
	}
	
	
	
	protected void testFindByCriteria() {
		
		logger.info("Testing FindByCriteria ...");
		int pageSize = 12;
		
		ProductoCriteria p = new ProductoCriteria();
		//p.setCodCategoria(1L);
		p.setNombre(" ");
//		p.setPrecioDesde(1.0);
//		p.setPrecioHasta(100000.0);

		try {

			List<Producto> results = null;
			int startIndex = 1; 
			int total = 0;
			
			do {
				results = productoService.findByCriteria(p, startIndex, pageSize,"ES");
				if (results.size()>0) {
					logger.info("Page ["+startIndex+" - "+(startIndex+results.size()-1)+"] : ");				
					for (Producto t: results) {
						total++;
						logger.info("Result "+total+": "+ToStringUtil.toString(t));
					}
					startIndex = startIndex + pageSize;
				}
				
			} while (results.size()==pageSize);
			
			logger.info("Found "+total+" results.");
						
		} catch (Throwable t) {
			logger.info("id = "+p.getCodCategoria(), t);
		}
		logger.info("Test FindByCriteria finished.\n");
	}

	
	public static void main(String args[]) {
		ProductoServiceTest test = new ProductoServiceTest();
//		test.testFindById();
		test.testFindByCriteria();

		
	}
}



