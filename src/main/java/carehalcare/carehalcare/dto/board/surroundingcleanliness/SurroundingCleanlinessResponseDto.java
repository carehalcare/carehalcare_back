package carehalcare.carehalcare.dto.board.surroundingcleanliness;

import carehalcare.carehalcare.domain.board.surroundingcleanliness.SurroundingCleanliness;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SurroundingCleanlinessResponseDto {
    private Long id;
    private String userId;
    private String puserId;
    private String cleanliness;
    private String content;
    private LocalDateTime createdDate;

    public SurroundingCleanlinessResponseDto(SurroundingCleanliness entity){
        this.id= entity.getId();
        this.userId=entity.getUserId();
        this.puserId=entity.getPuserId();
        this.cleanliness=entity.getCleanliness();
        this.content=entity.getContent();
        this.createdDate=entity.getCreatedDate();
    }
}
