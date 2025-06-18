package lt.ca.javau12.employeeshiftplanner.mappers;

import lt.ca.javau12.employeeshiftplanner.dto.AdminDTO;
import lt.ca.javau12.employeeshiftplanner.dto.EmployeeDTO;
import lt.ca.javau12.employeeshiftplanner.entities.Admin;
import lt.ca.javau12.employeeshiftplanner.entities.Employee;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public AdminDTO toDto(Admin entity) {
        return new AdminDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getPosition(),
                entity.getPassword(),
                entity.getRole()
        );
    }

    public Admin toEntity(AdminDTO dto) {
        return new Admin(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getPosition(),
                dto.getPassword(),
                dto.getRole()
        );
    }



}
