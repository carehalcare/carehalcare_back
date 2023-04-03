package carehalcare.carehalcare.domain.notice;

import carehalcare.carehalcare.dto.notice.NoticeListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<NoticeListResponseDto> findByUserIdOrderByCreatedDateDesc(String userId);
}
