package com.zgp.message.email.Util.improve;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Properties;

/**
 * @author zhugp
 * @description 通过qq发送邮件，注意要现在qq邮箱的设置->账户将smtp开启，然后获取客户端授权码就可以执行
 * @date 2019/11/7 15:33
 * @param null
 * @return 
 **/
public class SendQQMailUtil {

    private static final String EMAIL_TITLE = "告警";

    private static Properties properties;

    public SendQQMailUtil() {
        InputStream inputStream = getClass().getResourceAsStream("/email.properties");
        properties = new Properties();
        try {
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws AddressException, MessagingException, IOException {
        sendEmail("674236876@qq.com", "2201293673@qq.com", "邮件内容邮件内容邮件内容xmqtest");
    }

    public static void sendEmail(String from, String to, String content)  throws AddressException, MessagingException, IOException {
//        端口必须设定为int类型
        properties.put("mail.smtp.port", 465);
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress(from));
        // 设置收件人邮箱地址
//        message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("2201293673@qq.com"),new InternetAddress("xxx@qq.com"),new InternetAddress("xxx@qq.com")});
        //一个收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        // 设置邮件标题
        message.setSubject(EMAIL_TITLE);
        // 设置邮件内容
        message.setText(content);
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