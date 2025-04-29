package com.onlinebook.book_online.controller;

import com.onlinebook.book_online.Response.AuthenticationResponse;
import com.onlinebook.book_online.request.AuthenticationReq;
import com.onlinebook.book_online.request.RegistrationReq;
import com.onlinebook.book_online.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {

//    private final AuthenticationService service;
//
//    @PostMapping("/register")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public ResponseEntity<?> register(
//            @RequestBody @Valid RegistrationReq req
//    ) throws MessagingException {
//         service.register(req);
//        return ResponseEntity.accepted().build();
//    }
//
//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> authenticate(
//            @RequestBody AuthenticationReq request
//    ) {
//        return ResponseEntity.ok(service.authenticate(request));
//    }
//    @GetMapping("/activate-account")
//    public void confirm(
//            @RequestParam String token
//    ) throws MessagingException {
//        service.activateAccount(token);
//    }

}
