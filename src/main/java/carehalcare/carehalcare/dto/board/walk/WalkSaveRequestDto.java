package carehalcare.carehalcare.dto.board.walk;

import carehalcare.carehalcare.domain.board.walk.Walk;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WalkSaveRequestDto {
    @ApiModelProperty(name  = "간병인 id", example = "userid2")
    private String userId;

    @ApiModelProperty(name  = "환자(보호자) id", example = "userid1")
    private String puserId;

    @Builder
    public WalkSaveRequestDto(String userId, String puserId){
        this.userId=userId;
        this.puserId=puserId;
    }

    public Walk toEntity(){
        return Walk.builder()
                .userId(userId)
                .puserId(puserId)
                .category("walks")
                .build();
    }
}
