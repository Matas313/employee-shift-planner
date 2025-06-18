package lt.ca.javau12.employeeshiftplanner.repositories;

import lt.ca.javau12.employeeshiftplanner.entities.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	Admin findByEmail(String username);
}
