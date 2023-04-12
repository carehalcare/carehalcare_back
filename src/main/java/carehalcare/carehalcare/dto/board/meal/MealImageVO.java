package carehalcare.carehalcare.dto.board.meal;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class MealImageVO {
    @ApiParam(name  = "간병인 id", example = "userid2")
    private String userId;

    @ApiParam(name  = "환자(보호자) id", example = "userid1")
    private String puserId;

    @ApiParam(name  = "특이사항", example = "내용")
    private String content;

    @ApiParam(name  = "이미지")
    private List<MultipartFile> images;
}
