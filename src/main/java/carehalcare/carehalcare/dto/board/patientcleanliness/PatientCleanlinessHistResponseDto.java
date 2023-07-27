package carehalcare.carehalcare.dto.board.patientcleanliness;

import carehalcare.carehalcare.domain.board.patientcleanliness.PatientCleanliness;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.DefaultRevisionEntity;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PatientCleanlinessHistResponseDto {
    private Long id;
    private String userId;
    private String puserId;
    private String cleanliness;
    private String part;
    private String content;
    private String category;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
    private Integer revNum;

    public PatientCleanlinessHistResponseDto(
            PatientCleanliness patientCleanliness, DefaultRevisionEntity revisionEntity){
        this.id=patientCleanliness.getId();
        this.userId=patientCleanliness.getUserId();
        this.puserId=patientCleanliness.getPuserId();
        this.cleanliness=patientCleanliness.getCleanliness();
        this.part=patientCleanliness.getPart();
        this.content=patientCleanliness.getContent();
        this.category=patientCleanliness.getCategory();
        this.createdDateTime=patientCleanliness.getCreatedDateTime();
        this.modifiedDateTime=patientCleanliness.getModifiedDateTime();
        this.revNum=revisionEntity.getId();
    }

    public PatientCleanlinessHistResponseDto(PatientCleanliness patientCleanliness){
        this.id=patientCleanliness.getId();
        this.userId=patientCleanliness.getUserId();
        this.puserId=patientCleanliness.getPuserId();
        this.cleanliness=patientCleanliness.getCleanliness();
        this.part=patientCleanliness.getPart();
        this.content=patientCleanliness.getContent();
        this.category=patientCleanliness.getCategory();
        this.createdDateTime=patientCleanliness.getCreatedDateTime();
        this.modifiedDateTime=patientCleanliness.getModifiedDateTime();
    }
}
