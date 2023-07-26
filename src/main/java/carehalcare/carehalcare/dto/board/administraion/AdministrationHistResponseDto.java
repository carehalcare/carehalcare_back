package carehalcare.carehalcare.dto.board.administraion;

import carehalcare.carehalcare.domain.board.administration.Administration;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.DefaultRevisionEntity;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AdministrationHistResponseDto {
    private Long id;
    private String userId;
    private String puserId;
    private String time;
    private String mealStatus;
    private String medicine;
    private String category;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
    private Integer revNum;

    public AdministrationHistResponseDto(Administration administration, DefaultRevisionEntity revisionEntity){
        this.id=administration.getId();
        this.userId=administration.getUserId();
        this.puserId=administration.getPuserId();
        this.time=administration.getTime();
        this.mealStatus=administration.getMealStatus();
        this.medicine=administration.getMedicine();
        this.category=administration.getCategory();
        this.createdDateTime=administration.getCreatedDateTime();
        this.modifiedDateTime=administration.getModifiedDateTime();
        this.revNum=revisionEntity.getId();
    }

    public AdministrationHistResponseDto(Administration administration){
        this.id=administration.getId();
        this.userId=administration.getUserId();
        this.puserId=administration.getPuserId();
        this.time=administration.getTime();
        this.mealStatus=administration.getMealStatus();
        this.medicine=administration.getMedicine();
        this.category=administration.getCategory();
        this.createdDateTime=administration.getCreatedDateTime();
        this.modifiedDateTime=administration.getModifiedDateTime();
    }
}
