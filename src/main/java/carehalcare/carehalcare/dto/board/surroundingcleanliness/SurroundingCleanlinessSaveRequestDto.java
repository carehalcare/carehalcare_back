package carehalcare.carehalcare.dto.board.surroundingcleanliness;

import carehalcare.carehalcare.domain.board.surroundingcleanliness.SurroundingCleanliness;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SurroundingCleanlinessSaveRequestDto {
    @ApiModelProperty(name  = "간병인 id", example = "userid2")
    private String userId;

    @ApiModelProperty(name  = "환자(보호자) id", example = "userid1")
    private String puserId;

    @ApiModelProperty(name  = "주변 환경 청결", example = "환기")
    private String cleanliness;

    @ApiModelProperty(name  = "특이사항", example = "특이사항 내용")
    private String content;

    @Builder
    public SurroundingCleanlinessSaveRequestDto(String userId, String puserId,
                                                String cleanliness, String content){
        this.userId =  userId;
        this.puserId = puserId;
        this.cleanliness = cleanliness;
        this.content = content;
    }

    public SurroundingCleanliness toEntity(){
        return SurroundingCleanliness.builder()
                .userId(userId)
                .puserId(puserId)
                .cleanliness(cleanliness)
                .content(content)
                .build();
    }
}
