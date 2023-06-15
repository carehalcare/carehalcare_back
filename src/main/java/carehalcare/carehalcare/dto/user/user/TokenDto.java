package carehalcare.carehalcare.dto.user.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;

@Builder
@Data
@AllArgsConstructor
public class TokenDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
