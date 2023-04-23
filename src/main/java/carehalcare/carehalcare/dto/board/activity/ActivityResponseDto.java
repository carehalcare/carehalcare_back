package carehalcare.carehalcare.dto.board.activity;

import carehalcare.carehalcare.domain.board.activity.Activity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ActivityResponseDto {
    private Long id;
    private String userId;
    private String puserId;
    private String rehabilitation;
    private String walkingAssistance;
    private String position;
    private LocalDateTime createdDate;

    public ActivityResponseDto(Activity entity){
        this.id=entity.getId();
        this.userId=entity.getUserId();
        this.puserId=entity.getPuserId();
        this.rehabilitation=entity.getRehabilitation();
        this.walkingAssistance=entity.getWalkingAssistance();
        this.position=entity.getPosition();
        this.createdDate=entity.getCreatedDate();
    }
}
