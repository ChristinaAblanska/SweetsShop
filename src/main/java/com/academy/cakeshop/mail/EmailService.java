package com.academy.cakeshop.mail;

import com.academy.cakeshop.mail.dto.EmailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendSimpleEmail(EmailDto emailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailDto.getToList());
        message.setSubject(emailDto.getSubject());
        message.setText(emailDto.getBody());
        mailSender.send(message);
    }
}