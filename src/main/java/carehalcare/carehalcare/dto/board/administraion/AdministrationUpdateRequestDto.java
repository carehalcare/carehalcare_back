package carehalcare.carehalcare.dto.board.administraion;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdministrationUpdateRequestDto {
    @ApiModelProperty(name  = "투약 기록 id", example = "1")
    private Long id;

    @ApiModelProperty(name  = "시각", example = "아침")
    private String time;

    @ApiModelProperty(name  = "식사 여부", example = "공복")
    private String mealStatus;

    @ApiModelProperty(name  = "약 종류", example = "약 이름")
    private String medicine;
}
