package carehalcare.carehalcare.domain.board.walk;

import carehalcare.carehalcare.dto.board.walk.WalkResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalkRepository extends JpaRepository<Walk, Long> {
    List<WalkResponseDto> findByUserIdAndPuserIdOrderByCreatedDateDesc(String userId, String puserId);
}
