/**
 * 
 */
package com.wissen.eportal.server.emailxml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.mail.HtmlEmail;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author wissen16
 * 
 */
public class XmlParser {
	static InputStream in = null;
	static SAXReader reader = null;
	static Document document = null;

	
	public static String hostName;
	public static String defaultFromAddress;
	public static Integer smtpPostAddress;
	public static String smtpUserName;
	public static String smtpPassword;
	public static String toAddress;
		
	
	@SuppressWarnings("unchecked")
	public void main(){
		try {
			byte[] byteData;
			 byteData = getBytesFromFile("src/com/wissen/eportal/server/emailxml/EmailInfo.xml");
			String data = new String(byteData);
			if (data != null && data.length() > 0) {
				in = new ByteArrayInputStream(data.getBytes());
				reader = new SAXReader();
				try {
					document = reader.read(in);
				} catch (Exception e) {
					System.out
							.println("Exception while assigning the bytes to Document:"
									+ e);
				}
				if (document != null) {
					Element rootElement = document.getRootElement();
					rootElement.normalize();
					// System.out.println("******** XML Details *********");
					// System.out.println("ROOT ELEMENT:"+rootElement.getName());
					// System.out.println("TOTAL NUMBER OF CHILD ELEMENTS OF ROOT ELEMENT:"+rootElement.elements().size());
					List<Element> childElement = rootElement.elements();

					// code to get child elements of the root node
					for (int i = 0; i < childElement.size(); i++) {
						@SuppressWarnings("unused")
						String childName = childElement.get(i).getName();
						// System.out.println("NAME OF "+(i+1)+" CHILD OF ROOT ELEMENT: "+childName+" WITH ID "+childElement.get(i).attributeValue("id"));
					}
					System.out.println();

					// code to traverse the child elements of child of root
					// element
					for (int k = 0; k < childElement.size(); k++) {
						List<Element> elements = childElement.get(k).elements();
						if (elements != null && elements.size() > 0) {
							for (int i = 0; i < elements.size(); i++) {
//								System.out.println("NAME OF " + (i + 1)
//										+ " CHILD OF "
//										+ childElement.get(k).getName()
//										+ " ELEMENT:"
//										+ elements.get(i).getName());
							}
						}
					}

					
					
					
						
					hostName=childElement.get(0).elementText("hostName");
					defaultFromAddress=childElement.get(0).elementText("defaultFromAddress");
					smtpPostAddress=Integer.parseInt(childElement.get(0).elementText("smtpPostAddress"));
					smtpUserName=childElement.get(0).elementText("smtpUserName");
					smtpPassword=childElement.get(0).elementText("smtpPassword");
					toAddress=childElement.get(0).elementText("toAddress");
					
					try {
						System.out.println("***Mail sending started***");
						HtmlEmail email = new HtmlEmail();
						email.setHostName(hostName);
						email.addTo(toAddress, "Mayuresh");
						
						email.setFrom(defaultFromAddress,"Mayur Birari");
						email.setSubject("Simple mail sending demo");
						if (smtpPostAddress != null) {
							email.setSmtpPort(smtpPostAddress);
						}
						if (smtpUserName != null) {
							email.setAuthentication(smtpUserName, smtpPassword);
						}
						email.setTextMsg("Hi Mayur! Hows you ?");
						email.send();
						System.out.println("***Mail sent to:"+toAddress);

					} catch (Exception e) {
						System.out.println("Exception while sending the mail:"+e);
					}					
					
				}//if doc
			}

		} catch (Exception e) {
			System.out.println("Exception while getting the bytes:" + e);
		}
	}

	public static byte[] getBytesFromFile(String filename) throws IOException {

		byte[] bytes = null;
		try {
			File file = new File(filename);
			InputStream is = new FileInputStream(file);

			long length = file.length();

			if (length > Integer.MAX_VALUE) {
				// File is too large
			}

			// Create the byte array to hold the data
			bytes = new byte[(int) length];

			// Read in the bytes
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length
					&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}

			// Ensure all the bytes have been read in
			if (offset < bytes.length) {
				throw new IOException("Could not completely read file "
						+ file.getName());
			}

			// Close the input stream and return bytes
			is.close();
		} catch (Exception ex) {

			System.out.println("Exception while getting the bytes:" + ex);
		}

		return bytes;
	}

}
