package carehalcare.carehalcare.dto.board.administraion;

import carehalcare.carehalcare.domain.board.administration.Administration;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdministrationSaveRequestDto {
    @ApiModelProperty(name  = "간병인 id", example = "userid2")
    private String userId;

    @ApiModelProperty(name  = "환자(보호자) id", example = "userid1")
    private String puserId;

    @ApiModelProperty(name  = "시각", example = "아침")
    private String time;

    @ApiModelProperty(name  = "식사 여부", example = "공복")
    private String mealStatus;

    @ApiModelProperty(name  = "약 종류", example = "약 이름")
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
                .category("administrations")
                .build();
    }
}
