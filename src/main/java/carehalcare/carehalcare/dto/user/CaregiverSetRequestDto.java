package carehalcare.carehalcare.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CaregiverSetRequestDto {
    private String userId;
    private String cuserId;

    @Builder
    public CaregiverSetRequestDto(String userId, String cuserId){
        this.userId=userId;
        this.cuserId=cuserId;
    }
}
