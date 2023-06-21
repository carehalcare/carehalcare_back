package carehalcare.carehalcare.dto.board.bowelmovement;

import carehalcare.carehalcare.domain.board.bowelmovement.BowelMovement;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BowelMovementResponseDto {
    private Long id;
    private String userId;
    private String puserId;
    private Long count;
    private String content;
    private String category;
    private LocalDateTime createdDateTime;

    public BowelMovementResponseDto(BowelMovement entity){
        this.id=entity.getId();
        this.userId=entity.getUserId();
        this.puserId=entity.getPuserId();
        this.count=entity.getCount();
        this.content=entity.getContent();
        this.category=entity.getCategory();
        this.createdDateTime=entity.getCreatedDateTime();
    }
}
