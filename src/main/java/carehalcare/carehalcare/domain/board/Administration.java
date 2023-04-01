package carehalcare.carehalcare.domain.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Administration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String pUserId;

    private String time;

    private String mealStatus;

    private String medicine;

    @Builder
    public Administration(String userId, String pUserId, String time,
                          String mealStatus, String medicine){
        this.userId = userId;
        this.pUserId = pUserId;
        this.time = time;
        this.mealStatus = mealStatus;
        this.medicine = medicine;
    }

}
