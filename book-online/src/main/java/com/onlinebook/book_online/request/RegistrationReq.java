package com.onlinebook.book_online.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class RegistrationReq {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email")
    @Pattern(regexp = "^[^\\s]+@gmail\\.com$", message = "Email must be suitable")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password at least 8 charaters")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?])(?=.*\\d)[A-Za-z\\d!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]8,}$",
            message = "Password must at least 1 Caps words, 1 Special Charaters and 1 numbers"
    )
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
