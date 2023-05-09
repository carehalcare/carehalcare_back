package carehalcare.carehalcare.service.user;

import carehalcare.carehalcare.domain.user.PatientInfo;
import carehalcare.carehalcare.domain.user.PatientInfoRepository;
import carehalcare.carehalcare.domain.user.User;
import carehalcare.carehalcare.domain.user.UserRepository;
import carehalcare.carehalcare.dto.user.PatientInfoResponseDto;
import carehalcare.carehalcare.dto.user.PatientSetRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PatientService {
    private final UserRepository userRepository;
    private final PatientInfoRepository patientInfoRepository;

    /* 환자(보호자) 아이디 조회 */
    @Transactional(readOnly = true)
    public User findByUserId(String userId){
        return userRepository.findByUserId(userId)
                .orElseThrow(IllegalArgumentException::new);
    }


    /* 환자 등록 */
    @Transactional
    public Long setPatient(PatientSetRequestDto requestDto){
        User caregiver = userRepository.findByUserId(requestDto.getUserId())
                .orElseThrow(IllegalArgumentException::new);
        caregiver.setPuserId(requestDto.getPuserId());
        return caregiver.getId();
    }

    /* 환자 정보 조회 */
    @Transactional(readOnly = true)
    public PatientInfoResponseDto getPatientInfo(String puserId){
        PatientInfo entity = patientInfoRepository.findByUserId(puserId)
                .orElseThrow(IllegalArgumentException::new);
        return new PatientInfoResponseDto(entity);
    }
}
