package carehalcare.carehalcare.service.board;

import carehalcare.carehalcare.domain.board.activity.Activity;
import carehalcare.carehalcare.domain.board.bowelmovement.BowelMovement;
import carehalcare.carehalcare.domain.board.bowelmovement.BowelMovementRepository;
import carehalcare.carehalcare.dto.board.activity.ActivityHistResponseDto;
import carehalcare.carehalcare.dto.board.bowelmovement.BowelMovementHistResponseDto;
import carehalcare.carehalcare.dto.board.bowelmovement.BowelMovementResponseDto;
import carehalcare.carehalcare.dto.board.bowelmovement.BowelMovementSaveRequestDto;
import carehalcare.carehalcare.dto.board.bowelmovement.BowelMovementUpdateRequestDto;
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
public class BowelMovementService {
    private final BowelMovementRepository bowelMovementRepository;
    private final AuditReader auditReader;

    /* 배변 기록 저장 */
    @Transactional
    public Long saveBowelMovement(BowelMovementSaveRequestDto requestDto){
        return bowelMovementRepository.save(requestDto.toEntity()).getId();
    }

    /* 배변 기록 리스트 조회 */
    @Transactional(readOnly = true)
    public List<BowelMovementResponseDto> getBowelMovementList(
            String userId, String puserId){
        return this.bowelMovementRepository
                .findByUserIdAndPuserIdOrderByCreatedDateTimeDesc(userId, puserId);
    }

    /* 배변 기록 상세 조회 */
    @Transactional(readOnly = true)
    public BowelMovementResponseDto findById(Long id){
        BowelMovement bowelMovement = bowelMovementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new BowelMovementResponseDto(bowelMovement);
    }

    /* 배변 기록 삭제 */
    @Transactional
    public void deleteBowelMovement(Long id){
        BowelMovement bowelMovement = bowelMovementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        bowelMovementRepository.delete(bowelMovement);
    }

    /* 배변 기록 수정 */
    @Transactional
    public Long updateBowelMovement(BowelMovementUpdateRequestDto requestDto){
        BowelMovement bowelMovement = bowelMovementRepository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+requestDto.getId()));
        return bowelMovement.updateBowelMovement(requestDto.getCount(),
                requestDto.getContent()).getId();
    }

    /* 배변 기록 변경 이력 리스트 조회 */
    @Transactional(readOnly = true)
    public List<BowelMovementHistResponseDto> getBowelMovementHists(Long id){
        List<Object[]> hists = auditReader.createQuery()
                .forRevisionsOfEntity(BowelMovement.class, false, false)
                .add(AuditEntity.property("id").eq(id))
                .add(AuditEntity.revisionType().between(RevisionType.ADD, RevisionType.MOD))
                //.add(AuditEntity.revisionType().eq(RevisionType.MOD))
                .addOrder(AuditEntity.property("modifiedDateTime").desc())
                .getResultList();

        return hists.stream().map(history -> new BowelMovementHistResponseDto(
                        (BowelMovement) history[0], (DefaultRevisionEntity) history[1]))
                .collect(Collectors.toList());
    }

    /* 배변 기록 변경 이력 상세 조회 */
    @Transactional(readOnly = true)
    public BowelMovementHistResponseDto getBowelMovementHist(Long id, Integer revNum){
        Object bowelMovementHist = auditReader.createQuery()
                .forRevisionsOfEntity(BowelMovement.class, true, false)
                .add(AuditEntity.property("id").eq(id))
                .add(AuditEntity.revisionNumber().eq(revNum))
                .getSingleResult();

        return new BowelMovementHistResponseDto((BowelMovement) bowelMovementHist);
    }
}
