package carehalcare.carehalcare.dto.board.administraion;

import carehalcare.carehalcare.domain.board.Administration;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AdministrationResponseRequestDto {
    private Long id;
    private String userId;
    private String puserId;
    private String time;
    private String mealStatus;
    private String medicine;
    private LocalDateTime createdDate;

    public AdministrationResponseRequestDto(Administration entity){
        this.id=entity.getId();
        this.userId=entity.getUserId();
        this.puserId=entity.getPuserId();
        this.time=entity.getTime();
        this.mealStatus=entity.getMealStatus();
        this.medicine=entity.getMedicine();
        this.createdDate=entity.getCreatedDate();
    }
}
