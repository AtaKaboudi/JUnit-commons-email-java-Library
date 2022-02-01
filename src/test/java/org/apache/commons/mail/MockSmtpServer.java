package org.apache.commons.mail;

import java.io.File;

import org.subethamail.wiser.Wiser;

public class MockSmtpServer {
	/** The fake Wiser email server */
	private Wiser mockMailServer = null;
	private int mailServerPort;
	/** Where to save email output **/
	private File emailOutputDir;
	public MockSmtpServer() {
	mailServerPort = 8080;
	}
}
