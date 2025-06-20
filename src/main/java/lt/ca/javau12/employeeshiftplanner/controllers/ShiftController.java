package lt.ca.javau12.employeeshiftplanner.controllers;

import lt.ca.javau12.employeeshiftplanner.dto.ShiftDTO;
import lt.ca.javau12.employeeshiftplanner.entities.AssignShiftRequest;
import lt.ca.javau12.employeeshiftplanner.services.ShiftService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/shifts")
public class ShiftController {

    private final ShiftService shiftService;

    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @GetMapping
    public ResponseEntity<List<ShiftDTO>> getAll() {
        return ResponseEntity.ok(shiftService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShiftDTO> getShiftById(@PathVariable Long id) {
        return ResponseEntity.of(shiftService.byId(id));
    }
    @PostMapping
    public ResponseEntity<ShiftDTO> create(@RequestBody ShiftDTO dto) {
        return ResponseEntity
                .status(HttpStatusCode.valueOf(201))
                .body(shiftService.create(dto));
    }
    @GetMapping("/employee/{id}")
    public ResponseEntity<List<ShiftDTO>> getEmployeeShifts(@PathVariable Long id) {
        return ResponseEntity.ok(shiftService.getShiftsById(id));
    }
    @PostMapping("/{id}/assign")
    public ResponseEntity<ShiftDTO> assignShift(@PathVariable Long id, @RequestBody AssignShiftRequest request) {
        return ResponseEntity.ok(shiftService.assignShiftToEmployee(id, request.getEmployeeId()));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id){
        boolean deleted = shiftService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ShiftDTO> updateShift(@PathVariable Long id, @RequestBody ShiftDTO dto) {
        return shiftService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



}
