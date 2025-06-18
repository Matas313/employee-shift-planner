package lt.ca.javau12.employeeshiftplanner.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ca.javau12.employeeshiftplanner.entities.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
}
