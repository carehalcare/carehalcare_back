package carehalcare.carehalcare.service.board;

import carehalcare.carehalcare.domain.board.activity.Activity;
import carehalcare.carehalcare.domain.board.activity.ActivityRepository;
import carehalcare.carehalcare.dto.board.activity.ActivityResponseDto;
import carehalcare.carehalcare.dto.board.activity.ActivitySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ActivityService {
    private final ActivityRepository activityRepository;

    /* 활동 기록 저장 */
    @Transactional
    public Long saveActivity(ActivitySaveRequestDto requestDto){
        return activityRepository.save(requestDto.toEntity()).getId();
    }

    /* 활동 기록 리스트 조회 */
    @Transactional(readOnly = true)
    public List<ActivityResponseDto> getActivityList(
            String userId, String puserId){
        return this.activityRepository
                .findByUserIdAndPuserIdOrderByCreatedDateTimeDesc(userId, puserId);
    }

    /* 활동 기록 상세 조회 */
    @Transactional(readOnly = true)
    public ActivityResponseDto findById(Long id){
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new ActivityResponseDto(activity);
    }

    /* 활동 기록 삭제 */
    @Transactional
    public void deleteActivity(Long id){
        Activity activity = activityRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        activityRepository.delete(activity);
    }
}
