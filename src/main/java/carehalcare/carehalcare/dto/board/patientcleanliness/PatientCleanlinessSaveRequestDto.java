package carehalcare.carehalcare.dto.board.patientcleanliness;

import carehalcare.carehalcare.domain.board.patientcleanliness.PatientCleanliness;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PatientCleanlinessSaveRequestDto {
    @ApiModelProperty(name  = "간병인 id", example = "userid2")
    private String userId;

    @ApiModelProperty(name  = "환자(보호자) id", example = "userid1")
    private String puserId;

    @ApiModelProperty(name  = "환자 몸 관련 청결", example = "세안")
    private String cleanliness;

    @ApiModelProperty(name  = "세신 부위", example = "얼굴")
    private String part;

    @ApiModelProperty(name  = "특이사항", example = "특이사항 내용")
    private String content;

    @Builder
    public PatientCleanlinessSaveRequestDto(String userId, String puserId,
                                            String cleanliness,  String part, String content){
        this.userId =  userId;
        this.puserId = puserId;
        this.cleanliness = cleanliness;
        this.part = part;
        this.content = content;
    }

    public PatientCleanliness toEntity(){
        return PatientCleanliness.builder()
                .userId(userId)
                .puserId(puserId)
                .cleanliness(cleanliness)
                .part(part)
                .content(content)
                .category("pcleanliness")
                .build();
    }
}
