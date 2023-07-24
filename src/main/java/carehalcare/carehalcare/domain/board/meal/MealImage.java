package carehalcare.carehalcare.domain.board.meal;

import carehalcare.carehalcare.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class MealImage extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mealImageId")
    private Long id;

    private String originalFilename;

    private String storeFilename;

    private String filePath;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne
    @JoinColumn(name = "mealId")
    private Meal meal;

    @Builder
    public MealImage(String originalFilename, String storeFilename,
                     String filePath){
        this.originalFilename=originalFilename;
        this.storeFilename=storeFilename;
        this.filePath=filePath;
    }

    public void setMeal(Meal meal){
        this.meal = meal;
        if(!meal.getImages().contains(this)){
            meal.getImages().add(this);
        }
    }

}
