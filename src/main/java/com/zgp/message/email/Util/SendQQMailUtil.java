package com.zgp.message.email.Util;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Properties;

public class SendQQMailUtil {

    private static final String EMAIL_TITLE = "告警";


    public static void main(String[] args) throws AddressException, MessagingException, IOException {

        Properties properties = new Properties();
        // 连接协议
        properties.put("mail.transport.protocol", "smtp");
        // 主机名
        properties.put("mail.smtp.host", "smtp.qq.com");
        // 端口号
        properties.put("mail.smtp.port", 465);
        properties.put("mail.smtp.auth", "true");
        // 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.smtp.ssl.enable", "true");
        // 设置是否显示debug信息 true 会在控制台显示相关信息
        properties.put("mail.debug", "true");
        // 得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress("674236876@qq.com"));
        // 设置收件人邮箱地址 
//        message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("2201293673@qq.com"),new InternetAddress("xxx@qq.com"),new InternetAddress("xxx@qq.com")});
        //一个收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("2201293673@qq.com"));
        // 设置邮件标题
        message.setSubject(EMAIL_TITLE);
        // 设置邮件内容
        message.setText("邮件内容邮件内容邮件内容xmqtest");
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        // 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
        transport.connect("674236876@qq.com", "lktvtmsesylwbcib");
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}