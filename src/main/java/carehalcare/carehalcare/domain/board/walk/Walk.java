package carehalcare.carehalcare.domain.board.walk;

import carehalcare.carehalcare.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Walk extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="walkId")
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String puserId;

    @OneToMany(mappedBy = "walk", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WalkImage> walkImage = new ArrayList<>();

    @Builder
    public Walk (String userId, String puserId){
        this.userId = userId;
        this.puserId = puserId;
    }

    public void addWalkImage(WalkImage image){
        this.walkImage.add(image);
        if(image.getWalk() != this){
            image.setWalk(this);
        }
    }
}
