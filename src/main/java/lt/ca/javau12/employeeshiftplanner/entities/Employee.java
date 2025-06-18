package lt.ca.javau12.employeeshiftplanner.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.List;

@Entity
@DiscriminatorValue("EMPLOYEE")
public class Employee extends UserBase {

    @OneToMany(mappedBy = "employee")
    private List<Shift> shifts;

    public Employee(String firstName, String lastName, String email, String phone, String position, String password, Role role) {
        super(firstName, lastName, email, phone, position, password, role);
    }

    public Employee() {
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }
    
}
