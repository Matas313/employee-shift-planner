package lt.ca.javau12.employeeshiftplanner.controllers;

import lt.ca.javau12.employeeshiftplanner.dto.AdminDTO;
import lt.ca.javau12.employeeshiftplanner.dto.EmployeeDTO;
import lt.ca.javau12.employeeshiftplanner.services.AdminService;
import lt.ca.javau12.employeeshiftplanner.services.EmployeeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;
    private final EmployeeService employeeService;

    public AdminController(AdminService adminService, EmployeeService employeeService ) {
        this.adminService = adminService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAllAdmins(){
        return ResponseEntity.ok(adminService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id){
        return ResponseEntity.of(adminService.byId(id));
    }

    @PostMapping
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody AdminDTO dto){
        AdminDTO created = adminService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
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
    @PostMapping("/addemployees")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO dto) {
        EmployeeDTO created = employeeService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @GetMapping("/allemployees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAll());
    }
    
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        boolean deleted = employeeService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
