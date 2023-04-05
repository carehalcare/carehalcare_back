package carehalcare.carehalcare.domain.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Walk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String pUserId;

    //private List<Image> walkImage;

    @Builder
    public Walk (String userId, String pUserId){
        //List<Image> walkImage
        this.userId = userId;
        this.pUserId = pUserId;
        //this.walkImage = walkImage;
    }
}
