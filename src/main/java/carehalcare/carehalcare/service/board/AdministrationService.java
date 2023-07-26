package carehalcare.carehalcare.service.board;

import carehalcare.carehalcare.domain.board.administration.Administration;
import carehalcare.carehalcare.domain.board.administration.AdministrationRepository;
import carehalcare.carehalcare.dto.board.administraion.AdministrationHistResponseDto;
import carehalcare.carehalcare.dto.board.administraion.AdministrationResponseDto;
import carehalcare.carehalcare.dto.board.administraion.AdministrationSaveRequestDto;
import carehalcare.carehalcare.dto.board.administraion.AdministrationUpdateRequestDto;
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
public class AdministrationService {
    private final AdministrationRepository administrationRepository;
    private final AuditReader auditReader;

    /* 투약 기록 저장 */
    @Transactional
    public Long save(AdministrationSaveRequestDto requestDto){
        return administrationRepository.save(requestDto.toEntity())
                .getId();
    }

    /* 투약 기록 리스트 조회 */
    @Transactional(readOnly = true)
    public List<AdministrationResponseDto> getList(
            String userId, String puserId){
        return this.administrationRepository.findByUserIdAndPuserIdOrderByCreatedDateTimeDesc(userId,puserId);
    }

    /* 투약 기록 상세 조회 */
    @Transactional(readOnly = true)
    public AdministrationResponseDto findById(Long id){
        Administration entity = administrationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new AdministrationResponseDto(entity);
    }

    /* 투약 기록 삭제 */
    @Transactional
    public void deleteAdministration(Long id){
        Administration administration = administrationRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        administrationRepository.delete(administration);
    }

    /* 투약 기록 수정 */
    @Transactional
    public Long updateAdministration(AdministrationUpdateRequestDto requestDto){
        Administration administration = administrationRepository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+requestDto.getId()));
        return administration.updateAdministration(requestDto.getTime(),
                requestDto.getMealStatus(), requestDto.getMedicine()).getId();
    }

    /* 투약 기록 변경 이력 리스트 조회 */
    @Transactional(readOnly = true)
    public List<AdministrationHistResponseDto> getAdministrationHists(Long id){
        List<Object[]> hists = auditReader.createQuery()
                .forRevisionsOfEntity(Administration.class, false, false)
                .add(AuditEntity.property("id").eq(id))
                .add(AuditEntity.revisionType().eq(RevisionType.MOD))
                .addOrder(AuditEntity.property("modifiedDateTime").desc())
                .getResultList();
        return hists.stream().map(history -> new AdministrationHistResponseDto(
                        (Administration) history[0], (DefaultRevisionEntity) history[1]))
                .collect(Collectors.toList());
    }

    /* 투약 기록 변경 이력 상세 조회 */
    @Transactional(readOnly = true)
    public AdministrationHistResponseDto getAdministrationHist(Long id, Integer revNum){
        Object administrationHist = auditReader.createQuery()
                .forRevisionsOfEntity(Administration.class, true, false)
                .add(AuditEntity.property("id").eq(id))
                .add(AuditEntity.revisionNumber().eq(revNum))
                .getSingleResult();

        return new AdministrationHistResponseDto((Administration) administrationHist);
    }
}
