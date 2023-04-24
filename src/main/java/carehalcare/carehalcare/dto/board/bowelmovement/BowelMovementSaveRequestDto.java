package carehalcare.carehalcare.dto.board.bowelmovement;

import carehalcare.carehalcare.domain.board.bowelmovement.BowelMovement;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BowelMovementSaveRequestDto {
    @ApiModelProperty(name  = "간병인 id", example = "userid2")
    private String userId;

    @ApiModelProperty(name  = "환자(보호자) id", example = "userid1")
    private String puserId;

    @ApiModelProperty(name  = "횟수", example = "1")
    private Long count;

    @ApiModelProperty(name  = "특이사항", example = "특이사항내용")
    private String content;

    @Builder
    public BowelMovementSaveRequestDto(String userId, String puserId,
                                       Long count, String content){
        this.userId=userId;
        this.puserId = puserId;
        this.count = count;
        this.content = content;
    }

    public BowelMovement toEntity(){
        return BowelMovement.builder()
                .userId(userId)
                .puserId(puserId)
                .count(count)
                .content(content)
                .build();
    }
}
