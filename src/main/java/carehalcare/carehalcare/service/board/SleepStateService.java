package carehalcare.carehalcare.service.board;

import carehalcare.carehalcare.domain.board.sleepstate.SleepState;
import carehalcare.carehalcare.domain.board.sleepstate.SleepStateRepository;
import carehalcare.carehalcare.dto.board.sleepstate.SleepStateResponseDto;
import carehalcare.carehalcare.dto.board.sleepstate.SleepStateSaveRequsetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SleepStateService {
    private final SleepStateRepository sleepStateRepository;

    /* 수면 상태 기록 저장 */
    @Transactional
    public Long saveSleepState(SleepStateSaveRequsetDto requestDto){
        return sleepStateRepository.save(requestDto.toEntity()).getId();
    }

    /* 수면 상태 기록 리스트 조회 */
    @Transactional(readOnly = true)
    public List<SleepStateResponseDto> getSleepStateList(
            String userId, String puserId){
        return this.sleepStateRepository
                .findByUserIdAndPuserIdOrderByCreatedDateDesc(userId, puserId);
    }

    /* 수면 상태 기록 상세 조회 */
    @Transactional(readOnly = true)
    public SleepStateResponseDto findById(Long id){
        SleepState sleepState = sleepStateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new SleepStateResponseDto(sleepState);
    }

    /* 수면 상태 기록 삭제 */
    public void deleteSleepState(Long id){
        SleepState sleepState = sleepStateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        sleepStateRepository.delete(sleepState);
    }
}
