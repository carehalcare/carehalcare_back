package carehalcare.carehalcare.dto.notice;

import carehalcare.carehalcare.domain.notice.Notice;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.naming.NoInitialContextException;

@Getter
@NoArgsConstructor
public class NoticeSaveRequestDto {
    @ApiModelProperty(name  = "보호자 id", example = "userid1")
    private String userId;

    @ApiModelProperty(name  = "공지 내용", example = "공지 내용")
    private String content;

    @Builder
    public NoticeSaveRequestDto(String userId, String content){
        this.userId=userId;
        this.content=content;
    }

    public Notice toEntity(){
        return Notice.builder()
                .userId(userId)
                .content(content)
                .build();
    }
}
