package carehalcare.carehalcare.domain.board;

import carehalcare.carehalcare.dto.board.administraion.AdministrationListResponseRequestDto;
import carehalcare.carehalcare.dto.board.administraion.AdministrationResponseRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdministrationRepository extends JpaRepository<Administration, Long> {
    List<AdministrationListResponseRequestDto> findByUserIdAndPuserId(String userId, String puserId);

}
