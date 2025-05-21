package lt.ca.javau12.employeeshiftplanner.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class ShiftDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long Id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String name;

    public ShiftDTO() {}

    public ShiftDTO(Long Id, LocalDateTime startTime, LocalDateTime endTime, String  name) {
        this.Id = Id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
    }

    public long getShiftId() {
        return Id;
    }

    public void setShiftId(long shiftId) {
        this.Id = shiftId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
