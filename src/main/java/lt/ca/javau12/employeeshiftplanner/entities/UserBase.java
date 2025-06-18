package lt.ca.javau12.employeeshiftplanner.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public abstract  class UserBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String position;
    private String password;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    private boolean enabled = true;

    public boolean isEnabled() { return enabled; }

    public UserBase() {}

    public UserBase(String firstName, String lastName, String email, String phone, String position, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.position = position;
        this.password = password;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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

	public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
    

}
