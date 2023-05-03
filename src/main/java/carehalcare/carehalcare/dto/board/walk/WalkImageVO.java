package carehalcare.carehalcare.dto.board.walk;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class WalkImageVO {
    @ApiModelProperty(name  = "간병인 id", example = "userid2")
    private String userId;

    @ApiModelProperty(name  = "환자(보호자) id", example = "userid1")
    private String puserId;

    @ApiModelProperty(name  = "이미지")
    private List<MultipartFile> images;
}
