package lt.ca.javau12.employeeshiftplanner.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lt.ca.javau12.employeeshiftplanner.dto.JwtAuthenticationResponse;
import lt.ca.javau12.employeeshiftplanner.dto.LoginRequest;
import lt.ca.javau12.employeeshiftplanner.security.JwtTokenProvider;
import lt.ca.javau12.employeeshiftplanner.security.JwtUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public LoginController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
                )
            );

            String jwt = tokenProvider.generateToken(authentication);

            String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("UNKNOWN");

            Map<String, String> response = new HashMap<>();
            response.put("token", jwt);
            response.put("role", role);

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Neteisingi prisijungimo duomenys");
        } catch (DisabledException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Paskyra yra išjungta");
        } catch (LockedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Paskyra yra užrakinta");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Autentifikacijos klaida");
        }
    }





    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Neautorizuotas");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String email = userDetails.getUsername();
        String role = userDetails.getAuthorities().stream()
                .findFirst()
                .map(auth -> auth.getAuthority())
                .orElse("");

        return ResponseEntity.ok(Map.of(
            "email", email,
            "role", role
        ));
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.ok().build();
    }

}








