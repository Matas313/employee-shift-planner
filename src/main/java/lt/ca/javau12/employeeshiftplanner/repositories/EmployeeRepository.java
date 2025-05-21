package lt.ca.javau12.employeeshiftplanner.repositories;

import lt.ca.javau12.employeeshiftplanner.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
