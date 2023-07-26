package carehalcare.carehalcare.dto.board.bowelmovement;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BowelMovementUpdateRequestDto {
    @ApiModelProperty(name  = "투약 기록 id", example = "1")
    private Long id;

    @ApiModelProperty(name  = "횟수", example = "1")
    private Long count;

    @ApiModelProperty(name  = "특이사항", example = "특이사항내용")
    private String content;

}
