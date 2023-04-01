package carehalcare.carehalcare.domain.notice;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    private String content;

    @Builder
    public Notice(String userId, String pUserId, String content){
        this.userId = userId;
        this.content = content;
    }
}
