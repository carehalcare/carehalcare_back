package carehalcare.carehalcare.domain.board.patientcleanliness;

import carehalcare.carehalcare.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@AuditOverride(forClass=BaseTimeEntity.class)
@Audited
@Getter
@NoArgsConstructor
@Entity
public class PatientCleanliness extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String puserId;

    private String cleanliness;

    private String part;

    private String content;

    private String category;

    @Builder
    public PatientCleanliness(String userId, String puserId, String cleanliness,
                              String part, String content, String category){
        this.userId = userId;
        this.puserId = puserId;
        this.cleanliness = cleanliness;
        this.part = part;
        this.content = content;
        this.category = category;
    }

    public PatientCleanliness updatePatientCleanliness(
            String cleanliness, String part, String content){
        this.cleanliness = cleanliness;
        this.part = part;
        this.content = content;
        return this;
    }
}
