package carehalcare.carehalcare.dto.board.activity;

import carehalcare.carehalcare.domain.board.activity.Activity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ActivitySaveRequestDto {
    @ApiModelProperty(name  = "간병인 id", example = "userid2")
    private String userId;

    @ApiModelProperty(name  = "환자(보호자) id", example = "userid1")
    private String puserId;

    @ApiModelProperty(name  = "재활치료", example = "Y")
    private String rehabilitation;

    @ApiModelProperty(name  = "보행도움", example = "Y")
    private String walkingAssistance;

    @ApiModelProperty(name  = "체위변경", example = "N")
    private String position;

    @Builder
    public ActivitySaveRequestDto(String userId, String puserId, String rehabilitation,
                                  String walkingAssistance, String position){
        this.userId =  userId;
        this.puserId = puserId;
        this.rehabilitation = rehabilitation;
        this.walkingAssistance = walkingAssistance;
        this.position = position;
    }

    public Activity toEntity(){
        return Activity.builder()
                .userId(userId)
                .puserId(puserId)
                .rehabilitation(rehabilitation)
                .walkingAssistance(walkingAssistance)
                .position(position)
                .build();
    }
}
