/**
 * wissen16
 * 30-Jan-2010
 */
package com.wissen.eportal.server.impl.util;
import org.apache.commons.mail.HtmlEmail;

/**
 * @author wissen16
 *
 */
public class EmailManagerUtil {
	
	public static String hostName="wissen.co.in";
	public static String defaultFromAddress="mayur.birari@wissen.co.in";
	public static Integer smtpPostAddress=25;//Default is 25
	public static String smtpUserName="mayur.birari@wissen.co.in";
	public static String smtpPassword="mayurbirari1234";
	public static String toAddress="mayureshb4u@gmail.com";
	
//	 public static void main(String args[]){
//		EmailManager.sendSimpleMail();
//	}
	
	public boolean sendSimpleMail(){
		boolean flag=false;
		try {
			System.out.println("***Mail sending started***");
			HtmlEmail email = new HtmlEmail();
			email.setHostName("wissen.co.in");
			email.addTo(toAddress, "Mayuresh");
			
			email.setFrom(defaultFromAddress,"Mayur Birari");
			email.setSubject("Simple mail sending demo");
			if (smtpPostAddress != null) {
				email.setSmtpPort(smtpPostAddress);
			}
			if (smtpUserName != null) {
				email.setAuthentication("mayur.birari@wissen.co.in", "mayurbirari1234");
			}
			email.setTextMsg("Hi Mayur! Hows you ?");
			email.send();
			System.out.println("***Mail sent to:"+toAddress);
			
			flag=true;

		} catch (Exception e) {
			System.out.println("Exception while sending the mail:"+e);
		}
		return flag;
	}


}
