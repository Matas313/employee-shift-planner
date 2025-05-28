package services;

import dto.ShiftDTO;
import entities.Employee;
import entities.Shift;
import mappers.ShiftMapper;
import org.springframework.stereotype.Service;
import repositories.EmployeeRepository;
import repositories.ShiftRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ShiftService {

    private final ShiftRepository shiftRepository;
    private final ShiftMapper shiftMapper;
    private final EmployeeRepository employeeRepository;

    public ShiftService(ShiftRepository shiftRepository, ShiftMapper shiftMapper, EmployeeRepository employeeRepository) {
        this.shiftRepository = shiftRepository;
        this.shiftMapper = shiftMapper;
        this.employeeRepository = employeeRepository;
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

    public List<ShiftDTO> getShiftsById(Long employeeId) {
        return shiftRepository.findAll()
                .stream()
                .filter(shift -> {
                    return shift.getEmployee() != null && shift.getEmployee().getId().equals(employeeId);
                })
                .map(shiftMapper::toDto)
                .toList();
    }
    public List<ShiftDTO> getShiftsByEmployeeId(Long employeeId) {
        return shiftRepository.findByEmployeeId(employeeId)
                .stream()
                .map(shiftMapper::toDto)
                .toList();
    }

    public ShiftDTO assignShiftToEmployee(Long shiftId, Long employeeId) {
        Shift shift = shiftRepository.findById(shiftId)
                .orElseThrow(() -> new RuntimeException("Pamaina nerasta"));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Darbuotojas nerastas"));

        shift.setEmployee(employee);
        return shiftMapper.toDto(shiftRepository.save(shift));
    }

    public boolean delete(Long id) {
        if(!shiftRepository.existsById(id)) {
            return false;
        }
        shiftRepository.deleteById(id);
        return true;
    }
}
