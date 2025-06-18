package lt.ca.javau12.employeeshiftplanner.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShiftDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String shiftDate;
    private String startTime;
    private String endTime;
    private String name;

    private EmployeeDTO employee;

    public ShiftDTO() {}

    public ShiftDTO(Long id, String shiftDate, String startTime, String endTime, String name, EmployeeDTO employee) {
        this.id = id;
        this.shiftDate = shiftDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.employee = employee;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getShiftDate() {
		return shiftDate;
	}

	public void setShiftDate(String shiftDate) {
		this.shiftDate = shiftDate;
	}
	public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public EmployeeDTO getEmployee() { return employee; }
    public void setEmployee(EmployeeDTO employee) { this.employee = employee; }
}
