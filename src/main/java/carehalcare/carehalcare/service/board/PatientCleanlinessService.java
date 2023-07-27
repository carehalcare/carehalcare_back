package carehalcare.carehalcare.service.board;

import carehalcare.carehalcare.domain.board.administration.Administration;
import carehalcare.carehalcare.domain.board.patientcleanliness.PatientCleanliness;
import carehalcare.carehalcare.domain.board.patientcleanliness.PatientCleanlinessRepository;
import carehalcare.carehalcare.dto.board.administraion.AdministrationHistResponseDto;
import carehalcare.carehalcare.dto.board.patientcleanliness.PatientCleanlinessHistResponseDto;
import carehalcare.carehalcare.dto.board.patientcleanliness.PatientCleanlinessResponseDto;
import carehalcare.carehalcare.dto.board.patientcleanliness.PatientCleanlinessSaveRequestDto;
import carehalcare.carehalcare.dto.board.patientcleanliness.PatientCleanlinessUpdateRequestDto;
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
public class PatientCleanlinessService {

    private final PatientCleanlinessRepository patientCleanlinessRepository;
    private final AuditReader auditReader;

    /* 환자 청결 기록 저장 */
   @Transactional
    public Long savePatientCleanliness(PatientCleanlinessSaveRequestDto requestDto){
       return patientCleanlinessRepository.save(requestDto.toEntity()).getId();
   }

    /* 환자 청결 기록 리스트 조회 */
    @Transactional(readOnly = true)
    public List<PatientCleanlinessResponseDto> getPatientCleanlinessList(
            String userId, String puserId){
        return this.patientCleanlinessRepository
                .findByUserIdAndPuserIdOrderByCreatedDateTimeDesc(userId, puserId);
    }

    /* 환자 청결 기록 상세 조회 */
    @Transactional(readOnly = true)
    public PatientCleanlinessResponseDto findById(Long id){
        PatientCleanliness patientCleanliness = patientCleanlinessRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PatientCleanlinessResponseDto(patientCleanliness);
    }

    /* 환자 청결 기록 삭제 */
    @Transactional
    public void deletePatientCleanliness(Long id){
        PatientCleanliness patientCleanliness = patientCleanlinessRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        patientCleanlinessRepository.delete(patientCleanliness);
    }

    /* 환자 청결 기록 수정 */
    @Transactional
    public Long updatePatientCleanliness(PatientCleanlinessUpdateRequestDto requestDto){
        PatientCleanliness patientCleanliness = patientCleanlinessRepository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+requestDto.getId()));
        return patientCleanliness.updatePatientCleanliness(requestDto.getCleanliness(),
                requestDto.getPart(), requestDto.getContent()).getId();
    }

    /* 환자 청결 기록 변경 이력 리스트 조회 */
    @Transactional(readOnly = true)
    public List<PatientCleanlinessHistResponseDto> getPatientCleanlinessHists(Long id){
        List<Object[]> hists = auditReader.createQuery()
                .forRevisionsOfEntity(PatientCleanliness.class, false, false)
                .add(AuditEntity.property("id").eq(id))
                .add(AuditEntity.revisionType().between(RevisionType.ADD, RevisionType.MOD))
                //.add(AuditEntity.revisionType().eq(RevisionType.MOD))
                .addOrder(AuditEntity.property("modifiedDateTime").desc())
                .getResultList();

        return hists.stream().map(history -> new PatientCleanlinessHistResponseDto(
                        (PatientCleanliness) history[0], (DefaultRevisionEntity) history[1]))
                .collect(Collectors.toList());
    }

    /* 환자 청결 기록 변경 이력 상세 조회 */
    @Transactional(readOnly = true)
    public PatientCleanlinessHistResponseDto getPatientCleanlinessHist(Long id, Integer revNum){
        Object patientCleanlinessHist = auditReader.createQuery()
                .forRevisionsOfEntity(PatientCleanliness.class, true, false)
                .add(AuditEntity.property("id").eq(id))
                .add(AuditEntity.revisionNumber().eq(revNum))
                .getSingleResult();

        return new PatientCleanlinessHistResponseDto((PatientCleanliness) patientCleanlinessHist);
    }

}
