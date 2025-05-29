package lt.ca.javau12.employeeshiftplanner.services;

import lt.ca.javau12.employeeshiftplanner.dto.AdminDTO;
import lt.ca.javau12.employeeshiftplanner.dto.EmployeeDTO;
import lt.ca.javau12.employeeshiftplanner.entities.Admin;
import lt.ca.javau12.employeeshiftplanner.entities.Employee;
import lt.ca.javau12.employeeshiftplanner.mappers.EmployeeMapper;
import lt.ca.javau12.employeeshiftplanner.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            EmployeeMapper employeeMapper
    ) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public Optional<EmployeeDTO> byId(Long id) {
        return employeeRepository
                .findById(id)
                .map(e -> employeeMapper.toDto(e));
    }

    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        Employee entity = employeeMapper.toEntity(dto);
        return employeeMapper.toDto(employeeRepository.save(entity));
    }

    public List<EmployeeDTO> getAllEmployees() {
            return employeeRepository.findAll()
                    .stream()
                    .map(employeeMapper::toDto)
                    .toList();
        }

    public boolean delete(Long id) {
            if(!employeeRepository.existsById(id)) {
                return false;
            }
            employeeRepository.deleteById(id);
            return true;
    }
}
