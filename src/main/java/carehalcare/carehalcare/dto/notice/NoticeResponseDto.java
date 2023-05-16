package carehalcare.carehalcare.dto.notice;

import carehalcare.carehalcare.domain.notice.Notice;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class NoticeResponseDto {
    private Long id;
    private String userId;
    private String content;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;

    public NoticeResponseDto(Notice entity){
        this.id=entity.getId();
        this.userId=entity.getUserId();
        this.content=entity.getContent();
        this.createdDateTime=entity.getCreatedDateTime();
        this.modifiedDateTime=entity.getModifiedDateTime();
    }
}
