package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

    @Entity
    public class Employee extends UserBase {

    private String position;

    @OneToMany(mappedBy = "employee")
    private List<Shift> shifts;

    public Employee(String name, String email, String phone, String position) {
    }

        public Employee() {

        }

        public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public List<Shift> getShifts() { return shifts; }
    public void setShifts(List<Shift> shifts) { this.shifts = shifts; }
}
