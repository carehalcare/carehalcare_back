package carehalcare.carehalcare.domain.board.sleepstate;

import carehalcare.carehalcare.domain.board.sleepstate.SleepState;
import carehalcare.carehalcare.dto.board.activity.ActivityResponseDto;
import carehalcare.carehalcare.dto.board.sleepstate.SleepStateResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SleepStateRepository extends JpaRepository<SleepState, Long> {
    List<SleepStateResponseDto> findByUserIdAndPuserIdOrderByCreatedDateTimeDesc(String userId, String puserId);
}
