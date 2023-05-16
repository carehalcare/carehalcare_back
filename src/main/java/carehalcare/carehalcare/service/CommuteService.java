package carehalcare.carehalcare.service;

import carehalcare.carehalcare.domain.commute.Commute;
import carehalcare.carehalcare.domain.commute.CommuteRepository;
import carehalcare.carehalcare.dto.commute.CommuteResponseDto;
import carehalcare.carehalcare.dto.commute.CommuteSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommuteService {
    private final CommuteRepository commuteRepository;

    /* 출퇴근 기록 저장 */
    @Transactional
    public List<CommuteResponseDto> saveCommute(CommuteSaveRequestDto requestDto){
        Commute commute = commuteRepository.save(requestDto.toEntity());
        return commuteRepository.findByDateAndUserIdAndPuserId(commute.getDate(),
                commute.getUserId(), commute.getPuserId());
    }

    /* 출퇴근 기록 상세 조회 */
    @Transactional(readOnly = true)
    public List<CommuteResponseDto> getCommute(String date, String userId, String puserId){
        return commuteRepository.findByDateAndUserIdAndPuserId(date, userId, puserId);
    }
}
