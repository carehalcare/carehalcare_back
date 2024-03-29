package carehalcare.carehalcare.dto.board.meal;

import carehalcare.carehalcare.domain.board.meal.Meal;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class MealSaveRequestDto {
    @ApiModelProperty(name  = "간병인 id", example = "userid2")
    private String userId;

    @ApiModelProperty(name  = "환자(보호자) id", example = "userid1")
    private String puserId;

    @ApiModelProperty(name  = "특이사항", example = "내용")
    private String content;

    @Builder
    public MealSaveRequestDto(String userId, String puserId, String content){
        this.userId=userId;
        this.puserId=puserId;
        this.content=content;

    }

    public Meal toEntity(){
        return Meal.builder()
                .userId(userId)
                .puserId(puserId)
                .content(content)
                .category("meals")
                .build();
    }
}
