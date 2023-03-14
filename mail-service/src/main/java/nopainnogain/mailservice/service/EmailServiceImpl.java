package nopainnogain.mailservice.service;

import nopainnogain.mailservice.configuration.Mail;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements MailSender {

    private final Mail mailSender = new Mail();
    private final JavaMailSender emailSender = mailSender.getJavaMailSender();

    public void sendSimpleMessage(
            String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ivanivanov2023_18@mail.ru");
        message.setTo(to);
        message.setSubject(subject);
        String textMail = text.formatted("""
                Для верификации перейдите по ссылке ниже:
                                
                "$s"
                """);
        message.setText(textMail);
        emailSender.send(message);
    }

    @Override
    public void send(SimpleMailMessage simpleMessage) throws MailException {

    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) throws MailException {

    }
}
