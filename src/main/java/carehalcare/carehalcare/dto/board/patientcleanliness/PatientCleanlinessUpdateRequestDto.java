package carehalcare.carehalcare.dto.board.patientcleanliness;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PatientCleanlinessUpdateRequestDto {
    @ApiModelProperty(name  = "환자 청결 기록 id", example = "1")
    private Long id;

    @ApiModelProperty(name  = "환자 몸 관련 청결", example = "세안")
    private String cleanliness;

    @ApiModelProperty(name  = "세신 부위", example = "얼굴")
    private String part;

    @ApiModelProperty(name  = "특이사항", example = "특이사항 내용")
    private String content;
}
