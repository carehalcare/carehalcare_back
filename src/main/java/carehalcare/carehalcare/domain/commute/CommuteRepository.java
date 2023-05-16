package carehalcare.carehalcare.domain.commute;

import carehalcare.carehalcare.dto.commute.CommuteResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommuteRepository extends JpaRepository<Commute, Long> {
    List<CommuteResponseDto> findByDateAndUserIdAndPuserId(String date, String userId, String puserId);
}
