package lt.ca.javau12.employeeshiftplanner.services;

import lt.ca.javau12.employeeshiftplanner.dto.AdminDTO;
import lt.ca.javau12.employeeshiftplanner.dto.EmployeeDTO;
import lt.ca.javau12.employeeshiftplanner.entities.Admin;
import lt.ca.javau12.employeeshiftplanner.entities.Employee;
import lt.ca.javau12.employeeshiftplanner.entities.Role;
import lt.ca.javau12.employeeshiftplanner.mappers.AdminMapper;
import lt.ca.javau12.employeeshiftplanner.repositories.AdminRepository;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    public AdminService(
            AdminRepository adminRepository,
            AdminMapper adminMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<AdminDTO> getAll() {
        return adminRepository.findAll()
                .stream()
                .map(adminMapper::toDto)
                .toList();
    }

    public AdminDTO createAdmin(AdminDTO dto) {
        Admin entity = adminMapper.toEntity(dto);
        if (entity.getRole() == null) {
            entity.setRole(Role.ADMIN);
        }
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
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
    public AdminDTO getAdminProfileByUsername(String username) {
        Admin admin = adminRepository.findByEmail(username);
        if (admin == null) {
            throw new UsernameNotFoundException("Darbuotojas su tokiu paštu nerastas: " + username);
        }
        return adminMapper.toDto(admin);
    }

    public AdminDTO updateAdminProfile(String username, AdminDTO updatedProfile) {
    	Admin admin = adminRepository.findByEmail(username);
        if (admin == null) {
            throw new UsernameNotFoundException("Darbuotojas su tokiu paštu nerastas: " + username);
        }

        admin.setFirstName(updatedProfile.getFirstName());
        admin.setLastName(updatedProfile.getLastName());
        admin.setPhone(updatedProfile.getPhone());

        return adminMapper.toDto(adminRepository.save(admin));
    }
}
