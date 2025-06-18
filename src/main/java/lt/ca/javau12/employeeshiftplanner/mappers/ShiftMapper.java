package lt.ca.javau12.employeeshiftplanner.mappers;

import lt.ca.javau12.employeeshiftplanner.dto.EmployeeDTO;
import lt.ca.javau12.employeeshiftplanner.dto.ShiftDTO;
import lt.ca.javau12.employeeshiftplanner.entities.Shift;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ShiftMapper {

    private final EmployeeMapper employeeMapper;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public ShiftMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public ShiftDTO toDto(Shift entity) {
        EmployeeDTO employeeDTO = null;
        if (entity.getEmployee() != null) {
            employeeDTO = employeeMapper.toDto(entity.getEmployee());
        }

        String shiftDateStr = entity.getShiftDate() != null ? entity.getShiftDate().format(dateFormatter) : null;

        return new ShiftDTO(
                entity.getId(),
                shiftDateStr,
                entity.getStartTime() != null ? entity.getStartTime().format(dateTimeFormatter) : null,
                entity.getEndTime() != null ? entity.getEndTime().format(dateTimeFormatter) : null,
                entity.getName(),
                employeeDTO
        );
    }

    public Shift toEntity(ShiftDTO dto) {
        LocalDate shiftDate = dto.getShiftDate() != null ? LocalDate.parse(dto.getShiftDate(), dateFormatter) : null;

        LocalDateTime start = dto.getStartTime() != null
                ? LocalDateTime.parse(dto.getStartTime(), dateTimeFormatter)
                : null;

        LocalDateTime end = dto.getEndTime() != null
                ? LocalDateTime.parse(dto.getEndTime(), dateTimeFormatter)
                : null;

        return new Shift(dto.getName(), shiftDate, start, end);
    }
}
