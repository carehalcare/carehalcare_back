package carehalcare.carehalcare.domain.board.meal;

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
public class Meal extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mealId")
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String puserId;

    private String content;

    @OneToMany(mappedBy = "meal", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<MealImage> images = new ArrayList<>();

    private String category;

    @Builder
    public Meal(String userId, String puserId, String content, String category){
        this.userId = userId;
        this.puserId = puserId;
        this.content = content;
        this.category = category;
    }

    public void addImages(MealImage image){
        this.images.add(image);
        if(image.getMeal() != this){
            image.setMeal(this);
        }
    }
}
