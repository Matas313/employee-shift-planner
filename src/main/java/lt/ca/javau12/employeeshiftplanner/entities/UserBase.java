package lt.ca.javau12.employeeshiftplanner.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class UserBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String possition;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "employee")
    private List<Shift> shifts;

    public UserBase() {}

    public UserBase(String name, String email, String phone, String possition, Role role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.possition = possition;
        this.role = role;
    }

    // Getters and setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getPossition() { return possition; }

    public void setPossition(String possition) { this.possition = possition; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    public List<Shift> getShifts() { return shifts; }

    public void setShifts(List<Shift> shifts) { this.shifts = shifts; }
}
