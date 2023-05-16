package carehalcare.carehalcare.domain.commute;

import carehalcare.carehalcare.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Commute extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String puserId;

    @Column(nullable = false)
    private String category;

    private String date;

    private String time;

    @Builder
    public Commute(String userId, String puserId, String category,
                                  String date, String time){
        this.userId = userId;
        this.puserId = puserId;
        this.category = category;
        this.date = date;
        this.time = time;
    }
}
