package carehalcare.carehalcare.service.notice;

import carehalcare.carehalcare.domain.notice.Notice;
import carehalcare.carehalcare.domain.notice.NoticeRepository;
import carehalcare.carehalcare.dto.notice.NoticeResponseDto;
import carehalcare.carehalcare.dto.notice.NoticeSaveRequestDto;
import carehalcare.carehalcare.dto.notice.NoticeUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    /* 공지사항 등록 */
    @Transactional
    public Long saveNotice(NoticeSaveRequestDto requestDto){
        return noticeRepository.save(requestDto.toEntity()).getId();
    }

    /* 공지사항 리스트 조회 */
    @Transactional(readOnly = true)
    public List<NoticeResponseDto> getList(String puserId){
        return noticeRepository.findByUserIdOrderByCreatedDateDesc(puserId);
    }

    /* 공지사항 상세 조회 */
    @Transactional(readOnly = true)
    public NoticeResponseDto findById(Long id){
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new NoticeResponseDto(notice);
    }

    /* 공지사항 삭제 */
    @Transactional
    public void deleteNotice(Long id){
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        noticeRepository.delete(notice);
    }

    /* 공지사항 수정 */
    @Transactional
    public Long updateNotice(NoticeUpdateRequestDto requestDto){
        Notice notice = noticeRepository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+requestDto.getId()));
        return notice.updateNotice(requestDto).getId();
    }
}
