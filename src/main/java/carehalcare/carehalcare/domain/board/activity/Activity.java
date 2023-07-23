package carehalcare.carehalcare.domain.board.activity;

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
public class Activity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String puserId;

    private String rehabilitation;

    private String walkingAssistance;

    private String position;

    private String category;

    @Builder
    public Activity (String userId, String puserId, String rehabilitation,
                     String walkingAssistance, String position, String category){
        this.userId = userId;
        this.puserId = puserId;
        this.rehabilitation = rehabilitation;
        this.walkingAssistance = walkingAssistance;
        this.position=position;
        this.category=category;
    }

    public Activity updateActivity(String rehabilitation,String walkingAssistance,
                                   String position){
        this.rehabilitation=rehabilitation;
        this.walkingAssistance=walkingAssistance;
        this.position=position;
        return this;
    }
}
