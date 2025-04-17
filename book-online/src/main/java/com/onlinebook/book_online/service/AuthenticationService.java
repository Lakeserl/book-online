package com.onlinebook.book_online.service;

import com.onlinebook.book_online.models.EmailTemp;
import com.onlinebook.book_online.models.Token;
import com.onlinebook.book_online.models.User;
import com.onlinebook.book_online.repository.RoleRepository;
import com.onlinebook.book_online.repository.TokenRepository;
import com.onlinebook.book_online.repository.UserRepository;
import com.onlinebook.book_online.request.RegistrationReq;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    private final EmailService emailService;
    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    public void register(RegistrationReq registrationReq) throws MessagingException {
        var userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("ROLE_USER was not initialized"));

        var user = User.builder()
                .firstName(registrationReq.getFirstName())
                .lastName(registrationReq.getLastName())
                .email(registrationReq.getEmail())
                .password(passwordEncoder.encode(registrationReq.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();

        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveActivitionTokens(user);

        emailService.sendEmail(
                user.getEmail(),
                user.fullName(),
                EmailTemp.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Account activation"
        );
    }

    private String generateAndSaveActivitionTokens(User user) {
        String generatedToken = generateActivationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusDays(30))
                .user(user)
                .build();

        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String charaters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for(int i = 0; i < length; i++) {
            int randomChar = secureRandom.nextInt(charaters.length());
            codeBuilder.append(charaters.charAt(randomChar));
        }
        return codeBuilder.toString();
    }
}
