package carehalcare.carehalcare.service.board;

import carehalcare.carehalcare.domain.board.patientcleanliness.PatientCleanliness;
import carehalcare.carehalcare.domain.board.patientcleanliness.PatientCleanlinessRepository;
import carehalcare.carehalcare.dto.board.patientcleanliness.PatientCleanlinessResponseDto;
import carehalcare.carehalcare.dto.board.patientcleanliness.PatientCleanlinessSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PatientCleanlinessService {

    private final PatientCleanlinessRepository patientCleanlinessRepository;

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
                .findByUserIdAndPuserIdOrderByCreatedDateDesc(userId, puserId);
    }

    /* 환자 청결 기록 상세 조회 */
    @Transactional(readOnly = true)
    public PatientCleanlinessResponseDto findById(Long id){
        PatientCleanliness patientCleanliness = patientCleanlinessRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PatientCleanlinessResponseDto(patientCleanliness);
    }

    /* 환자 청결 기록 삭제 */
    public void deletePatientCleanliness(Long id){
        PatientCleanliness patientCleanliness = patientCleanlinessRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        patientCleanlinessRepository.delete(patientCleanliness);
    }
}
