package carehalcare.carehalcare.service.user;

import carehalcare.carehalcare.domain.user.PatientInfo;
import carehalcare.carehalcare.domain.user.PatientInfoRepository;
import carehalcare.carehalcare.domain.user.User;
import carehalcare.carehalcare.domain.user.UserRepository;
import carehalcare.carehalcare.dto.user.PatientInfoResponseDto;
import carehalcare.carehalcare.dto.user.PatientInfoSaveRequestDto;
import carehalcare.carehalcare.dto.user.PatientInfoUpdateRequestDto;
import carehalcare.carehalcare.dto.user.PatientSetRequestDto;
import carehalcare.carehalcare.service.FCMService;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PatientService {
    private final UserRepository userRepository;
    private final PatientInfoRepository patientInfoRepository;
    private final FCMService fcmService;

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

    /* 환자 정보 저장 */
    @Transactional
    public Long savePatientInfo(PatientInfoSaveRequestDto requestDto){
        return patientInfoRepository.save(requestDto.toEntity()).getId();
    }

    /* 환자 정보 조회 */
    @Transactional(readOnly = true)
    public PatientInfoResponseDto getPatientInfo(String puserId){
        PatientInfo entity = patientInfoRepository.findByUserId(puserId)
                .orElseThrow(IllegalArgumentException::new);
        return new PatientInfoResponseDto(entity);
    }

    /* 환자 정보 수정 */
    @Transactional
    public Long updatePatientInfo(PatientInfoUpdateRequestDto requestDto){
        PatientInfo patientInfo = patientInfoRepository.findByUserId(requestDto.getUserId())
                .orElseThrow(IllegalArgumentException::new);
        try{
            sendPushMsg(patientInfo);
        }catch (FirebaseMessagingException e){
            log.info(e.getMessage());
        }finally {
            return patientInfo.updatePatientInfo(requestDto).getId();
        }
    }

    /* 환자 정보 수정 푸시메시지(알림) */
    @Transactional
    public void sendPushMsg(PatientInfo patientInfo) throws FirebaseMessagingException {
        User puser = userRepository.findByUserId(patientInfo.getUserId())
                .orElseThrow(IllegalArgumentException::new);

        User cuser = userRepository.findByUserId(puser.getCuserId())
                .orElseThrow(IllegalArgumentException::new);

        fcmService.sendMessage(cuser.getFcmToken(), patientInfo.getPname()+" 환자의 정보가 수정되었습니다.");
        System.out.println(patientInfo.getPname()+" 환자의 정보가 수정되었습니다.");
    }

}
