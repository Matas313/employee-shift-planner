package lt.ca.javau12.employeeshiftplanner.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends UserBase {

    private String position;


    public Admin(String name, String email, String phone, String position) {
        this.setName(name);
        this.setEmail(email);
        this.setPhone(phone);
        this.position = position;
    }


    public Admin() {

    }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

}

