package lt.ca.javau12.employeeshiftplanner.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeDTO {

    @JsonProperty (access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String position;

    public EmployeeDTO() {}
    public EmployeeDTO(Long id, String name, String email, String phone, String position) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
