package lt.ca.javau12.employeeshiftplanner.mappers;

import lt.ca.javau12.employeeshiftplanner.dto.EmployeeDTO;
import lt.ca.javau12.employeeshiftplanner.entities.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeDTO toDto(Employee entity) {
        return new EmployeeDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getPosition()
        );
    }

    public Employee toEntity(EmployeeDTO dto) {
        return new Employee(
                dto.getName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getPosition()
        );
    }


}
