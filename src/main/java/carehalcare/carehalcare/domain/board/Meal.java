package carehalcare.carehalcare.domain.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String pUserId;

    private String content;

    private String location;

    // @OneToMany(mappedBy = "meal_id", cascade=CascadeType.ALL)
    // private List<Image> Images;

    @Builder
    public Meal(String userId, String pUserId, String content, String location){
        //List<Image> images
        this.userId = userId;
        this.pUserId = pUserId;
        this.content = content;
        this.location = location;
        //this.images=images;
    }
}
