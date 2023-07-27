package carehalcare.carehalcare.service.board;

import carehalcare.carehalcare.domain.board.surroundingcleanliness.SurroundingCleanliness;
import carehalcare.carehalcare.domain.board.surroundingcleanliness.SurroundingCleanlinessRepository;
import carehalcare.carehalcare.dto.board.surroundingcleanliness.SurroundingCleanlinessHistResponseDto;
import carehalcare.carehalcare.dto.board.surroundingcleanliness.SurroundingCleanlinessResponseDto;
import carehalcare.carehalcare.dto.board.surroundingcleanliness.SurroundingCleanlinessSaveRequestDto;
import carehalcare.carehalcare.dto.board.surroundingcleanliness.SurroundingCleanlinessUpdateRequestDto;
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
public class SurroundingCleanlinessService {

    private final SurroundingCleanlinessRepository surroundingCleanlinessRepository;
    private final AuditReader auditReader;

    /* 주변 환경 청결 기록 저장 */
    @Transactional
    public Long saveSurroundingCleanliness(SurroundingCleanlinessSaveRequestDto requestDto){
        return surroundingCleanlinessRepository.save(requestDto.toEntity()).getId();
    }

    /* 주변 환경 청결 기록 리스트 조회 */
    @Transactional(readOnly = true)
    public List<SurroundingCleanlinessResponseDto> getSurroundingCleanlinessList(
            String userId, String puserId){
        return this.surroundingCleanlinessRepository
                .findByUserIdAndPuserIdOrderByCreatedDateTimeDesc(userId, puserId);
    }

    /* 주변 환경 청결 기록 상세 조회 */
    @Transactional(readOnly = true)
    public SurroundingCleanlinessResponseDto findById(Long id){
        SurroundingCleanliness surroundingCleanliness = surroundingCleanlinessRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new SurroundingCleanlinessResponseDto(surroundingCleanliness);
    }

    /* 주변 환경 청결 기록 삭제 */
    @Transactional
    public void deleteSurroundingCleanliness(Long id){
        SurroundingCleanliness surroundingCleanliness = surroundingCleanlinessRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        surroundingCleanlinessRepository.delete(surroundingCleanliness);
    }

    /* 주변 환경 청결 기록 수정 */
    @Transactional
    public Long updateSCleanliness(SurroundingCleanlinessUpdateRequestDto requestDto){
        SurroundingCleanliness surroundingCleanliness = surroundingCleanlinessRepository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+requestDto.getId()));

        return surroundingCleanliness.updateSurroundingCleanliness(
                requestDto.getCleanliness(), requestDto.getContent()).getId();
    }

    /* 주변 환경 청결 기록 변경 이력 리스트 조회 */
    @Transactional(readOnly = true)
    public List<SurroundingCleanlinessHistResponseDto> getSCleanlinessHists(Long id){
        List<Object[]> hists = auditReader.createQuery()
                .forRevisionsOfEntity(SurroundingCleanliness.class, false, false)
                .add(AuditEntity.property("id").eq(id))
                .add(AuditEntity.revisionType().between(RevisionType.ADD, RevisionType.MOD))
                //.add(AuditEntity.revisionType().eq(RevisionType.MOD))
                .addOrder(AuditEntity.property("modifiedDateTime").desc())
                .getResultList();

        return hists.stream().map(history -> new SurroundingCleanlinessHistResponseDto(
                        (SurroundingCleanliness) history[0], (DefaultRevisionEntity) history[1]))
                .collect(Collectors.toList());
    }

    /* 주변 환경 청결 기록 변경 이력 상세 조회 */
    @Transactional(readOnly = true)
    public SurroundingCleanlinessHistResponseDto getSCleanlinessHist(Long id, Integer revNum){
        Object sCleanlinessHist = auditReader.createQuery()
                .forRevisionsOfEntity(SurroundingCleanliness.class, true, false)
                .add(AuditEntity.property("id").eq(id))
                .add(AuditEntity.revisionNumber().eq(revNum))
                .getSingleResult();

        return new SurroundingCleanlinessHistResponseDto((SurroundingCleanliness) sCleanlinessHist);
    }
}
