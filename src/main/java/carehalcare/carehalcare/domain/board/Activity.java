package carehalcare.carehalcare.domain.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String pUserId;

    private String rehabilitation;

    private String walkingAssistance;

    private String position;

    @Builder
    public Activity (String userId, String pUserId, String rehabilitation,
                     String walkingAssistance, String position){
        this.userId = userId;
        this.pUserId = pUserId;
        this.rehabilitation = rehabilitation;
        this.walkingAssistance = walkingAssistance;
        this.position=position;
    }

}
