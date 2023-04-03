package carehalcare.carehalcare.service.notice;

import carehalcare.carehalcare.domain.notice.NoticeRepository;
import carehalcare.carehalcare.dto.notice.NoticeListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    /* 공지사항 리스트 조회 */
    @Transactional(readOnly = true)
    public List<NoticeListResponseDto> getList(String puserId){
        return this.noticeRepository.findByUserIdOrderByCreatedDateDesc(puserId);
    }
}
