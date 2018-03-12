package com.mapple.ecommerce.service;

//import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mapple.ecommerce.model.LineaPedido;
import com.mapple.ecommerce.model.LineaPedidoId;
import com.mapple.ecommerce.service.impl.LineaPedidoServiceImpl;
import com.mapple.ecommerce.util.ToStringUtil;

public class LineaPedidoServiceTest {
	
	private static Logger logger = LogManager.getLogger(LineaPedidoServiceTest.class.getName());
	
	private LineaPedidoService LineaPedidoService = null;
	
	public LineaPedidoServiceTest() {
		LineaPedidoService = new LineaPedidoServiceImpl();
	}
	
	protected void testFindById() {
		logger.info("Testing findById ...");
		
		LineaPedidoId pedido = new LineaPedidoId();
		pedido.setCodPedido(1L);
		pedido.setCodProducto(1L);
		
		try {			
			LineaPedido lp = LineaPedidoService.findById(pedido);			
			logger.info("Found: "+ToStringUtil.toString(lp));
			
		} catch (Throwable t) {
			logger.error("id = "+pedido, t);
		}
		
		logger.info("Test testFindById finished.\n");		
	}
	
	protected void testExists() {
		logger.info("Testing exists ...");

		LineaPedidoId pedido = new LineaPedidoId();
		pedido.setCodPedido(4L);
		pedido.setCodProducto(4L);
		
		try {			
			Boolean exists = LineaPedidoService.exists(pedido);			
			logger.info("Exists: "+pedido+" -> "+exists);
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
		logger.info("Test exists finished.\n");		
	}
	
	
	protected void testFindByPedido() {
		logger.info("Testing findByPedido ...");
		
		int pageSize = 2; 
		Long codPedido = 4L;
		
		try {

			List<LineaPedido> results = null;
			int startIndex = 1; 
			int total = 0;
			
			do {
				results = LineaPedidoService.findByPedido(startIndex, pageSize, codPedido);
				if (results.size()>0) {
					logger.info("Page ["+startIndex+" - "+(startIndex+results.size()-1)+"] : ");				
					for (LineaPedido lp: results) {
						total++;
						logger.info("Result "+total+": "+ToStringUtil.toString(lp));
					}
					startIndex = startIndex + pageSize;
				}
				
			} while (results.size()==pageSize);
			
			logger.info("Found "+total+" results.");
						
		} catch (Throwable t) {
			t.printStackTrace();
		}
		logger.info("Test testFindByPedido finished.\n");
	}
	
	
	
	
	
	protected void testCreate() {		
		logger.info("Testing create ...");	
		
		LineaPedidoId idLineaPedido = new LineaPedidoId();
		idLineaPedido.setCodPedido(2L);
		idLineaPedido.setCodProducto(15L);
		
		try {
			
			
		LineaPedido lp = new LineaPedido();
		
		lp.setId(idLineaPedido);
		lp.setPrecioUnidad(450.00);
		lp.setCantidad(3);
	
			
			lp = LineaPedidoService.create(lp);
			
			logger.info("Created: "+ToStringUtil.toString(lp));
					
		} catch (Throwable t) {
			logger.error(t.getMessage(), t);
		}
		logger.info("Test created finished.\n");		
	}
	
	
//	protected void testDelete() {		
//		logger.info("Testing delete ...");	
//		
//		LineaPedidoId pedido = new LineaPedidoId();
//		pedido.setCodPedido(20L);
//		pedido.setCodProducto(15L);
//		
//		try {
//
//			List<LineaPedido> results = 
//					LineaPedidoService.findByPedido(1, 10, 20L);
//			if (results.size()!=1) {
//				throw new RuntimeException("Unexpected results from previous test");
//			}
//			
//			LineaPedido e = results.get(0);
//			logger.info("Deleting by id "+e.getCodLineaPedido());
//			LineaPedidoService.delete(e.getCodLineaPedido());
//			
//			try {
//				e = LineaPedidoService.findById(e.getCodLineaPedido());
//				logger.info("Delete NOK!");
//			} catch (InstanceNotFoundException ine) {
//				logger.info("Delete OK");
//			}						
//								
//		} catch (Throwable t) {
//			t.printStackTrace();
//		}
//		logger.info("Test delete finished.\n");		
//	}		
//		
	
	public static void main(String args[]) {
		LineaPedidoServiceTest test = new LineaPedidoServiceTest();
		test.testFindById();	
		test.testExists();
		test.testFindByPedido();
		test.testCreate();
//		test.testDelete();
	}

}

