package carehalcare.carehalcare.dto.board.activity;

import carehalcare.carehalcare.domain.board.activity.Activity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.DefaultRevisionEntity;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ActivityHistResponseDto {
    private Long id;
    private String userId;
    private String puserId;
    private String rehabilitation;
    private String walkingAssistance;
    private String position;
    private String category;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
    private Integer revNum;

    public ActivityHistResponseDto(Activity activity, DefaultRevisionEntity revisionEntity){
        this.id=activity.getId();
        this.userId=activity.getUserId();
        this.puserId=activity.getPuserId();
        this.rehabilitation=activity.getRehabilitation();
        this.walkingAssistance=activity.getWalkingAssistance();
        this.position=activity.getPosition();
        this.category=activity.getCategory();
        this.createdDateTime=activity.getCreatedDateTime();
        this.modifiedDateTime=activity.getModifiedDateTime();
        this.revNum=revisionEntity.getId();
    }

    public ActivityHistResponseDto(Activity activity){
        this.id=activity.getId();
        this.userId=activity.getUserId();
        this.puserId=activity.getPuserId();
        this.rehabilitation=activity.getRehabilitation();
        this.walkingAssistance=activity.getWalkingAssistance();
        this.position=activity.getPosition();
        this.category=activity.getCategory();
        this.createdDateTime=activity.getCreatedDateTime();
        this.modifiedDateTime=activity.getModifiedDateTime();
    }
}
