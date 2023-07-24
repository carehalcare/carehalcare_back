package carehalcare.carehalcare.dto.board.meal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MealUpdateRequestDto {
    @ApiModelProperty(name  = "식사 기록 id", example = "1")
    private Long id;

    @ApiModelProperty(name  = "특이사항", example = "내용")
    private String content;
}
