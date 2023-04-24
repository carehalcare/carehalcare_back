package carehalcare.carehalcare.dto.board.sleepstate;


import carehalcare.carehalcare.domain.board.sleepstate.SleepState;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SleepStateResponseDto {
    private Long id;
    private String userId;
    private String puserId;
    private String state;
    private String content;
    private LocalDateTime createdDate;

    public SleepStateResponseDto(SleepState entity){
        this.id=entity.getId();
        this.userId=entity.getUserId();
        this.puserId=entity.getPuserId();
        this.state=entity.getState();
        this.content=entity.getContent();
        this.createdDate=entity.getCreatedDate();
    }
}
