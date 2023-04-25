package carehalcare.carehalcare.domain.board.surroundingcleanliness;

import carehalcare.carehalcare.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Builder
    public SurroundingCleanliness(String userId, String puserId, String cleanliness, String content){
        this.userId = userId;
        this.puserId = puserId;
        this.cleanliness = cleanliness;
        this.content = content;
    }
}
