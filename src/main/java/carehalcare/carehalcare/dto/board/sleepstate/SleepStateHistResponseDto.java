package carehalcare.carehalcare.dto.board.sleepstate;

import carehalcare.carehalcare.domain.board.sleepstate.SleepState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.DefaultRevisionEntity;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SleepStateHistResponseDto {
    private Long id;
    private String userId;
    private String puserId;
    private String state;
    private String content;
    private String category;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
    private Integer revNum;

    public SleepStateHistResponseDto(SleepState sleepState, DefaultRevisionEntity revisionEntity){
        this.id=sleepState.getId();
        this.userId=sleepState.getUserId();
        this.puserId=sleepState.getPuserId();
        this.state=sleepState.getState();
        this.content=sleepState.getContent();
        this.category=sleepState.getCategory();
        this.createdDateTime=sleepState.getCreatedDateTime();
        this.modifiedDateTime=sleepState.getModifiedDateTime();
        this.revNum=revisionEntity.getId();
    }

    public SleepStateHistResponseDto(SleepState sleepState){
        this.id=sleepState.getId();
        this.userId=sleepState.getUserId();
        this.puserId=sleepState.getPuserId();
        this.state=sleepState.getState();
        this.content=sleepState.getContent();
        this.category=sleepState.getCategory();
        this.createdDateTime=sleepState.getCreatedDateTime();
        this.modifiedDateTime=sleepState.getModifiedDateTime();
    }
}
