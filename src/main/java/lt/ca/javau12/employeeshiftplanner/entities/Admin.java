package lt.ca.javau12.employeeshiftplanner.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends UserBase {

    private String position;


    public Admin(String firstName, String lastName ,String email, String phone, String position, String password, Role role) {
        super(firstName, lastName, email, phone, position, password, role);
    }


    public Admin() {

    }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    

}

