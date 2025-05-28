package mappers;


import dto.AdminDTO;
import entities.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public AdminDTO toDto(Admin entity) {
        return new AdminDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getPosition()
        );
    }

    public Admin toEntity(AdminDTO dto) {
        return new Admin(
                dto.getName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getPosition()
        );
    }


}
