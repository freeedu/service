package org.personal.mason.feop.oauth.service.common.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.util.StringUtils;

import javax.mail.internet.MimeMessage;

public class EmailSender {

    private JavaMailSender javaMailSender;

    private String sender;

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void sendEmail(final String toEmailAddr, final String subject, final String body) {
        sendEmail(toEmailAddr, sender, subject, body, null, null);
    }

    public void sendEmail(final String toEmailAddr, final String fromEmailAddr, final String subject, final String body) {
        sendEmail(toEmailAddr, fromEmailAddr, subject, body, null, null);
    }

    public void sendEmailWithAttachment(final String toEmailAddr, final String subject, final String body, final String attachmentPath,
                                        final String attachmentName) {
        sendEmail(toEmailAddr, sender, subject, body, attachmentPath, attachmentName);
    }

    public void sendEmailWithAttachment(final String toEmailAddr, final String fromEmailAddr, final String subject, final String body,
                                        final String attachmentPath, final String attachmentName) {
        sendEmail(toEmailAddr, fromEmailAddr, subject, body, attachmentPath, attachmentName);
    }

    private void sendEmail(final String toEmailAddr, final String fromEmailAddr, final String subject, final String body,
                           final String attachmentPath, final String attachmentName) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
                messageHelper.setTo(toEmailAddr);
                messageHelper.setFrom(fromEmailAddr);
                messageHelper.setSubject(subject);
                messageHelper.setText(body, true);

                if (!StringUtils.isEmpty(attachmentPath)) {
                    FileSystemResource file = new FileSystemResource(attachmentPath);
                    messageHelper.addAttachment(attachmentName, file);
                }
            }
        };
        javaMailSender.send(preparator);
    }

}
