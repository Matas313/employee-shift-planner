package lt.ca.javau12.employeeshiftplanner.mappers;

import lt.ca.javau12.employeeshiftplanner.dto.EmployeeDTO;
import lt.ca.javau12.employeeshiftplanner.entities.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeDTO toDto(Employee entity) {
        return new EmployeeDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getPosition(),
                entity.getPassword(),
                0.0,
                entity.getRole()

        );
    }

    public Employee toEntity(EmployeeDTO dto) {
        return new Employee(
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
