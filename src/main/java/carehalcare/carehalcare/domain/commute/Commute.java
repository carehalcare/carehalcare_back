package carehalcare.carehalcare.domain.commute;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Commute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String pUserId;

    @Column(nullable = false)
    private String category;

    private String date;

    private String time;

    @Builder
    public Commute(String userId, String pUserId, String category,
                                  String date, String time){
        this.userId = userId;
        this.pUserId = pUserId;
        this.category = category;
        this.date = date;
        this.time = time;
    }
}
