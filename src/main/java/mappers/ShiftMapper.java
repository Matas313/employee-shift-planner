package mappers;

import dto.ShiftDTO;
import entities.Shift;
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
