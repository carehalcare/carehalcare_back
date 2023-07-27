package carehalcare.carehalcare.domain.board.sleepstate;

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
public class SleepState extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String puserId;

    private String state;

    private String content;

    private String category;

    @Builder
    public SleepState(String userId, String puserId, String state,
                      String content, String category){
        this.userId = userId;
        this.puserId = puserId;
        this.state = state;
        this.content = content;
        this.category = category;
    }

    public SleepState updateSleepState(String state, String content){
        this.state = state;
        this.content = content;
        return this;
    }
}
