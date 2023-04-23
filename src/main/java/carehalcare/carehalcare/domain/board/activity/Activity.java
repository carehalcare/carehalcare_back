package carehalcare.carehalcare.domain.board.activity;

import carehalcare.carehalcare.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Builder
    public Activity (String userId, String puserId, String rehabilitation,
                     String walkingAssistance, String position){
        this.userId = userId;
        this.puserId = puserId;
        this.rehabilitation = rehabilitation;
        this.walkingAssistance = walkingAssistance;
        this.position=position;
    }

}
