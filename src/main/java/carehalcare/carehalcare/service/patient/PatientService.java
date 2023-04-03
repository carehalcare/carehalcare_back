package carehalcare.carehalcare.service.patient;

import carehalcare.carehalcare.domain.user.User;
import carehalcare.carehalcare.domain.user.UserRepository;
import carehalcare.carehalcare.dto.patient.PatientSetRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Service
public class PatientService {
    private final UserRepository userRepository;

    /* 환자(보호자) 아이디 조회 */
    @Transactional
    public User findByUserId(String userId){
        return userRepository.findByUserId(userId);
    }


    /* 환자 등록 */
    @Transactional
    public Long setPatient(PatientSetRequestDto requestDto){
        User caregiver = userRepository.findByUserId(requestDto.getUserId());
        caregiver.setPuserId(requestDto.getPuserId());
        return caregiver.getId();
    }
}
