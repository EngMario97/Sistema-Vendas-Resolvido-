/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.model;

import br.com.sistema.view.Frmmenu;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;


/**
 *
 * @author Tampelini
 */
public class Utilitarios {

    //metodo limparCampos
    public void LimpaTela(JPanel container) {
        Component components[] = container.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText(null);
            }
        }
    }

    //Metodo para adicionar imagem de fundo JDesktopPane
//    public void adicionaImagem() {
//
//        ImageIcon icon = new ImageIcon(Frmmenu.class.getResource("br.com.projeto.images/fundo.jpg"));
//        Image img = icon.getImage();
//
//        JDesktopPane painel = new JDesktopPane() {
//            public void paintComponent(Graphics g) {
//                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
//            }
//
//        }
               
    public boolean isAllFilled(JPanel container) {
        boolean bool = true;
        Component components[] = container.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField && component.getName() == null) {
                if (((JTextField) component).getText().isEmpty()) {
                    bool = false;
                    break;
                }
            }
        }
        return bool;
    }
    

public void JavaEmail(String email, String senha) {
    Properties props = new Properties();
    /** Parâmetros de conexão com servidor Gmail
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class",
    "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "465");
    */
    
    int stmpport = 465;
    
    String  d_email = "sistemavendasbiopark@gmail.com",
        d_password = "biopark123",
        d_host = "smtp.gmail.com",
        d_port  = "465", //465,587
        m_to = email,
        m_subject = "Troca de senha",
        m_text = "Sua nova senha é: " + senha;
    
    props.put("mail.smtp.user", d_email);
    props.setProperty("mail.user", d_email);
    props.put("mail.password", d_password);
    props.put("mail.smtp.host", d_host);
    props.put("mail.smtp.port", d_port);
    props.put("mail.smtp.ssl.trust", d_host);
    props.put("mail.smtp.ssl.enable", "true");
    props.put("mail.smtp.debug", "true");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.starttls.required", "true");
    props.put("mail.smtp.ssl.protocols", "TLSv1.2");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.socketFactory.port", d_port);
    props.put("mail.smtp.socketFactory.fallback", "false");
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.debug", true);

      // Get system properties
      System.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.host", d_host);
    
    Session session = Session.getDefaultInstance(props,
      new javax.mail.Authenticator() {
          @Override
           protected PasswordAuthentication getPasswordAuthentication()
           {
                 return new PasswordAuthentication(d_email, d_password);
           }
      });
    

    //Ativa Debug para sessão 
    session.setDebug(true);
    
    try {
    
        Message msg = new MimeMessage(session);
        msg.setText(m_text);
        msg.setSubject(m_subject);
        msg.setFrom(new InternetAddress(d_email));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(m_to));

        Address[] toUser = InternetAddress.parse(email);
       
        Transport transport = session.getTransport("smtps");
        
        transport.send(msg);

        JOptionPane.showMessageDialog(null, "Email enviado!");

     } catch (MessagingException e) {
        e.printStackTrace();
    }
  }
}
        
        