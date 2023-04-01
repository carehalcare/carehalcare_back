package carehalcare.carehalcare.domain.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class BowelMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String pUserId;

    private Integer count;

    private String content;

    @Builder
    public BowelMovement(String userId, String pUserId, Integer count, String content){
        this.userId = userId;
        this.pUserId = pUserId;
        this.count = count;
        this.content = content;
    }
}
