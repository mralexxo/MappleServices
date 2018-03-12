package com.mapple.ecommerce.service;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mapple.ecommerce.model.Pedido;
import com.mapple.ecommerce.service.impl.PedidoServiceImpl;
import com.mapple.ecommerce.util.ToStringUtil;

public class PedidoServiceTest {
	
	private static Logger logger = LogManager.getLogger(PedidoServiceTest.class.getName());
	
	private PedidoService PedidoService = null;
	
	public PedidoServiceTest() {
		PedidoService = new PedidoServiceImpl();
	}
	
	protected void testFindById() {
		logger.info("Testing findById ...");
		
		Long id = 1L;
		
		try {			
			Pedido p = PedidoService.findById(id);			
			logger.info("Found: "+ToStringUtil.toString(p));
			
		} catch (Throwable t) {
			logger.error("id = "+id, t);
		}
		
		logger.info("Test testFindById finished.\n");		
	}
	
	protected void testExists() {
		logger.info("Testing exists ...");
				Long id = 6L;
		
		try {			
			Boolean exists = PedidoService.exists(id);			
			logger.info("Exists: "+id+" -> "+exists);
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
		logger.info("Test exists finished.\n");		
	}
	
	
	protected void testFindByUsuario() {
		logger.info("Testing findByUsuario ...");
		
		int pageSize = 5; 
		
		String correoUsuario = "amr@gmail.com" ;
		
		try {

			List<Pedido> results = null;
			int startIndex = 1; 
			int total = 0;
			
			do {
				results = PedidoService.findByUsuario(startIndex, pageSize, correoUsuario);
				if (results.size()>0) {
					logger.info("Page ["+startIndex+" - "+(startIndex+results.size()-1)+"] : ");				
					for (Pedido p: results) {
						total++;
						logger.info("Result "+total+": "+ToStringUtil.toString(p));
					}
					startIndex = startIndex + pageSize;
				}
				
			} while (results.size()==pageSize);
			
			logger.info("Found "+total+" results.");
						
		} catch (Throwable t) {
			t.printStackTrace();
		}
		logger.info("Test testFindByUsuario finished.\n");
	}
	
	
	
	
	
	protected void testCreate() {		
		logger.info("Testing create ...");	
		
		Date localDate = new Date();

		
		try {
		Pedido p = new Pedido();
		p.setCodPedido(25L);
		p.setFecha(localDate);
		p.setImporteTotal(12500.00);
		p.setCorreoUsuario("hld@gmail.com");
	
			
			p = PedidoService.create(p);
			
			logger.info("Created: "+ToStringUtil.toString(p));
					
		} catch (Throwable t) {
			logger.error(t.getMessage(), t);
		}
		logger.info("Test created finished.\n");		
	}
	
		
		
	
	public static void main(String args[]) {
		PedidoServiceTest test = new PedidoServiceTest();
		test.testFindById();	
		test.testExists();
		test.testFindByUsuario();
		test.testCreate();
	}

}

