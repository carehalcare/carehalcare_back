package carehalcare.carehalcare.dto.board.administraion;

import carehalcare.carehalcare.domain.board.administration.Administration;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AdministrationResponseDto {
    private Long id;
    private String userId;
    private String puserId;
    private String time;
    private String mealStatus;
    private String medicine;
    private String category;
    private LocalDateTime createdDateTime;

    public AdministrationResponseDto(Administration entity){
        this.id=entity.getId();
        this.userId=entity.getUserId();
        this.puserId=entity.getPuserId();
        this.time=entity.getTime();
        this.mealStatus=entity.getMealStatus();
        this.medicine=entity.getMedicine();
        this.category=entity.getCategory();
        this.createdDateTime=entity.getCreatedDateTime();
    }
}
