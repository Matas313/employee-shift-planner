package lt.ca.javau12.employeeshiftplanner.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.List;

    @Entity
    public class Employee extends UserBase {

    private String position;

    @OneToMany(mappedBy = "employee")
    private List<Shift> shifts;

        public Employee(String name, String email, String phone, String position) {
            super(name, email, phone, position, null);
        }


        public Employee() {

        }
}
