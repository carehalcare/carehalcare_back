package carehalcare.carehalcare.domain.board.administration;

import carehalcare.carehalcare.dto.board.administraion.AdministrationResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdministrationRepository extends JpaRepository<Administration, Long> {
    List<AdministrationResponseDto> findByUserIdAndPuserIdOrderByCreatedDateDesc(String userId, String puserId);

}
