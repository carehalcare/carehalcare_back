package carehalcare.carehalcare.domain.notice;

import carehalcare.carehalcare.domain.BaseTimeEntity;
import carehalcare.carehalcare.dto.notice.NoticeUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Notice extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Builder
    public Notice(String userId, String pUserId, String content){
        this.userId = userId;
        this.content = content;
    }

    public Notice updateNotice(NoticeUpdateRequestDto requestDto){
        this.content=requestDto.getContent();
        return this;
    }
}
