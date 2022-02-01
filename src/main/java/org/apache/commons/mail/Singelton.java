package org.apache.commons.mail;

import java.util.Date;
import java.util.Map;

import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;


import javax.mail.internet.InternetAddress;

public class Singelton extends Email {
	
	public Email setMsg(String msg) throws EmailException {
		// TODO Auto-generated method stub
		return null;
	}
    public Email addCc(String... emails) throws EmailException {
    	 return super.addCc(emails);
    }
    public Email addBcc(String... emails) throws EmailException {
    	 return super.addBcc(emails);
    }
    public void addHeader(String name, String value)
    {
    	 super.addHeader(name,value);
    }
    
    public Email addReplyTo(String email, String name) throws EmailException {
    	return super.addReplyTo(email,name);
    }
  
    
    public void buildMimeMessage() throws EmailException{
    	super.buildMimeMessage();
    }
    public String getHostName() {
    	return super.getHostName();
    }
    public Session getMailSession() throws EmailException
    {
    	return super.getMailSession();
    	
    }
    public Date getSentDate() {
    	return super.getSentDate();
 
    }
    public int getSocketConnectionTimeout() {
    	return super.getSocketConnectionTimeout();
    }
    public String send() throws EmailException{
    	return super.send();
    }
    public Email setFrom(String email) throws EmailException {
    	return super.setFrom(email);
    }
    public void updateContentType(final String aContentType) {
    	super.updateContentType(aContentType);
    }
    public String getHeadersByKey(String index) {
    	return super.headers.get(index);
    }

	public MimeMessage getMessage() {
		return super.message;
	}
	
	public Map<String, String> getHeaders() {
		return super.headers;
	}
	
	public String getSmtpPort() {
		return super.getSmtpPort();
	}
	public void tearDownMessage() {
		super.message = null;
	}
	public void setContent(Object a , String b) {
		super.setContent(a,b);
	}
	public void setAuthetication(String name, String password) {
		super.setAuthentication(name,password);
	}
	public Authenticator getAuthentication() {
		return super.authenticator;
	}
	public String getBounceAddresse() {
		return super.bounceAddress;
	}
	public boolean isStartTLSEnabled() {
		return  super.isStartTLSEnabled();
	}




	

}
