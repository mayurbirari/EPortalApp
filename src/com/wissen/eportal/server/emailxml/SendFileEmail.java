package com.wissen.eportal.server.emailxml;


/*
 Copyright 2010 Wissen System Pvt. Ltd. All rights reserved.
 Author: Dipti Bhave on 5:54:38 PM
 */

/**
 * @author Dipti Bhave
 *
 * Create Date : 29-Jan-2010
 */
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
//import javax.activation.*;

public class SendFileEmail
{
   public boolean sendmail(String too,String sub,String msg)
   {
      
       String host = "smtp.gmail.com";
       String from = "mayureshb4u@gmail.com";
       String pass = "mayurbirari1234";
       String portNumber=null;//
       Properties props = System.getProperties();
       props.put("mail.smtp.starttls.enable", "true"); // added this line
       props.put("mail.smtp.host", host);
       props.put("mail.smtp.user", from);
       props.put("mail.smtp.password", pass);
       if(portNumber!=null){
    	   props.put("mail.smtp.port",portNumber);
       }
       props.put("mail.smtp.auth", "true");

       String[] to = {"mayureshb4u@gmail.com",too}; // added this line

       Session session = Session.getDefaultInstance(props, null);
       MimeMessage message = new MimeMessage(session);
       try{
       message.setFrom(new InternetAddress(from));

       InternetAddress[] toAddress = new InternetAddress[to.length];

       // To get the array of addresses
       for( int i=0; i < to.length; i++ ) { // changed from a while loop
           System.out.println(to[i]);
    	   toAddress[i] = new InternetAddress(to[i]);
       }
       System.out.println(Message.RecipientType.TO);

       for( int i=0; i < toAddress.length; i++) { // changed from a while loop
          System.out.println(toAddress[i]);
    	   message.addRecipient(Message.RecipientType.TO, toAddress[i]);
       }
       message.setSubject(sub);
       message.setText(msg);
       //message.setFileName("mayur");
       Transport transport = session.getTransport("smtp");
       transport.connect(host, from, pass);
       transport.sendMessage(message, message.getAllRecipients());
       transport.close();
       }
       catch (Exception e) {
    	   System.out.println("Exception : "+e);
        return false;
       }
       return true;
   }
}
