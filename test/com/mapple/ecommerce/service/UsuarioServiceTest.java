package com.mapple.ecommerce.service;

import com.mapple.ecommerce.model.Usuario;
import com.mapple.ecommerce.service.impl.UsuarioServiceImpl;
import com.mapple.ecommerce.util.ToStringUtil;

public class UsuarioServiceTest {

	private UsuarioService usuarioService = null;
	
	public UsuarioServiceTest() {
		usuarioService = new UsuarioServiceImpl();
	}
	
	protected void testExists() {
		System.out.println("Testing exists ...");

		String correoUsuario =  "amr@gmail.com";
		
		try {			
			Boolean exists = usuarioService.exists(correoUsuario);			
			System.out.println("Exists: "+correoUsuario+" -> "+exists);
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
		System.out.println("Test exists finished.\n");		
	}
	
	protected void testCreate() {		
		System.out.println("Testing create ...");	
		
		Usuario usuario = new Usuario();
		usuario.setCorreoUsuario("hld@gmail.com");
		usuario.setNombre("Hekto");
		usuario.setApellidos("Doval Ledo");
		usuario.setTelefono(638512450);
		usuario.setClave("abc123");
		usuario.setCodDireccion(12);

		try {
			
			usuario = usuarioService.create(usuario);
			
			System.out.println("Created: "+ToStringUtil.toString(usuario));
					
		} catch (Throwable t) {
			t.printStackTrace();
		}
		System.out.println("Test created finished.\n");		
	}
	
	
	protected void testUpdate() {		
		System.out.println("Testing update ...");	
		
		try {
			Usuario usuario = usuarioService.findById("hld@gmail.com");

			usuario.setNombre("Hector");
			usuario.setApellidos("Ledo Doval");
			usuario.setTelefono(612345678);
			usuario.setClave("gzonemola");


			usuarioService.update(usuario);
						
			System.out.println("Updated to: "+usuarioUpdate.getUsuario());
								
		} catch (Throwable t) {
			t.printStackTrace();
		}
		System.out.println("Test update finished.\n");		
	}	
	
	
	
	public static void main(String args[]) {
		UsuarioServiceTest test = new UsuarioServiceTest();
		test.testExists();
		test.testCreate();
		test.testUpdate();

	}
}
