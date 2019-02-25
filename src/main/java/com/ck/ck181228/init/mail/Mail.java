package com.ck.ck181228.init.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
    // foxmail
    // outlook

    private String[] to;   // 收件人电子邮箱
    private String   from; // 发件人电子邮箱
    private String   pass;
    // private String hostSend; // 指定发送邮件的主机 smtp.qq.com
    // private Session session; // 获取默认session对象
    private MimeMessage message; // 创建默认的 MimeMessage 对象

    public Mail(String[] to, String from, String pass, String hostSend, String portSend) {
        this.to = to;
        this.from = from;
        this.pass = pass;
        init(hostSend, portSend);
    }

    public void init(String hostSend, String portSend) {
        // 获取系统属性
        // Properties properties = System.getProperties();
        Properties properties = new Properties();
        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", hostSend);
        properties.setProperty("mail.smtp.port", portSend);
        properties.put("mail.smtp.auth", "true");
        // Session session = Session.getDefaultInstance(properties);
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass); // 发件人邮件用户名、密码
            }
        });
        // 创建默认的 MimeMessage 对象
        message = new MimeMessage(session);
    }

    public void send(String subject, String content) throws MessagingException {
        // Set From:
//        message.setFrom(new InternetAddress(from));
        
        String nick="";  
        try {  
            nick=javax.mail.internet.MimeUtility.encodeText("Administrator Official");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }   
        message.setFrom(new InternetAddress(nick+" <"+from+">"));
        // Set To:
        for (String t : to) {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(t));
        }
        // Set Subject:
        message.setSubject(subject);
        // 设置消息体
         message.setContent(content,"text/html;charset=utf-8" );
        // 发送消息
        Transport.send(message);
        System.out.println("已发送");
    }
    
    public static String sendmail(String[] to,String context,String Title) throws MessagingException {
    	Mail ms = new Mail(to, "1063730142@qq.com", "jnyvukewmcfabfjf", "smtp.qq.com", "587");
    	ms.send(Title, context);
    	return "success";
    }
}
