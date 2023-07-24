package carehalcare.carehalcare.domain.board.meal;

import carehalcare.carehalcare.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AuditOverride(forClass=BaseTimeEntity.class)
@Audited
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

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToMany(mappedBy = "meal", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<MealImage> images = new ArrayList<>();

    private String category; // 전체카테고리에서 상세조회하기 위함

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

    public Meal updateMeal(String content){
        this.content = content;
        return this;
    }


}
