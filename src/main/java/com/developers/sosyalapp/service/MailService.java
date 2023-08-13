package com.developers.sosyalapp.service;

import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class MailService {
    private String sendGridApiKey;
    private String emailFrom;
    private String applicationUrl;

    @Value("${sendgrid.key}")
    public void setSendGridApiKey(String sendGridApiKey) {
        this.sendGridApiKey = sendGridApiKey;
    }

    @Value("${email.from}")
    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    @Value("${application.url}")
    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }

    public void sendVerificationMail(String email, String token){
        Email from = new Email(emailFrom);
        String subject = "Sosyal App Verification Email";
        Email to = new Email(email);

        Content content = new Content("text/plain", "Please verify your email address by clicking the link below:\n" +
                applicationUrl + "/api/v1/verification/verify-mail?email=" + email + "&token=" + token);

        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        try {
            request.setMethod(com.sendgrid.Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sg.api(request);
        } catch (Exception e) {
            throw new RuntimeException("Error sending email: " + e.getMessage());
        }
    }

}