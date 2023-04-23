package carehalcare.carehalcare.domain.board.activity;

import carehalcare.carehalcare.domain.board.activity.Activity;
import carehalcare.carehalcare.dto.board.activity.ActivityResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<ActivityResponseDto> findByUserIdAndPuserIdOrderByCreatedDateDesc(String userId, String puserId);
}
