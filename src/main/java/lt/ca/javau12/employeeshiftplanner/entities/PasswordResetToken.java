package lt.ca.javau12.employeeshiftplanner.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;

    @ManyToOne
    private Employee user;

    private LocalDateTime expiryDate;

    public PasswordResetToken() {}

    public PasswordResetToken(String token, Employee user) {
        this.token = token;
        this.user = user;
        this.expiryDate = LocalDateTime.now().plusHours(1);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryDate);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Employee getUser() { return user; }
    public void setUser(Employee user) { this.user = user; }

    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }
}
