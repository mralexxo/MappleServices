package com.mapple.ecommerce.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mapple.ecommerce.model.Usuario;
import com.mapple.ecommerce.service.impl.UsuarioServiceImpl;
import com.mapple.ecommerce.util.PasswordEncryptionUtil;
import com.mapple.ecommerce.util.ToStringUtil;

public class UsuarioServiceTest {

	private static Logger logger = LogManager.getLogger(UsuarioServiceTest.class.getName());
	
	private UsuarioService usuarioService = null;
	
	public UsuarioServiceTest() {
		usuarioService = new UsuarioServiceImpl();
	}
	
	protected void testExists() {
		logger.info("Testing exists ...");

		String correoUsuario =  "amr@gmail.com";
		
		try {			
			Boolean exists = usuarioService.exists(correoUsuario);			
			logger.info("Exists: "+correoUsuario+" -> "+exists);
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
		logger.info("Test exists finished.\n");		
	}
	
	
	protected void testFindById() {
		logger.info("Testing findById ...");
		
		String correoUsuario = "amr@gmail.com";
		
		try {			
			Usuario u = usuarioService.findById(correoUsuario);			
			logger.info("Found: "+ToStringUtil.toString(u));
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
		logger.info("Test testFindById finished.\n");		
	}
	
	
	protected void testCreate() {		
		logger.info("Testing create ...");	
		
		Usuario usuario = new Usuario();
		usuario.setCorreoUsuario("atv@gmail.com");
		usuario.setNombre("Alberto");
		usuario.setApellidos("Taboada Varela");
		usuario.setTelefono(695264135);
		usuario.setClave(PasswordEncryptionUtil.encryptPassword("abc123"));

		try {
			
			usuario = usuarioService.create(usuario);
			
			logger.info("Created: "+ToStringUtil.toString(usuario));
					
		} catch (Throwable t) {
			t.printStackTrace();
		}
		logger.info("Test created finished.\n");		
	}
	
	
	protected void testUpdate() {		
		logger.info("Testing update ...");	
		
		try {
			Usuario usuario = usuarioService.findById("hld@gmail.com");

			usuario.setNombre("Hector");
			usuario.setApellidos("Ledo Doval");
			usuario.setTelefono(612345678);
			usuario.setClave("abc123");
		


			usuarioService.update(usuario);
						
			usuario = usuarioService.findById("hld@gmail.com");
			logger.info("Updated to: "+usuario);
								
		} catch (Throwable t) {
			t.printStackTrace();
		}
		logger.info("Test update finished.\n");		
	}	
	
	
	
	public static void main(String args[]) {
		UsuarioServiceTest test = new UsuarioServiceTest();
		test.testExists();
		test.testFindById();
		//test.testCreate();
		//test.testUpdate();

	}
}
