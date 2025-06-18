package lt.ca.javau12.employeeshiftplanner.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.ca.javau12.employeeshiftplanner.entities.UserBase;

@Repository
public interface UserBaseRepository extends JpaRepository<UserBase, Long> {
    Optional<UserBase> findByEmail(String email);
}

