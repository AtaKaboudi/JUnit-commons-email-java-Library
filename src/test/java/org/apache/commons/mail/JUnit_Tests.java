package org.apache.commons.mail;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import static org.junit.Assert.*;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.OrderWith;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetAddress;


public class JUnit_Tests extends Wiser  {
	
	// ADD SETUP FUNCTION FOR INSTANCIATE AND MOCKUP SERVER USE WAISER
	 static Wiser server = new Wiser();
	 Singelton email = new Singelton();
	 MimeMultipart mime = new MimeMultipart();
	
	
	
	
	 String[] EMAIL_ARRAY = {"A.K@gmail.com","X.Y@gmail.com","Z.Y@gmail.com","A.B@gmail.com"};
	 String[] EMAIL_EMPTY = {};
	 String MAP_KEY = "2";
	 String MAP_VALUE ="Ata"; 
	 String SAMPLE_EMAIL ="ata.kaboudi@gmail.com";
	 String SAMPLE_NAME ="Ata Kaboudi";
	 String SAMPLE_PASSWORD="password";
	 String SAMPLE_TO_EMAIL ="jhon.doe@gmail.com";
	 String SAMPLE_TO_NAME ="Jhon Doe";
	 String SAMPLE_SUBJECT ="Subject";
	 String SAMPLE_CHARSET ="UTF-8";
	 String SAMPLE_HEADER_NAME="Content-type";
	 String SAMPLE_HEADER_VALUE="json/text";
	
	 
	 
	 
	 

	 // SETUP CLASS
	@BeforeClass
	public static void setUp() throws Exception {
		server.setPort(25);
		server.setHostname("localhost");
		System.out.print("[SERVER] Runninng ...");

		try {
		 server.start();
		}catch(RuntimeException e) {
			System.out.print("[SERVER] Failed to start");
		}
	}
	
	
	
	
	
		
	// TEST NORMAL BEHAVIOR OF ADDCC
	@Test
	public void addCC() throws EmailException {
		 email.setDebug(true);

		Email instance = email.addCc(EMAIL_ARRAY);
		
		boolean flag = true;
		
		 // Check match using last elements in instance.ccList
		for(int i =0 ;i< EMAIL_ARRAY.length;i++) {
			if (!EMAIL_ARRAY[i].equals(instance.ccList.get(instance.ccList.size()- EMAIL_ARRAY.length+i).getAddress())) {
				flag = false;
				break;
			}
			
		}	
		assertEquals(flag,true);
	}
	
	
	
	
	// TEST NORAMLE BEHAVIOR OF ADDCCB
	@Test
	public void addCCB() throws EmailException {

		Email instance = email.addCc(SAMPLE_EMAIL);
		assertEquals(SAMPLE_EMAIL,instance.ccList.get(instance.ccList.size()-1).getAddress()); 
					
	}
	
	
	// TEST EDGE CASES OF ADDCC
	@Test(expected = EmailException.class)
	public void addCC_E() throws EmailException {
		Email instance = email.addCc(EMAIL_EMPTY);			
	}
	
	
	
	// TEST EDGE CASES OF ADDBCC
	@Test(expected = EmailException.class)
	public void addBcc_E() throws EmailException {
		email.addBcc(EMAIL_EMPTY);	
	}
	
	@Test
	public void addBcc_E_A() throws EmailException {
		
		Email instance = email.addBcc(EMAIL_ARRAY);
		
		boolean flag = true;
		
		for(int i =0 ;i< EMAIL_ARRAY.length;i++) {
			if (!EMAIL_ARRAY[i].equals(instance.bccList.get(instance.bccList.size()- EMAIL_ARRAY.length+i).getAddress())) {
				flag = false;
				break;
			}
			
		}
		assertEquals(flag,true);	
	}
	
	
	// TEST NORAML BEHAVIOR OF ADDHEADER

	@Test(expected =IllegalArgumentException.class)
	public void addheader() throws EmailException {
		email.addHeader("","1");
	}
	
	
	// TEST EDGE CASES  OF addheader

	@Test(expected =IllegalArgumentException.class)
	public void addHeader_E_B() throws EmailException {
		email.addHeader("Ata","");
		
	}
	@Test
	public void addHeader_E_C() throws EmailException {
		email.addHeader(MAP_KEY,MAP_VALUE);	
		assertEquals(email.getHeadersByKey(MAP_KEY), MAP_VALUE);	
	}
	
	
	// TEST NORAMLE BEHAVIOR OF ADDRELYTO

	@Test
	public void test_addReplyTo() throws EmailException {
		Email instance = email.addReplyTo(SAMPLE_EMAIL,SAMPLE_NAME);		
		assertEquals(instance.replyList.get(instance.replyList.size()-1).getAddress(),SAMPLE_EMAIL);
		assertEquals(instance.replyList.get(instance.replyList.size()-1).getPersonal(),SAMPLE_NAME);
	}
	
	
	// TEST NORAMLE BEHAVIOR OF ADDTO
	
	@Test(expected=EmailException.class)
	public void test_addTo() throws EmailException {
		 email.addTo(EMAIL_EMPTY);
	}
	
	
	
	//SETUP FOR BUILDMIMEMESSAGE FUNCTION
	public void setup_buildMimeMesssage(String hostname , MimeMultipart mime,boolean addRecipients) throws EmailException {
		 
		
		//SET SUBJECT
		email.setSubject(SAMPLE_SUBJECT);
		assertEquals(email.subject,SAMPLE_SUBJECT);
		
		//SET_CHARSET
		email.setCharset("UTF-8");
		assertEquals(email.charset,"UTF-8");

		
		//SET_CONTENT
		email.setContent(mime);
		
		
		//SET_FROM
		email.setFrom(SAMPLE_EMAIL);
		assertEquals(email.fromAddress.getAddress(),SAMPLE_EMAIL);
		
		
		email.setHostName(SAMPLE_NAME);	
		//assertEquals(email.getHostName(),SAMPLE_EMAIL);

		 
		 
		 //sET TOlist
		 if( addRecipients) {
		 email.addTo(EMAIL_ARRAY);
		 
		 
		
		 List<InternetAddress> i = new ArrayList <InternetAddress>();
		
		 i = email.getToAddresses();
		 
		assertEquals(i.get(i.size()-1).getAddress(),EMAIL_ARRAY[EMAIL_ARRAY.length-1]);
		
		
		email.addCc(SAMPLE_EMAIL);
		
		i = email.getCcAddresses();
		assertEquals(SAMPLE_EMAIL,i.get(i.size()-1).getAddress()); 
		
		email.addBcc(SAMPLE_EMAIL);
		i = email.getBccAddresses();
		assertEquals(SAMPLE_EMAIL,i.get(i.size()-1).getAddress());
		
		
		email.addReplyTo(SAMPLE_EMAIL);
		i = email.getReplyToAddresses();
		assertEquals(SAMPLE_EMAIL,i.get(i.size()-1).getAddress());
			
		
		email.addHeader(SAMPLE_HEADER_NAME , SAMPLE_HEADER_VALUE);
		Map<String, String> headersList = email.getHeaders();
		assertEquals(headersList.get(SAMPLE_HEADER_NAME),SAMPLE_HEADER_VALUE);
		
		 }
		
	}
	
	
	// TEST EDGE CASES OF BUILDMIMEMESSAGE

	@Test (expected =IllegalStateException.class)
	public void buildMimeMessage_E_B() throws EmailException   {

			setup_buildMimeMesssage("localhost",mime,true);
			 email.buildMimeMessage();	
			 email.buildMimeMessage();	
 
	}
	
	
	// TEST NORAMLE BEHAVIOR OF BUILDMIMEMESSAGE
	@Test
	public void test_buildMimeMessage() throws EmailException, MessagingException, IOException {

		
		if(!(email.getMessage() != null)) {
			setup_buildMimeMesssage("localhost",mime,true);
			 email.buildMimeMessage();	

		};
		 

		 assertEquals(email.message.getSubject(),SAMPLE_SUBJECT);		 
		 assertEquals(email.message.getContent(),mime);
		 
		 assertEquals(email.message.getFrom()[email.message.getFrom().length-1].toString(),SAMPLE_EMAIL);
		 
		 
		 String lastToAddress = email.message.getRecipients(Message.RecipientType.TO)[email.message.getRecipients(Message.RecipientType.TO).length-1].toString();
		 
		 assertEquals(lastToAddress,EMAIL_ARRAY[EMAIL_ARRAY.length-1]); 
	
	}
	
	

	// TEST EDGE CASES OF BUIDMIMESESSION

	@Test(expected =IllegalStateException.class)
	public void buildMimeMessage_E_A() throws EmailException   {
		MimeMultipart mime = new MimeMultipart();

		 setup_buildMimeMesssage(SAMPLE_NAME,mime,true);
		
		 email.buildMimeMessage();	
		 email.buildMimeMessage();	
	}
	
	
	// TEST EDGE CASES OF BUIDMIMESESSION

	@Test
	public void test_buildMimeMessageE3() throws EmailException   {
	
		
		//SET SUBJECT
		email.setSubject(SAMPLE_SUBJECT);
		
		//SET_CHARSET
		email.setCharset("UTF-8");

		
		//SET_CONTENT
		email.setContent(mime);
		
		
		//SET_FROM
		email.setFrom(SAMPLE_EMAIL);
		assertEquals(email.fromAddress.getAddress(),SAMPLE_EMAIL);
		
		
		email.setHostName(SAMPLE_NAME);	

		
		email.setSSL(true);
		 email.setSSLCheckServerIdentity(false);
		assertEquals(email.isSSL(),true);
		
		
		email.setStartTLSEnabled(true);
		assertEquals(email.isStartTLSEnabled(),true);
		
		email.setStartTLSRequired(false);
		assertEquals(email.isStartTLSRequired(),false);
		

		
		email.setSslSmtpPort("800");
		assertEquals(email.getSslSmtpPort(),"800");
		
		email.setSocketConnectionTimeout(1000);
		assertEquals(email.getSocketConnectionTimeout(),1000);
		
		email.setSocketTimeout(1000);
		assertEquals(email.getSocketTimeout(),1000);
		
		email.setBounceAddress(SAMPLE_EMAIL);
		assertEquals(email.getBounceAddresse(),SAMPLE_EMAIL);
		
		
		email.setSmtpPort(10);
		assertEquals(email.getSmtpPort(),"10");
		 
		email.addTo(EMAIL_ARRAY);
		
		 List<InternetAddress> i = new ArrayList <InternetAddress>();
		
		 i = email.getToAddresses();
		 
		assertEquals(i.get(i.size()-1).getAddress(),EMAIL_ARRAY[EMAIL_ARRAY.length-1]);
		email.buildMimeMessage();
		

	}
	
	// TESTS NORAMLE BEHAVIOR OF GTHOSTNAME
	@Test
	public void getHostName() {
		email.setHostName(SAMPLE_NAME);
		String hostname = email.getHostName();
		assertEquals(hostname,SAMPLE_NAME);

		}
	
	// TESTE EDGES CASES OF GETHOSTNAME
	@Test
	public void getHostName_E() {	
		String hostname = email.getHostName();
		assertEquals(hostname,null);
	}
	
	//TEST EDGES CASES OR GETHOSTNAME
	@Test
	public void getHostName_E_A() {	
		email.setMailSession(super.getSession());
		String hostname = email.getHostName();
		assertEquals(hostname,null);
	}
	

	
	// TEST NORMALE getmailsession
	@Test
	public void getMailSession() throws EmailException {	
		Session s = super.getSession();
		
		email.setMailSession(s);
		
		assertEquals(email.getMailSession(),s);
		
	}
	
	
	
	// TEST NORAML BEHAVIOR GETSENTDATE
	@Test
	public void test_getSendDate() throws EmailException {	
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");

		Date setDate = new Date(System.currentTimeMillis());
		email.setSentDate(setDate);
		
		
		Date getDate = email.getSentDate();
		assertEquals(formatter.format(setDate),formatter.format(getDate));
		
	}
	
	
	// TEST NORAMLE BEHAVIOR GETSOCKETCONNECTION TIMEOUT
	@Test
	public void test_getSocketConnectionTimeout() throws EmailException {	
		
			
		int timeout = email.getSocketConnectionTimeout();
		
		assertEquals(timeout,EmailConstants.SOCKET_TIMEOUT_MS);
		
	}
	
	
	// TEST NORAML BEHAVIOR SETFROM
	@Test
	public void setFrom() throws EmailException {	
		email.setFrom(SAMPLE_EMAIL);
		assertEquals(email.getFromAddress().toString(),SAMPLE_EMAIL);		
	}
	
	
	//  TEST NORAMLE BEHAVIOR OF UPDATECONTENTTYPE
	@Test
	public void updateContentType() throws EmailException {	
	email.updateContentType(SAMPLE_CHARSET);

	assertEquals(email.contentType,SAMPLE_CHARSET);
	}
	
	
	// TEST NORMAL BEAVIOR OF SETCONTENT
	@Test
	public void setContent() throws EmailException {
		Object obj = new Object();
		email.setContent(obj ,SAMPLE_CHARSET);

		assertEquals(email.contentType,SAMPLE_CHARSET);
		assertEquals(email.content,obj);
	
	}
	
	// TEST EDGES CASES SETTO
	@Test(expected =EmailException.class)
	public void setTo_E_A() throws AddressException, EmailException {
		Collection<InternetAddress> COLLECTION = new ArrayList<InternetAddress>();
		email.setTo(COLLECTION);
	}
	
	// TEST EDGES CASES SETTO
	@Test
	public void setTo_E_B() throws AddressException, EmailException {
		Collection<InternetAddress> COLLECTION = new ArrayList<InternetAddress>();
		COLLECTION.add(new InternetAddress("X.Z@gmail.com"));
		COLLECTION.add(new InternetAddress("Z.Y@gmail.com"));
		COLLECTION.add(new InternetAddress("Y.Z@gmail.com"));
		email.setTo(COLLECTION);
		Iterator<InternetAddress> iter = COLLECTION.iterator();
		
		for(InternetAddress i : email.getToAddresses()) {
			assertEquals(i,iter.next() );
			
		}
	}
	
	
	// TEST SETCC ORMALE BEHAVIOR
	@Test
	public void setCc() throws AddressException, EmailException {
		Collection<InternetAddress> COLLECTION = new ArrayList<InternetAddress>();
		COLLECTION.add(new InternetAddress("X.Z@gmail.com"));
		COLLECTION.add(new InternetAddress("Z.Y@gmail.com"));
		COLLECTION.add(new InternetAddress("Y.Z@gmail.com"));
		email.setCc(COLLECTION);
		Iterator<InternetAddress> iter = COLLECTION.iterator();

		for(InternetAddress i : email.getCcAddresses()) {
			assertEquals(i,iter.next() );
			
		}
		
	}
	
	// EDGES CASES SETCCa
	@Test(expected =EmailException.class)
	public void setCcA() throws AddressException, EmailException {
		Collection<InternetAddress> COLLECTION = new ArrayList<InternetAddress>();
		email.setCc(COLLECTION);
	}
	
	// NORAMLE BAHAVOR CASES SETBCC

	@Test
	public void setBcc() throws AddressException, EmailException {
		Collection<InternetAddress> COLLECTION = new ArrayList<InternetAddress>();
		COLLECTION.add(new InternetAddress("X.Z@gmail.com"));
		COLLECTION.add(new InternetAddress("Z.Y@gmail.com"));
		COLLECTION.add(new InternetAddress("Y.Z@gmail.com"));
		email.setBcc(COLLECTION);
		Iterator<InternetAddress> iter = COLLECTION.iterator();

		for(InternetAddress i : email.getBccAddresses()) {
			assertEquals(i,iter.next() );		
		}	
	}
	
	// EDGE CASES   SETBCC

	@Test(expected =EmailException.class)
	public void setBccA() throws AddressException, EmailException {
		Collection<InternetAddress> COLLECTION = new ArrayList<InternetAddress>();
		email.setBcc(COLLECTION);
	}
	
	
	// NORAMLE BAHAVOR CASES SETREPLYTO

	@Test
	public void setRplyTo() throws AddressException, EmailException {
		Collection<InternetAddress> COLLECTION = new ArrayList<InternetAddress>();
		COLLECTION.add(new InternetAddress("X.Z@gmail.com"));
		COLLECTION.add(new InternetAddress("Z.Y@gmail.com"));
		COLLECTION.add(new InternetAddress("Y.Z@gmail.com"));
		email.setReplyTo(COLLECTION);
		Iterator<InternetAddress> iter = COLLECTION.iterator();

		for(InternetAddress i : email.getReplyToAddresses()) {
			assertEquals(i,iter.next() );		
		}	
	}
	
	
	// EDGE CASES SETREPLYTO
	@Test(expected =EmailException.class)
	public void setReplyToE() throws AddressException, EmailException {
		Collection<InternetAddress> COLLECTION = new ArrayList<InternetAddress>();
		email.setReplyTo(COLLECTION);
	}
	
	// NORAMLE BEHAVIOR SETHEADERS
	@Test
	public void setHeaders() throws AddressException, EmailException {
	    Map<String, String> map = new HashMap<String, String>();
	    map.put(MAP_KEY,MAP_VALUE);
	    email.setHeaders(map);
	    assertEquals(email.getHeaders().get(MAP_KEY),MAP_VALUE); 
	}
	
	
	// NORMALE BEHAVIOR SEND
	@Test
	public void test_send() throws EmailException {	
	
		 //SET SUBJECT
		email.setSubject(SAMPLE_SUBJECT);
		assertEquals(email.subject,SAMPLE_SUBJECT);
		
		//SET_CHARSET
		email.setCharset("UTF-8");
		assertEquals(email.charset,"UTF-8");

		
		//SET_CONTENT
		email.setContent(mime);
		
		
		//SET_FROM
		email.setFrom(SAMPLE_EMAIL);
		assertEquals(email.fromAddress.getAddress(),SAMPLE_EMAIL);
		
		 
		 //SET HOSTNAME
		 email.setHostName("localhost");
		 
		 
	    
		 email.addTo(SAMPLE_TO_EMAIL,SAMPLE_TO_NAME,SAMPLE_CHARSET);
		 
		 
		
		email.addCc(SAMPLE_EMAIL);
		
		
		email.addBcc(SAMPLE_EMAIL);
		
		
		email.addReplyTo(SAMPLE_EMAIL);
			
		
	    
		email.send();

		assertEquals(getMailSubject(),email.getSubject());
	}
	
	
	public String getMailSubject() {
	     String subject = "";
	    if (this.server != null) {
	    		if (server.getServer().isRunning()) {
	    			if (server.getMessages().size() >= 1) {
	    				WiserMessage emailMessage = server.getMessages().get(0);
	    				try {
	    					// get the MimeMessage
	    					MimeMessage mimeMessage = emailMessage.getMimeMessage();
	    					subject = mimeMessage.getHeader("Subject", null);
	    				} catch (MessagingException me) {
	    					IllegalStateException ise = new IllegalStateException(
	    							"caught MessagingException in alidateSend()");
	    					ise.initCause(me);
	    					throw ise;
	    				}
	    			}
	    		}
	    }
	return subject;
	}
	
	
	
	
	// ADD TEAR DOWN FREE MOCKUP SERVER PORT
	@AfterClass
    public static void after() throws Exception {
        if (server != null) {
    		if (server.getServer().isRunning()) {
    		server.stop();
    		System.out.print(" \n[SERVER] Shutting down \n");
    		}
    	}
    }
	

	
	


}

	
	
	
	


