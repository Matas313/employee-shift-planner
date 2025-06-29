package lt.ca.javau12.employeeshiftplanner.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate shiftDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Shift() {}

    public Shift(String name,LocalDate shiftDate, LocalDateTime startTime, LocalDateTime endTime) {
        this.name = name;
        this.shiftDate = shiftDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        

        public LocalDate getShiftDate() {
			return shiftDate;
		}

		public void setShiftDate(LocalDate shiftDate) {
			this.shiftDate = shiftDate;
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

        public Employee getEmployee() {
            return employee;
        }

        public void setEmployee(Employee employee) {
            this.employee = employee;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }


