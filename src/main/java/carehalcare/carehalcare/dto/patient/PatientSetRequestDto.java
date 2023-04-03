package carehalcare.carehalcare.dto.patient;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PatientSetRequestDto {
    private String userId;
    private String puserId;

    @Builder
    public PatientSetRequestDto(String userId, String puserId){
        this.userId =  userId;
        this.puserId = puserId;
    }
}
