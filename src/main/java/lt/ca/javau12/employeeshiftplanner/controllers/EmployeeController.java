package lt.ca.javau12.employeeshiftplanner.controllers;


import lt.ca.javau12.employeeshiftplanner.dto.EmployeeDTO;
import lt.ca.javau12.employeeshiftplanner.dto.ShiftDTO;
import lt.ca.javau12.employeeshiftplanner.services.EmployeeService;
import lt.ca.javau12.employeeshiftplanner.services.ShiftService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ShiftService shiftService;

    public EmployeeController (EmployeeService employeeService,ShiftService shiftService ){
        this.employeeService = employeeService;
        this.shiftService = shiftService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id){
        return ResponseEntity.of(employeeService.byId(id));
    }
    @GetMapping("/myshifts")
    public ResponseEntity<List<ShiftDTO>> getMyShifts(Authentication authentication) {
        String username = authentication.getName();
        Long employeeId = employeeService.getEmployeeIdByUsername(username);
        List<ShiftDTO> shifts = shiftService.getShiftsById(employeeId);
        return ResponseEntity.ok(shifts);
    }
    @GetMapping("/me")
    public ResponseEntity<EmployeeDTO> getMyProfile(Authentication authentication) {
        String username = authentication.getName();
        EmployeeDTO employeeDTO = employeeService.getEmployeeProfileByUsername(username);
        return ResponseEntity.ok(employeeDTO);
    }

    @PutMapping("/me")
    public ResponseEntity<EmployeeDTO> updateMyProfile(
            Authentication authentication,
            @RequestBody EmployeeDTO updatedProfile
    ) {
        String username = authentication.getName();
        EmployeeDTO updated = employeeService.updateEmployeeProfile(username, updatedProfile);
        return ResponseEntity.ok(updated);
    }
    

}
