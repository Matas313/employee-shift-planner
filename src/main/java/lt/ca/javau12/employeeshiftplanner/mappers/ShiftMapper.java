package lt.ca.javau12.employeeshiftplanner.mappers;

import lt.ca.javau12.employeeshiftplanner.dto.ShiftDTO;
import lt.ca.javau12.employeeshiftplanner.entities.Shift;
import org.springframework.stereotype.Component;

@Component
public class ShiftMapper {

    public ShiftDTO toDto(Shift entity) {
        return new ShiftDTO(
                entity.getId(),
                entity.getEndTime(),
                entity.getStartTime(),
                entity.getName()
        );
    }

    public Shift toEntity(ShiftDTO dto) {
        return new Shift(
                dto.getName(),
                dto.getStartTime(),
                dto.getEndTime()
        );
    }
}
