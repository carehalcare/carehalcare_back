package carehalcare.carehalcare.service;

import carehalcare.carehalcare.domain.notice.NoticeRepository;
import carehalcare.carehalcare.domain.user.PatientInfoRepository;
import carehalcare.carehalcare.domain.user.User;
import carehalcare.carehalcare.domain.user.UserRepository;
import carehalcare.carehalcare.dto.user.PatientInfoPushMsgDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class PushMsgService {

    private final UserRepository userRepository;
    private final PatientInfoRepository patientInfoRepository;
    private final NoticeRepository noticeRepository;

    @Transactional
    public void getFcmToken(String userId, String fcmToken) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        user.updateFcmToken(fcmToken);
    }

    /*@Transactional
    public List<PatientInfoPushMsgDto> findCuserIdByPuserId(String puserId){
        User cuser = userRepository.findByPuserId(puserId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }*/

}
