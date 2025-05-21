package lt.ca.javau12.employeeshiftplanner.services;

import lt.ca.javau12.employeeshiftplanner.dto.ShiftDTO;
import lt.ca.javau12.employeeshiftplanner.entities.Shift;
import lt.ca.javau12.employeeshiftplanner.mappers.ShiftMapper;
import lt.ca.javau12.employeeshiftplanner.repositories.ShiftRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShiftService {

    private final ShiftRepository shiftRepository;
    private final ShiftMapper shiftMapper;

    public ShiftService(ShiftRepository shiftRepository, ShiftMapper shiftMapper) {
        this.shiftRepository = shiftRepository;
        this.shiftMapper = shiftMapper;
    }

    public List<ShiftDTO> getAll() {
        return shiftRepository
                .findAll()
                .stream()
                .map(e -> shiftMapper.toDto(e))
                .toList();
    }

    public Optional<ShiftDTO> byId(long id) {
        return shiftRepository.findById(id).map(e -> shiftMapper.toDto(e));
    }

    public ShiftDTO create(ShiftDTO dto) {
        Shift entity = shiftMapper.toEntity(dto);
        return shiftMapper.toDto(shiftRepository.save(entity));
    }
}
