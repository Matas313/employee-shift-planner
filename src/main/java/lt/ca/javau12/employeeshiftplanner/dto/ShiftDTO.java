package lt.ca.javau12.employeeshiftplanner.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShiftDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;  // mažosiomis, kad JSON būtų "id"

    private int startTime;
    private int endTime;
    private String name;

    // jei reikia, gali pridėti employee
    private EmployeeDTO employee;

    public ShiftDTO() {}

    public ShiftDTO(Long id, int startTime, int endTime, String name, EmployeeDTO employee) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.employee = employee;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getStartTime() { return startTime; }
    public void setStartTime(int startTime) { this.startTime = startTime; }

    public int getEndTime() { return endTime; }
    public void setEndTime(int endTime) { this.endTime = endTime; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public EmployeeDTO getEmployee() { return employee; }
    public void setEmployee(EmployeeDTO employee) { this.employee = employee; }
}
