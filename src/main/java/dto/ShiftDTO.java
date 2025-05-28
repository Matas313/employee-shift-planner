package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShiftDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long Id;

    private int startTime;
    private int endTime;
    private String name;

    public ShiftDTO() {}

    public ShiftDTO(Long Id, int startTime, int endTime, String  name) {
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

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
