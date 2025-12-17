package com.ashish.MoneyManager.controller;

import com.ashish.MoneyManager.dto.ContactRequest;
import com.ashish.MoneyManager.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {

    private final MailService mailService;

    @Value("${CONTACT_EMAIL}")
    private String adminEmail;

    @PostMapping
    public ResponseEntity<?> sendContactMessage(@RequestBody ContactRequest request) {

        String subject = "New Contact Message from " + request.getName();

        String body =
                "Name: " + request.getName() + "\n" +
                        "Email: " + request.getEmail() + "\n\n" +
                        "Message:\n" + request.getMessage();

        mailService.sendEmail(adminEmail, subject, body);

        return ResponseEntity.ok("Message sent to Admin");
    }
}
