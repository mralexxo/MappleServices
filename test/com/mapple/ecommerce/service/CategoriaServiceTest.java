package com.mapple.ecommerce.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mapple.ecommerce.model.Categoria;
import com.mapple.ecommerce.service.impl.CategoriaServiceImpl;
import com.mapple.ecommerce.util.ToStringUtil;


public class CategoriaServiceTest {


private static Logger logger = LogManager.getLogger(CategoriaServiceTest.class.getName());

private CategoriaService categoriaService = null;

public CategoriaServiceTest() {
	categoriaService = new CategoriaServiceImpl();
}

protected void testFindById() {
	logger.info("Testing findById ...");
	
	Long codCategoria = 1L;
	String idioma = "ES";
	
	try {			
		Categoria cat = categoriaService.findById(codCategoria, idioma);			
		logger.info("Found: "+ToStringUtil.toString(cat));
		
	} catch (Throwable t) {
		logger.error("id = "+codCategoria, t);
	}
	
	logger.info("Test testFindById finished.\n");		
}


protected void testExists() {
	logger.info("Testing exists ...");

	Long codCategoria = 3L;
	String idioma = "ES";
	
	try {			
		Boolean exists = categoriaService.exists(codCategoria, idioma);			
		logger.info("Exists: "+codCategoria+" -> "+exists);
		
	} catch (Throwable t) {
		logger.error("id = "+codCategoria, t);
	}
	
	logger.info("Test exists finished.\n");		
}


protected void testFindAll() {
	System.out.println("Testing findAll ...");
	
	int pageSize = 4; 
	
	String idioma = "ES";
	
	try {

		List<Categoria> results = null;
		int startIndex = 1; 
		int total = 0;
		
		do {
			results = categoriaService.findAll(startIndex, pageSize, idioma);
			if (results.size()>0) {
				logger.info("Page ["+startIndex+" - "+(startIndex+results.size()-1)+"] : ");				
				for (Categoria cat: results) {
					total++;
					logger.info("Result "+total+": "+ToStringUtil.toString(cat));
				}
				startIndex = startIndex + pageSize;
			}
			
		} while (results.size()==pageSize);
		
		logger.info("Found "+total+" results.");
					
	} catch (Throwable t) {
		t.printStackTrace();
	}
	System.out.println("Test testFindAll finished.\n");
}


public static void main(String args[]) {
	
	CategoriaServiceTest test = new CategoriaServiceTest();
	test.testFindById();
	test.testExists();
	test.testFindAll();
	
	}
}

