package ru.redcollar.notification.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.redcollar.notification.domain.Mail;
import ru.redcollar.notification.service.EmailService;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final EmailService emailService;

    @PreAuthorize("hasRole('ROLE_CLIENT_MAIL')")
    @PostMapping("/email")
    public ResponseEntity<Void> sendMail(@RequestBody Mail mail){
        emailService.sendEmail(mail);
        return ResponseEntity.ok().build();
    }
}
