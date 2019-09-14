package com.donum.accountservice.Service;

import com.donum.accountservice.Model.MailRequest;
import com.donum.accountservice.Model.MailResponse;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import freemarker.template.Configuration;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration configuration;

    public MailResponse sendEmail(MailRequest request, Map<String, Object> model, String template) {

        MailResponse response = new MailResponse();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            // set mediaType
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Template t = configuration.getTemplate(template);
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            helper.setTo(request.getTo());
            helper.setText(html, true);
            helper.setSubject(request.getSubject());
            helper.setFrom(request.getFrom());
            javaMailSender.send(mimeMessage);

            response.setMessage("mail send to : " + request.getTo());
            response.setStatus(Boolean.TRUE);

        } catch (MessagingException | IOException | TemplateException e) {
            response.setMessage("Mail Sending failure : " + e.getMessage());
            response.setStatus(Boolean.FALSE);
        }
        return response;
    }
}
