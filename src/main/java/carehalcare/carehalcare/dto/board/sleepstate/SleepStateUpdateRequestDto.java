package carehalcare.carehalcare.dto.board.sleepstate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SleepStateUpdateRequestDto {
    @ApiModelProperty(name  = "수면 상태 기록 id", example = "1")
    private Long id;

    @ApiModelProperty(name  = "수면 상태", example = "잘주무심")
    private String state;

    @ApiModelProperty(name  = "특이사항", example = "특이사항 내용")
    private String content;
}
