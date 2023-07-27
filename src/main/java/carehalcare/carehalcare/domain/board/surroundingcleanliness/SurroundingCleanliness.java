package carehalcare.carehalcare.domain.board.surroundingcleanliness;

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
public class SurroundingCleanliness extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String puserId;

    private String cleanliness;

    private String content;

    private String category;

    @Builder
    public SurroundingCleanliness(String userId, String puserId, String cleanliness,
                                  String content, String category){
        this.userId = userId;
        this.puserId = puserId;
        this.cleanliness = cleanliness;
        this.content = content;
        this.category = category;
    }

    public SurroundingCleanliness updateSurroundingCleanliness(
            String cleanliness, String content){
        this.cleanliness = cleanliness;
        this.content = content;
        return this;
    }
}
