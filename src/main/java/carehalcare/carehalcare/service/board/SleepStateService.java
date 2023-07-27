package carehalcare.carehalcare.service.board;

import carehalcare.carehalcare.domain.board.sleepstate.SleepState;
import carehalcare.carehalcare.domain.board.sleepstate.SleepStateRepository;
import carehalcare.carehalcare.dto.board.sleepstate.SleepStateHistResponseDto;
import carehalcare.carehalcare.dto.board.sleepstate.SleepStateResponseDto;
import carehalcare.carehalcare.dto.board.sleepstate.SleepStateSaveRequestDto;
import carehalcare.carehalcare.dto.board.sleepstate.SleepStateUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SleepStateService {
    private final SleepStateRepository sleepStateRepository;
    private final AuditReader auditReader;

    /* 수면 상태 기록 저장 */
    @Transactional
    public Long saveSleepState(SleepStateSaveRequestDto requestDto){
        return sleepStateRepository.save(requestDto.toEntity()).getId();
    }

    /* 수면 상태 기록 리스트 조회 */
    @Transactional(readOnly = true)
    public List<SleepStateResponseDto> getSleepStateList(
            String userId, String puserId){
        return this.sleepStateRepository
                .findByUserIdAndPuserIdOrderByCreatedDateTimeDesc(userId, puserId);
    }

    /* 수면 상태 기록 상세 조회 */
    @Transactional(readOnly = true)
    public SleepStateResponseDto findById(Long id){
        SleepState sleepState = sleepStateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new SleepStateResponseDto(sleepState);
    }

    /* 수면 상태 기록 삭제 */
    @Transactional
    public void deleteSleepState(Long id){
        SleepState sleepState = sleepStateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        sleepStateRepository.delete(sleepState);
    }

    /* 수면 상태 기록 수정 */
    @Transactional
    public Long updateSleepState(SleepStateUpdateRequestDto requestDto){
        SleepState sleepState = sleepStateRepository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+requestDto.getId()));

        return sleepState.updateSleepState(requestDto.getState(),
                requestDto.getContent()).getId();
    }

    /* 수면 상태 기록 변경 이력 리스트 조회 */
    @Transactional(readOnly = true)
    public List<SleepStateHistResponseDto> getSleepStateHists(Long id){
        List<Object[]> hists = auditReader.createQuery()
                .forRevisionsOfEntity(SleepState.class, false, false)
                .add(AuditEntity.property("id").eq(id))
                .add(AuditEntity.revisionType().between(RevisionType.ADD, RevisionType.MOD))
                //.add(AuditEntity.revisionType().eq(RevisionType.MOD))
                .addOrder(AuditEntity.property("modifiedDateTime").desc())
                .getResultList();

        return hists.stream().map(history -> new SleepStateHistResponseDto(
                        (SleepState) history[0], (DefaultRevisionEntity) history[1]))
                .collect(Collectors.toList());
    }

    /* 수면 상태 기록 변경 이력 상세 조회 */
    @Transactional(readOnly = true)
    public SleepStateHistResponseDto getSleepStateHist(Long id, Integer revNum){
        Object sleepStateHist = auditReader.createQuery()
                .forRevisionsOfEntity(SleepState.class, true, false)
                .add(AuditEntity.property("id").eq(id))
                .add(AuditEntity.revisionNumber().eq(revNum))
                .getSingleResult();

        return new SleepStateHistResponseDto((SleepState) sleepStateHist);
    }
}
