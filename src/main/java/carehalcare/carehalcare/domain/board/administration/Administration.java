package carehalcare.carehalcare.domain.board.administration;

import carehalcare.carehalcare.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Administration extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String puserId;

    private String time;

    private String mealStatus;

    private String medicine;

    private String category;

    @Builder
    public Administration(String userId, String puserId, String time,
                          String mealStatus, String medicine, String category){
        this.userId = userId;
        this.puserId = puserId;
        this.time = time;
        this.mealStatus = mealStatus;
        this.medicine = medicine;
        this.category = category;
    }

}
