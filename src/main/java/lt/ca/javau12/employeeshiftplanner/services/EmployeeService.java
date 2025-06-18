package lt.ca.javau12.employeeshiftplanner.services;

import lt.ca.javau12.employeeshiftplanner.dto.EmployeeDTO;
import lt.ca.javau12.employeeshiftplanner.entities.Employee;
import lt.ca.javau12.employeeshiftplanner.entities.Role;
import lt.ca.javau12.employeeshiftplanner.entities.Shift;
import lt.ca.javau12.employeeshiftplanner.mappers.EmployeeMapper;
import lt.ca.javau12.employeeshiftplanner.repositories.EmployeeRepository;
import lt.ca.javau12.employeeshiftplanner.repositories.ShiftRepository;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final ShiftRepository shiftRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            EmployeeMapper employeeMapper,
            ShiftRepository shiftRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.shiftRepository = shiftRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<EmployeeDTO> byId(Long id) {
        return employeeRepository
                .findById(id)
                .map(e -> employeeMapper.toDto(e));
    }

    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        Employee entity = employeeMapper.toEntity(dto);
        if (entity.getRole() == null) {
            entity.setRole(Role.EMPLOYEE);
        }
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        return employeeMapper.toDto(employeeRepository.save(entity));
    }


    public List<EmployeeDTO> getAllEmployees() {
            return employeeRepository.findAll()
                    .stream()
                    .map(employeeMapper::toDto)
                    .toList();
        }

    public boolean delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            return false;
        }

        List<Shift> shifts = shiftRepository.findByEmployeeId(id);
        for (Shift shift : shifts) {
            shift.setEmployee(null);
        }
        shiftRepository.saveAll(shifts);

        employeeRepository.deleteById(id);
        return true;
    }

    public Long getEmployeeIdByUsername(String username) {
        Employee employee = employeeRepository.findByEmail(username);
        if (employee == null) {
            throw new UsernameNotFoundException("Employee not found with email: " + username);
        }
        return employee.getId();
    }

    public EmployeeDTO getEmployeeProfileByUsername(String username) {
        Employee employee = employeeRepository.findByEmail(username);
        if (employee == null) {
            throw new UsernameNotFoundException("Darbuotojas su tokiu paštu nerastas: " + username);
        }
        return employeeMapper.toDto(employee);
    }

    public EmployeeDTO updateEmployeeProfile(String username, EmployeeDTO updatedProfile) {
        Employee employee = employeeRepository.findByEmail(username);
        if (employee == null) {
            throw new UsernameNotFoundException("Darbuotojas su tokiu paštu nerastas: " + username);
        }

        employee.setFirstName(updatedProfile.getFirstName());
        employee.setLastName(updatedProfile.getLastName());
        employee.setPhone(updatedProfile.getPhone());

        return employeeMapper.toDto(employeeRepository.save(employee));
    }
    private double calculateTotalHoursForEmployee(Long employeeId) {
        List<Shift> shifts = shiftRepository.findByEmployeeId(employeeId);
        return shifts.stream()
            .mapToDouble(shift -> Duration.between(shift.getStartTime(), shift.getEndTime()).toHours())
            .sum();
    }

    public List<EmployeeDTO> getAllEmployeesWithTotalHours() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map(employee -> {
            double totalHours = calculateTotalHoursForEmployee(employee.getId());
            return new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getPosition(),
                null,
                totalHours,
                employee.getRole()
            );
        }).collect(Collectors.toList());
    }




}
