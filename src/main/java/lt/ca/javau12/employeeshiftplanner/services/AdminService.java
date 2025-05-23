package lt.ca.javau12.employeeshiftplanner.services;

import lt.ca.javau12.employeeshiftplanner.dto.AdminDTO;
import lt.ca.javau12.employeeshiftplanner.entities.Admin;
import lt.ca.javau12.employeeshiftplanner.mappers.AdminMapper;
import lt.ca.javau12.employeeshiftplanner.repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    public AdminService(
            AdminRepository adminRepository,
            AdminMapper adminMapper
    ) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    public List<AdminDTO> getAll() {
        return adminRepository.findAll()
                .stream()
                .map(adminMapper::toDto)
                .toList();
    }

    public AdminDTO create(AdminDTO dto) {
        Admin entity = adminMapper.toEntity(dto);
        return adminMapper.toDto(adminRepository.save(entity));
    }

    public boolean delete(Long id) {
        if(!adminRepository.existsById(id)) {
            return false;
        }
        adminRepository.deleteById(id);
        return true;
    }

    public Optional<AdminDTO> byId(Long id) {
        return adminRepository.findById(id)
                .map(adminMapper::toDto);
    }
}
