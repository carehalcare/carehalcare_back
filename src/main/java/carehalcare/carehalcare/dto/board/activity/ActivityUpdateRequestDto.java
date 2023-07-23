package carehalcare.carehalcare.dto.board.activity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ActivityUpdateRequestDto {
    @ApiModelProperty(name  = "활동 기록 id", example = "1")
    private Long id;

    @ApiModelProperty(name  = "재활치료", example = "Y")
    private String rehabilitation;

    @ApiModelProperty(name  = "보행도움", example = "Y")
    private String walkingAssistance;

    @ApiModelProperty(name  = "체위변경", example = "N")
    private String position;

}
