package carehalcare.carehalcare.dto.board.administraion;

import carehalcare.carehalcare.domain.board.administration.Administration;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdministrationSaveRequestDto {
    private String userId;
    private String puserId;
    private String time;
    private String mealStatus;
    private String medicine;

    @Builder
    public AdministrationSaveRequestDto(String userId, String puserId, String time,
                                        String mealStatus, String medicine){
        this.userId =  userId;
        this.puserId = puserId;
        this.time = time;
        this.mealStatus = mealStatus;
        this.medicine = medicine;
    }

    public Administration toEntity(){
        return Administration.builder()
                .userId(userId)
                .puserId(puserId)
                .time(time)
                .mealStatus(mealStatus)
                .medicine(medicine)
                .build();
    }
}
