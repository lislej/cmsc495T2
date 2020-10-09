package org.amortizer.loancalcservice.email;

// using SendGrid's Java Library
// https://github.com/sendgrid/sendgrid-java
import com.sendgrid.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Properties;
import java.util.jar.Manifest;



public class EmailManager {

	private Email from;
	private String subject;
	private Email to;
	private Content content;
	private Mail mail;
	private Attachments attachment;
	private SendGrid sg;
	static private  String API_KEY;
	

	
	public EmailManager(String subject, String toAddress, String fromAddress, String contentMsg) throws IOException {
		
		
		API_KEY="";
		
		this.sg = new SendGrid(EmailManager.API_KEY);

		this.from = new Email(fromAddress);
		this.subject = subject;
		this.to = new Email(toAddress);
		this.content = new Content("text/html", contentMsg);
		this.mail = new Mail(this.from, this.subject, this.to, this.content);
		this.attachment = new Attachments();

	}
	
	public static Properties readPropertiesFile(String fileName) throws IOException {
	      FileInputStream fis = null;
	      Properties prop = null;
	      try {
	         fis = new FileInputStream(fileName);
	         prop = new Properties();
	         prop.load(fis);
	      } catch(FileNotFoundException fnfe) {
	         fnfe.printStackTrace();
	      } catch(IOException ioe) {
	         ioe.printStackTrace();
	      } finally {
	         fis.close();
	      }
	      return prop;
	   }

	public Response sendEmailWithoutAttachement() throws IOException {

		Request request = new Request();
		Response response;

		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());

			response = sg.api(request);

		} catch (IOException ex) {
			throw ex;
		}

		return response;
	}

	public Response sendEmailWithAttachment(File attachedFile) throws IOException {

		Request request = new Request();
		Response response;
		try {

			String encodedContent = Base64.getEncoder().encodeToString(Files.readAllBytes(attachedFile.toPath()));
			attachment.setContent(encodedContent);
			attachment.setType("application/pdf");
			attachment.setFilename(attachedFile.getName());
			attachment.setDisposition("attachment");
			attachment.setContentId("Amortization Schedule");
			mail.addAttachments(attachment);

			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());

			response = sg.api(request);
			// System.out.println(response.getStatusCode());
			// System.out.println(response.getBody());
			// System.out.println(response.getHeaders());
		} catch (IOException ex) {
			throw ex;
		}

		return response;

	}

	

}
