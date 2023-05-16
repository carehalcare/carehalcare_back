package carehalcare.carehalcare.dto.notice;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeUpdateRequestDto {
    @ApiModelProperty(name  = "공지사항 id", example = "1")
    private Long id;

    @ApiModelProperty(name  = "공지 내용", example = "공지 내용")
    private String content;
}
