package carehalcare.carehalcare.dto.board.patientcleanliness;

import carehalcare.carehalcare.domain.board.patientcleanliness.PatientCleanliness;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PatientCleanlinessResponseDto {
    private Long id;
    private String userId;
    private String puserId;
    private String cleanliness;
    private String part;
    private String content;
    private LocalDateTime createdDate;

    public PatientCleanlinessResponseDto(PatientCleanliness entity){
        this.id= entity.getId();
        this.userId=entity.getUserId();
        this.puserId=entity.getPuserId();
        this.cleanliness=entity.getCleanliness();
        this.part=entity.getPart();
        this.content=entity.getContent();
        this.createdDate=entity.getCreatedDate();
    }
}
