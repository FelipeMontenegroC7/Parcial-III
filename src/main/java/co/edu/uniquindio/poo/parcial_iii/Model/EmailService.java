package co.edu.uniquindio.poo.parcial_iii.Model;


import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {

    //password: bdtg vyzm ufvq mygq
    private static final String EMAIL_ORIGEN = "neodeliveryuq@gmail.com";
    private static final String APP_PASSWORD = "bdtg vyzm ufvq mygq";

    private static final Session session;

    static {
        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_ORIGEN, APP_PASSWORD);
            }
        });
    }

    public static void sendEmail(String destinatario, String asunto, String mensajeTexto) {
        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(EMAIL_ORIGEN));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(mensajeTexto);

            Transport.send(message);

            System.out.println("Correo enviado a " + destinatario);

        } catch (MessagingException e) {
            System.out.println("Error enviando correo: " + e.getMessage());
        }
    }
}
