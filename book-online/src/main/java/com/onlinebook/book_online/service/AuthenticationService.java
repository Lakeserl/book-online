package com.onlinebook.book_online.service;

import com.onlinebook.book_online.Response.AuthenticationResponse;
import com.onlinebook.book_online.models.EmailTemp;
import com.onlinebook.book_online.models.Token;
import com.onlinebook.book_online.models.User;
import com.onlinebook.book_online.repository.RoleRepository;
import com.onlinebook.book_online.repository.TokenRepository;
import com.onlinebook.book_online.repository.UserRepository;
import com.onlinebook.book_online.request.AuthenticationReq;
import com.onlinebook.book_online.request.RegistrationReq;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationService {

//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//    private final RoleRepository roleRepository;
//    private final EmailService emailService;
//    private final TokenRepository tokenRepository;
//
//    @Value("${application.mailing.frontend.activation-url}")
//    private String activationUrl;
//
//    public void register(RegistrationReq request) throws MessagingException {
//        var userRole = roleRepository.findByName("USER")
//                // todo - better exception handling
//                .orElseThrow(() -> new IllegalStateException("ROLE USER was not initiated"));
//        var user = User.builder()
//                .firstName(request.getFirstname())
//                .lastName(request.getLastname())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .accountLocked(false)
//                .enabled(false)
//                .roles(List.of(userRole))
//                .build();
//        //userRepository.save(user);
//        sendValidationEmail(user);
//    }
//
//    public AuthenticationResponse authenticate(AuthenticationReq request) {
//        var auth = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//
//        var claims = new HashMap<String, Object>();
//        var user = ((User) auth.getPrincipal());
//        claims.put("fullName", user.getFullName());
//
//        var jwtToken = jwtService.generateToken(claims, (User) auth.getPrincipal());
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
//
//    @Transactional
//    public void activateAccount(String token) throws MessagingException {
//        Token savedToken = tokenRepository.findByToken(token)
//                // todo exception has to be defined
//                .orElseThrow(() -> new RuntimeException("Invalid token"));
//        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
//            sendValidationEmail(savedToken.getUser());
//            throw new RuntimeException("Activation token has expired. A new token has been send to the same email address");
//        }
//
//        var user = userRepository.findById(savedToken.getUser().getId())
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        user.setEnabled(true);
//        userRepository.save(user);
//
//        savedToken.setValidatedAt(LocalDateTime.now());
//        tokenRepository.save(savedToken);
//    }
//
//    private String generateAndSaveActivationToken(User user) {
//        // Generate a token
//        String generatedToken = generateActivationCode(6);
//        var token = Token.builder()
//                .token(generatedToken)
//                .createdAt(LocalDateTime.now())
//                .expiresAt(LocalDateTime.now().plusMinutes(15))
//                .user(user)
//                .build();
//        tokenRepository.save(token);
//
//        return generatedToken;
//    }
//
//    private void sendValidationEmail(User user) throws MessagingException {
//        var newToken = generateAndSaveActivationToken(user);
//
//        emailService.sendEmail(
//                user.getEmail(),
//                user.getFullName(),
//                EmailTemp.ACTIVATE_ACCOUNT,
//                activationUrl,
//                newToken,
//                "Account activation"
//                );
//    }
//
//    private String generateActivationCode(int length) {
//        String characters = "0123456789";
//        StringBuilder codeBuilder = new StringBuilder();
//
//        SecureRandom secureRandom = new SecureRandom();
//
//        for (int i = 0; i < length; i++) {
//            int randomIndex = secureRandom.nextInt(characters.length());
//            codeBuilder.append(characters.charAt(randomIndex));
//        }
//
//        return codeBuilder.toString();
//    }
}
