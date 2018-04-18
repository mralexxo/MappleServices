package com.mapple.ecommerce.service.impl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.mapple.ecommerce.config.ConfigurationManager;
import com.mapple.ecommerce.exceptions.MailException;
import com.mapple.ecommerce.service.MailService;

public class MailServiceImpl implements MailService {

	private static final String CFG_PFX = "service.mail."; 
	private static final String HOSTNAME_PARAMETER_NAME = CFG_PFX + "host.name";
	private static final String SMTP_PORT_PARAMETER_NAME  = CFG_PFX + "smtp.port"; 
	private static final String DEFAULT_USER_NAME =  CFG_PFX + "default.user.name";
	private static final String DEFAULT_FROM = CFG_PFX + "default.from";
	private static final String DEFAULT_USER_PASSWORD = CFG_PFX + "default.user.password";

	
	public MailServiceImpl() {		
		
	}


	public void sendMail(String subject, String message, String... to) throws MailException{
		// Por qu� crees que obtenemos los parametros de configuraci�n en cada 
		// invocacion del m�todo, en lugar de obtenerla en el constructor solo una vez?
		// Es imprescindible? Es la mejor opci�n? Depende?

		ConfigurationManager cfg = ConfigurationManager.getInstance();

		SimpleEmail email = new SimpleEmail();
		try {

			email.setHostName(cfg.getParameter(HOSTNAME_PARAMETER_NAME));

			email.setSmtpPort(Integer.valueOf(cfg.getParameter(SMTP_PORT_PARAMETER_NAME)));

			email.setAuthenticator(
					new DefaultAuthenticator(
							cfg.getParameter(DEFAULT_USER_NAME),
							cfg.getParameter(DEFAULT_USER_PASSWORD)
							));
			email.setSSLOnConnect(true);
			email.setFrom(cfg.getParameter(DEFAULT_FROM));			
			email.setSubject(subject);
			email.setMsg(message);
			email.addTo(to);
			email.send();
		} catch (EmailException e) {
			// TODO: Logger
			throw new MailException ("Trying to send email "
					+ " from " + cfg.getParameter(DEFAULT_FROM)
					+ " using hostname "+cfg.getParameter(HOSTNAME_PARAMETER_NAME)
					+ " to " + to, e);
			// No mostramos password ni contenido de email 
		}
	}
}
