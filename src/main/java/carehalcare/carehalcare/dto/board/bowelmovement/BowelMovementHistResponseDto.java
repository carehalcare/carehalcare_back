package carehalcare.carehalcare.dto.board.bowelmovement;

import carehalcare.carehalcare.domain.board.bowelmovement.BowelMovement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.DefaultRevisionEntity;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BowelMovementHistResponseDto {
    private Long id;
    private String userId;
    private String puserId;
    private Long count;
    private String content;
    private String category;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
    private Integer revNum;

    public BowelMovementHistResponseDto(BowelMovement bowelMovement, DefaultRevisionEntity revisionEntity){
        this.id=bowelMovement.getId();
        this.userId=bowelMovement.getUserId();
        this.puserId=bowelMovement.getPuserId();
        this.count=bowelMovement.getCount();
        this.content=bowelMovement.getContent();
        this.category=bowelMovement.getCategory();
        this.createdDateTime=bowelMovement.getCreatedDateTime();
        this.modifiedDateTime=bowelMovement.getModifiedDateTime();
        this.revNum=revisionEntity.getId();
    }

    public BowelMovementHistResponseDto(BowelMovement bowelMovement){
        this.id=bowelMovement.getId();
        this.userId=bowelMovement.getUserId();
        this.puserId=bowelMovement.getPuserId();
        this.count=bowelMovement.getCount();
        this.content=bowelMovement.getContent();
        this.category=bowelMovement.getCategory();
        this.createdDateTime=bowelMovement.getCreatedDateTime();
        this.modifiedDateTime=bowelMovement.getModifiedDateTime();
    }
}
