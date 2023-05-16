package carehalcare.carehalcare.domain.board.bowelmovement;

import carehalcare.carehalcare.domain.board.bowelmovement.BowelMovement;
import carehalcare.carehalcare.dto.board.activity.ActivityResponseDto;
import carehalcare.carehalcare.dto.board.bowelmovement.BowelMovementResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BowelMovementRepository extends JpaRepository<BowelMovement, Long> {
    List<BowelMovementResponseDto> findByUserIdAndPuserIdOrderByCreatedDateTimeDesc(String userId, String puserId);
}
