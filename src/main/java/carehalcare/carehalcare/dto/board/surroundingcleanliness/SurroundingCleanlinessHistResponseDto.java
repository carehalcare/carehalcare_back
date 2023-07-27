package carehalcare.carehalcare.dto.board.surroundingcleanliness;

import carehalcare.carehalcare.domain.board.surroundingcleanliness.SurroundingCleanliness;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.DefaultRevisionEntity;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SurroundingCleanlinessHistResponseDto {
    private Long id;
    private String userId;
    private String puserId;
    private String cleanliness;
    private String content;
    private String category;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
    private Integer revNum;

    public SurroundingCleanlinessHistResponseDto(
            SurroundingCleanliness sCleanliness, DefaultRevisionEntity revisionEntity){
        this.id=sCleanliness.getId();
        this.userId=sCleanliness.getUserId();
        this.puserId=sCleanliness.getPuserId();
        this.cleanliness=sCleanliness.getCleanliness();
        this.content=sCleanliness.getContent();
        this.category=sCleanliness.getCategory();
        this.createdDateTime=sCleanliness.getCreatedDateTime();
        this.modifiedDateTime=sCleanliness.getModifiedDateTime();
        this.revNum=revisionEntity.getId();
    }

    public SurroundingCleanlinessHistResponseDto(SurroundingCleanliness sCleanliness){
        this.id=sCleanliness.getId();
        this.userId=sCleanliness.getUserId();
        this.puserId=sCleanliness.getPuserId();
        this.cleanliness=sCleanliness.getCleanliness();
        this.content=sCleanliness.getContent();
        this.category=sCleanliness.getCategory();
        this.createdDateTime=sCleanliness.getCreatedDateTime();
        this.modifiedDateTime=sCleanliness.getModifiedDateTime();
    }
}
