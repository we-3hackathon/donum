package com.donum.accountservice.Service;

import com.donum.accountservice.Enum.Enum_Login_Credentials;
import com.donum.accountservice.Model.MailRequest;
import com.donum.accountservice.Model.MailResponse;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import freemarker.template.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

@Service
public class EmailService {

    private JavaMailSenderImpl sender  = new JavaMailSenderImpl();

    private Configuration config = new Configuration();

    public MailResponse sendEmail(MailRequest request, Map<String, Object> model, String template) {

        MailResponse response = new MailResponse();

        MimeMessage message = sender.createMimeMessage();

        model.put("URL", request.getURL() + "/" + request.getFirstname() + "/" + request.getTo());

        try {
            // set mediaType
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            config.setDirectoryForTemplateLoading(new File("src/main/resources/templates/"));

            Template t = config.getTemplate(template);
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            sender.setHost("smtp.gmail.com");
            sender.setPort(587);
            sender.setUsername("Aroundhackathon@gmail.com");
            sender.setPassword(Enum_Login_Credentials.GMAIL_PASSWORD.toString());

            Properties props = sender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");

            helper.setTo(request.getTo());
            helper.setText(html, true);
            helper.setSubject(request.getSubject());
            helper.setFrom(request.getFrom());
            sender.send(message);

            response.setMessage("mail send to : " + request.getTo());
            response.setStatus(Boolean.TRUE);

        } catch (MessagingException | IOException | TemplateException e) {
            response.setMessage("Mail Sending failure : "+e.getMessage());
            response.setStatus(Boolean.FALSE);
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}