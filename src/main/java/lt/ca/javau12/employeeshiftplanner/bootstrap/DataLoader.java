package lt.ca.javau12.employeeshiftplanner.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lt.ca.javau12.employeeshiftplanner.entities.Admin;
import lt.ca.javau12.employeeshiftplanner.entities.Role;
import lt.ca.javau12.employeeshiftplanner.entities.UserBase;
import lt.ca.javau12.employeeshiftplanner.repositories.UserBaseRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserBaseRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserBaseRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByEmail("admin@admin.lt").isEmpty()) {
            Admin admin = new Admin();
            admin.setEmail("admin@admin.lt");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole(Role.ADMIN);
            admin.setFirstName("Admin Vardas");
            admin.setLastName("Pavarde");
            admin.setPosition("Administratorius");
            userRepository.save(admin);
        }
    }

}
