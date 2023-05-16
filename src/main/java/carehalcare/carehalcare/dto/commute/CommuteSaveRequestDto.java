package carehalcare.carehalcare.dto.commute;

import carehalcare.carehalcare.domain.commute.Commute;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommuteSaveRequestDto {
    @ApiModelProperty(name  = "간병인 id", example = "userid2")
    private String userId;

    @ApiModelProperty(name  = "보호자 id", example = "userid1")
    private String puserId;

    @ApiModelProperty(name  = "출퇴근", example = "")
    private String category;

    @ApiModelProperty(name  = "출근 날짜", example = "")
    private String date;

    @ApiModelProperty(name  = "출근 시각", example = "")
    private String time;

    @Builder
    public CommuteSaveRequestDto(String userId, String puserId, String category,
                                 String date, String time){
        this.userId=userId;
        this.puserId=puserId;
        this.category=category;
        this.date=date;
        this.time=time;
    }

    public Commute toEntity(){
        return Commute.builder()
                .userId(userId)
                .puserId(puserId)
                .category(category)
                .date(date)
                .time(time)
                .build();
    }
}
