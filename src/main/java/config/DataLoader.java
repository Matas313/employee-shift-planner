package config;



import lt.ca.javau12.employeeshiftplanner.entities.Admin;
import lt.ca.javau12.employeeshiftplanner.entities.Employee;
import lt.ca.javau12.employeeshiftplanner.entities.Role;
import lt.ca.javau12.employeeshiftplanner.repositories.UserBaseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserBaseRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserBaseRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findByEmail("admin@example.com").isEmpty()) {
            Admin admin = new Admin("Admin","Admin","admin@example.com", "123456", "Manager", passwordEncoder.encode("adminpass"), Role.ADMIN);
            userRepository.save(admin);
        }

        if (userRepository.findByEmail("employee@example.com").isEmpty()) {
            Employee employee = new Employee("Emp", "EMP", "employee@example.com", "789101", "Worker", passwordEncoder.encode("employeepass"), Role.EMPLOYEE);
            userRepository.save(employee);
        }
    }
}
