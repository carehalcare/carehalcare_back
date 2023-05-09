package carehalcare.carehalcare.service.user;

import carehalcare.carehalcare.domain.user.User;
import carehalcare.carehalcare.domain.user.UserRepository;
import carehalcare.carehalcare.dto.user.CaregiverSetRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CaregiverService {
    private final UserRepository userRepository;

    /* 간병인 아이디 조회 */
    @Transactional(readOnly = true)
    public User findByUserId(String userId){
        return userRepository.findByUserId(userId)
                .orElseThrow(IllegalArgumentException::new);
    }

    /* 간병인 등록 */
    @Transactional
    public Long setCaregiver(CaregiverSetRequestDto requestDto){
        User patient = userRepository.findByUserId(requestDto.getUserId())
                .orElseThrow(IllegalArgumentException::new);
        patient.setCuserId(requestDto.getCuserId());
        return patient.getId();
    }
}
