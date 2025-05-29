package lt.ca.javau12.employeeshiftplanner.mappers;

import lt.ca.javau12.employeeshiftplanner.dto.EmployeeDTO;
import lt.ca.javau12.employeeshiftplanner.dto.ShiftDTO;
import lt.ca.javau12.employeeshiftplanner.entities.Shift;
import org.springframework.stereotype.Component;

@Component
public class ShiftMapper {

    private final EmployeeMapper employeeMapper;

    public ShiftMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public ShiftDTO toDto(Shift entity) {
        EmployeeDTO employeeDTO = null;
        if (entity.getEmployee() != null) {
            employeeDTO = employeeMapper.toDto(entity.getEmployee());
        }

        return new ShiftDTO(
                entity.getId(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getName(),
                employeeDTO
        );
    }

    public Shift toEntity(ShiftDTO dto) {
        Shift shift = new Shift(
                dto.getName(),
                dto.getStartTime(),
                dto.getEndTime()
        );


        return shift;
    }
}
