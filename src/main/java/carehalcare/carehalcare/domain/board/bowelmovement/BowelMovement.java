package carehalcare.carehalcare.domain.board.bowelmovement;

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
public class BowelMovement extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String puserId;

    private Long count;

    private String content;

    private String category;

    @Builder
    public BowelMovement(String userId, String puserId, Long count, String content, String category){
        this.userId = userId;
        this.puserId = puserId;
        this.count = count;
        this.content = content;
        this.category = category;
    }

    public BowelMovement updateBowelMovement(Long count, String content){
        this.count = count;
        this.content = content;
        return this;
    }
}
