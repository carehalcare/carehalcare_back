package carehalcare.carehalcare.dto.notice;

import carehalcare.carehalcare.domain.board.administration.Administration;
import carehalcare.carehalcare.domain.notice.Notice;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class NoticeListResponseDto {
    private Long id;
    private String userId;
    private String content;
    private LocalDateTime createdDate;

    public NoticeListResponseDto(Notice entity){
        this.id=entity.getId();
        this.userId=entity.getUserId();
        this.content=entity.getContent();
        this.createdDate=entity.getCreatedDate();
    }
}
