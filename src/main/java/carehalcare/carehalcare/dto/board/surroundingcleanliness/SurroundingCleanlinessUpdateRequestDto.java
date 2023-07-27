package carehalcare.carehalcare.dto.board.surroundingcleanliness;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SurroundingCleanlinessUpdateRequestDto {
    @ApiModelProperty(name  = "주변 환경 청결 기록 id", example = "1")
    private Long id;

    @ApiModelProperty(name  = "주변 환경 청결", example = "환기")
    private String cleanliness;

    @ApiModelProperty(name  = "특이사항", example = "특이사항 내용")
    private String content;
}
