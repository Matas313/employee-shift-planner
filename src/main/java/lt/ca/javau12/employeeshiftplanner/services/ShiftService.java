package lt.ca.javau12.employeeshiftplanner.services;

import lt.ca.javau12.employeeshiftplanner.dto.ShiftDTO;
import lt.ca.javau12.employeeshiftplanner.entities.Employee;
import lt.ca.javau12.employeeshiftplanner.entities.Shift;
import lt.ca.javau12.employeeshiftplanner.entities.UserBase;
import lt.ca.javau12.employeeshiftplanner.mappers.ShiftMapper;
import lt.ca.javau12.employeeshiftplanner.repositories.EmployeeRepository;
import lt.ca.javau12.employeeshiftplanner.repositories.ShiftRepository;
import lt.ca.javau12.employeeshiftplanner.repositories.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShiftService {

    private final ShiftRepository shiftRepository;
    private final ShiftMapper shiftMapper;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public ShiftService(ShiftRepository shiftRepository, ShiftMapper shiftMapper, EmployeeRepository employeeRepository, UserRepository userRepository) {
        this.shiftRepository = shiftRepository;
        this.shiftMapper = shiftMapper;
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
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
            .orElseThrow(() -> new RuntimeException("Shift not found"));

        UserBase user = userRepository.findById(employeeId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (!(user instanceof Employee)) {
            throw new RuntimeException("User is not an Employee");
        }

        shift.setEmployee((Employee) user);
        Shift savedShift = shiftRepository.save(shift);
        return shiftMapper.toDto(savedShift);
    }




    public boolean delete(Long id) {
        if(!shiftRepository.existsById(id)) {
            return false;
        }
        shiftRepository.deleteById(id);
        return true;
    }
}
