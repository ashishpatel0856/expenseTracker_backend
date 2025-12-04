package com.ashish.MoneyManager.controller;

import com.ashish.MoneyManager.dto.ContactRequest;
import com.ashish.MoneyManager.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {

    private final EmailService emailService;

    @Value("${CONTACT_EMAIL}")
    private String adminEmail;

    @PostMapping
    public ResponseEntity<?> sendContactMessage(@RequestBody ContactRequest request) {

        String subject = "New Contact Message from " + request.getName();

        String body =
                "Name: " + request.getName() + "\n" +
                        "Email: " + request.getEmail() + "\n\n" +
                        "Message:\n" + request.getMessage();

        // Existing email service method call
        emailService.sendEmail(adminEmail, subject, body);

        return ResponseEntity.ok("Message sent to Admin");
    }
}
