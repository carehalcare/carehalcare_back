package carehalcare.carehalcare.dto.user.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginDto {
    @ApiModelProperty(name  = "사용자 id", example = "userid1")
    private String userId;

    @ApiModelProperty(name  = "사용자 pw", example = "1111")
    private String password;

}
