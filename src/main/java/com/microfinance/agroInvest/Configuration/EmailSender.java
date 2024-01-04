package com.microfinance.agroInvest.Configuration;

import com.microfinance.agroInvest.model.Agriculteur;
import com.microfinance.agroInvest.services.AgriculteurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailSender {

    @Autowired
    private AgriculteurServiceImpl agriculteurService;
    @Autowired
    private JavaMailSender mailSender;

    public void AlerteAgriculteur(
            String body,
            String subject
    ) {
        List<Agriculteur> agriculteurs = agriculteurService.affichertout();
        for (Agriculteur agriculteur : agriculteurs) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(agriculteur.getEmail());
           // message.setFrom("souleymane.7578@gmail.com");
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);

            System.out.println("Alerte effectuer avec succès....");
        }

    }
}
