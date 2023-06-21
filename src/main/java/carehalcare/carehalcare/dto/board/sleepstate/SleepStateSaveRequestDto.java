package carehalcare.carehalcare.dto.board.sleepstate;

import carehalcare.carehalcare.domain.board.sleepstate.SleepState;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SleepStateSaveRequestDto {
    @ApiModelProperty(name  = "간병인 id", example = "userid2")
    private String userId;

    @ApiModelProperty(name  = "환자(보호자) id", example = "userid1")
    private String puserId;

    @ApiModelProperty(name  = "수면 상태", example = "잘주무심")
    private String state;

    @ApiModelProperty(name  = "특이사항", example = "특이사항 내용")
    private String content;

    @Builder
    public SleepStateSaveRequestDto(String userId, String puserId,
                                    String state, String content){
        this.userId =  userId;
        this.puserId = puserId;
        this.state = state;
        this.content = content;
    }

    public SleepState toEntity(){
        return SleepState.builder()
                .userId(userId)
                .puserId(puserId)
                .state(state)
                .content(content)
                .category("sleepstates")
                .build();
    }
}
