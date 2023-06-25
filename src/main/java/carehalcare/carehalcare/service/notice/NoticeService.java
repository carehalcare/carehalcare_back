package carehalcare.carehalcare.service.notice;

import carehalcare.carehalcare.domain.notice.Notice;
import carehalcare.carehalcare.domain.notice.NoticeRepository;
import carehalcare.carehalcare.domain.user.PatientInfo;
import carehalcare.carehalcare.domain.user.PatientInfoRepository;
import carehalcare.carehalcare.domain.user.User;
import carehalcare.carehalcare.domain.user.UserRepository;
import carehalcare.carehalcare.dto.notice.NoticeResponseDto;
import carehalcare.carehalcare.dto.notice.NoticeSaveRequestDto;
import carehalcare.carehalcare.dto.notice.NoticeUpdateRequestDto;
import carehalcare.carehalcare.service.FCMService;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final PatientInfoRepository patientInfoRepository;
    private final UserRepository userRepository;
    private final FCMService fcmService;

    /* 공지사항 등록 */
    @Transactional
    public Long saveNotice(NoticeSaveRequestDto requestDto){
        try{
            sendPushMsg(requestDto.getUserId());
        }catch (FirebaseMessagingException e){
            log.info(e.getMessage());
        }finally {
            return noticeRepository.save(requestDto.toEntity()).getId();
        }
    }

    /* 공지사항 리스트 조회 */
    @Transactional(readOnly = true)
    public List<NoticeResponseDto> getList(String puserId){
        return noticeRepository.findByUserIdOrderByCreatedDateTimeDesc(puserId);
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

    /* 공지사항 등록 시 간병인에게 푸시메시지(알림) 전송 */
    @Transactional
    public void sendPushMsg(String userId) throws FirebaseMessagingException {
        PatientInfo patientInfo = patientInfoRepository.findByUserId(userId)
                .orElseThrow(IllegalArgumentException::new);

        User cuser = userRepository.findByPuserId(userId)
                .orElseThrow(IllegalArgumentException::new);

        System.out.println("간병인아이디 = "+cuser.getUserId());
        fcmService.sendMessage(cuser.getFcmToken(), "["+patientInfo.getPname()+"] 환자의 공지사항이 새로 등록되었습니다.");
        System.out.println("["+patientInfo.getPname()+"] 환자의 공지사항이 새로 등록되었습니다.");
    }
}
