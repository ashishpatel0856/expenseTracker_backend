package com.ashish.MoneyManager.controller;

import com.ashish.MoneyManager.dto.ProfileDto;
import com.ashish.MoneyManager.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/register")
    public ResponseEntity<ProfileDto> registerProfile(@RequestBody ProfileDto profileDto) {
        ProfileDto profile = profileService.registerProfile(profileDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(profile);
    }
}
