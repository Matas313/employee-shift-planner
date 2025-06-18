package lt.ca.javau12.employeeshiftplanner.controllers;

import lt.ca.javau12.employeeshiftplanner.dto.AdminDTO;
import lt.ca.javau12.employeeshiftplanner.dto.EmployeeDTO;
import lt.ca.javau12.employeeshiftplanner.entities.Employee;
import lt.ca.javau12.employeeshiftplanner.repositories.EmployeeRepository;
import lt.ca.javau12.employeeshiftplanner.services.AdminService;
import lt.ca.javau12.employeeshiftplanner.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    public AdminController(AdminService adminService, EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.adminService = adminService;
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAllAdmins(){

        return ResponseEntity.ok(adminService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id){
        return ResponseEntity.of(adminService.byId(id));
    }
    @GetMapping("/allemployees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping("/add")
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody AdminDTO dto){
        AdminDTO created = adminService.createAdmin(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @PostMapping("/addemployees")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO dto){
        EmployeeDTO create = employeeService.createEmployee(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id){
        boolean deleted = adminService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        boolean deleted = employeeService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/dashboard")
    public String dashboard() {
        return "Sveiki atvykę į admin zoną";
    }
    @GetMapping("/debug")
    public ResponseEntity<?> debug() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(Map.of(
            "user", auth.getName(),
            "roles", auth.getAuthorities()
        ));
    }
    
    @GetMapping("/me")
    public ResponseEntity<AdminDTO> getMyProfile(Authentication authentication) {
        String username = authentication.getName();
        AdminDTO adminDTO = adminService.getAdminProfileByUsername(username);
        return ResponseEntity.ok(adminDTO);
    }

    @PutMapping("/me")
    public ResponseEntity<AdminDTO> updateMyProfile(
            Authentication authentication,
            @RequestBody AdminDTO updatedProfile
    ) {
        String username = authentication.getName();
        AdminDTO updated = adminService.updateAdminProfile(username, updatedProfile);
        return ResponseEntity.ok(updated);
    }
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Employee employee = optionalEmployee.get();

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setPhone(updatedEmployee.getPhone());
        employee.setPosition(updatedEmployee.getPosition());

        employeeRepository.save(employee);
        return ResponseEntity.ok(employee);
    }
    @GetMapping("/employeestime")
    public List<EmployeeDTO> getAllEmployeesWorkingTime() {
        return employeeService.getAllEmployeesWithTotalHours();
    }


}
