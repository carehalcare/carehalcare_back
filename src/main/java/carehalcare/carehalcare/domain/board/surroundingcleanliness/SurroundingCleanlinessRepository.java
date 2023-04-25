package carehalcare.carehalcare.domain.board.surroundingcleanliness;

import carehalcare.carehalcare.dto.board.surroundingcleanliness.SurroundingCleanlinessResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurroundingCleanlinessRepository extends JpaRepository<SurroundingCleanliness, Long> {
    List<SurroundingCleanlinessResponseDto> findByUserIdAndPuserIdOrderByCreatedDateDesc(String userId, String puserId);
}
