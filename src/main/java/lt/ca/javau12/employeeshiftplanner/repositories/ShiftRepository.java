package lt.ca.javau12.employeeshiftplanner.repositories;

import lt.ca.javau12.employeeshiftplanner.entities.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
    List<Shift> findByEmployeeId(Long employeeId);

}