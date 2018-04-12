package com.mapple.ecommerce.service;

import com.mapple.ecommerce.exceptions.MailException;

public interface MailService {
	
	public void sendMail(String subject, String message, String... to)
		throws MailException;

}
