package lt.ca.javau12.employeeshiftplanner.controllers;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.ca.javau12.employeeshiftplanner.entities.Employee;
import lt.ca.javau12.employeeshiftplanner.entities.PasswordResetToken;
import lt.ca.javau12.employeeshiftplanner.entities.Role;
import lt.ca.javau12.employeeshiftplanner.repositories.PasswordResetTokenRepository;
import lt.ca.javau12.employeeshiftplanner.repositories.UserBaseRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserBaseRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetTokenRepository tokenRepository;

    public UserController(UserBaseRepository userRepository, PasswordEncoder passwordEncoder,
                          PasswordResetTokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            return ResponseEntity.badRequest().body("El. paštas jau užimtas");
        }

        Employee user = new Employee();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.EMPLOYEE);
        user.setPosition(request.position());

        userRepository.save(user);

        return ResponseEntity.ok("Vartotojas sėkmingai sukurtas");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPasswordSimple(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String newPassword = body.get("password");

        if (email == null || email.isEmpty() || newPassword == null || newPassword.isEmpty()) {
            return ResponseEntity.badRequest().body("El. paštas ir naujas slaptažodis privalomi");
        }

        Optional<Employee> userOpt = userRepository.findByEmail(email).map(u -> (Employee) u);

        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Vartotojas nerastas");
        }

        Employee user = userOpt.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return ResponseEntity.ok("Slaptažodis sėkmingai pakeistas");
    }


    record RegisterUserRequest(String firstName, String lastName, String email, String password, String position) {
    }
}
