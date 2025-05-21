package lt.ca.javau12.employeeshiftplanner.repositories;

import lt.ca.javau12.employeeshiftplanner.entities.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
}