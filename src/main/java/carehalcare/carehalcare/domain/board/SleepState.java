package carehalcare.carehalcare.domain.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class SleepState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String pUserId;

    private String state;

    private String content;

    @Builder
    public SleepState(String userId, String pUserId, String state, String content){
        this.userId = userId;
        this.pUserId = pUserId;
        this.state = state;
        this.content = content;
    }
}
