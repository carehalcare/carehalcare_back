package carehalcare.carehalcare.domain.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class SurroundingCleanliness {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String pUserId;

    private String cleanliness;

    private String content;

    @Builder
    public SurroundingCleanliness(String userId, String pUserId, String cleanliness, String content){
        this.userId = userId;
        this.pUserId = pUserId;
        this.cleanliness = cleanliness;
        this.content = content;
    }
}
