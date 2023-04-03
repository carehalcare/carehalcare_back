package carehalcare.carehalcare.domain.board.administration;

import carehalcare.carehalcare.dto.board.administraion.AdministrationListResponseRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdministrationRepository extends JpaRepository<Administration, Long> {
    List<AdministrationListResponseRequestDto> findByUserIdAndPuserIdOrderByCreatedDateDesc(String userId, String puserId);

}
