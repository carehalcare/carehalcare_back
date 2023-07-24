package carehalcare.carehalcare.dto.board.meal;

import carehalcare.carehalcare.domain.board.meal.Meal;
import carehalcare.carehalcare.domain.board.meal.MealImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.DefaultRevisionEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class MealHistResponseDto {
    private Long id;
    private String userId;
    private String puserId;
    private String content;
    private List<MealImageResponseDto> images;
    private String category;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
    private Integer revNum;

    public MealHistResponseDto(Meal meal, DefaultRevisionEntity revisionEntity, List<MealImage> images){
        this.id=meal.getId();
        this.userId=meal.getUserId();
        this.puserId=meal.getPuserId();
        this.content=meal.getContent();
        this.category=meal.getCategory();
        this.createdDateTime=meal.getCreatedDateTime();
        this.modifiedDateTime=meal.getModifiedDateTime();
        this.images=images.stream()
                .map(MealImageResponseDto::new)
                .collect(Collectors.toList());
        this.revNum=revisionEntity.getId();
    }

    public MealHistResponseDto(Meal meal, List<MealImage> images){
        this.id=meal.getId();
        this.userId=meal.getUserId();
        this.puserId=meal.getPuserId();
        this.content=meal.getContent();
        this.category=meal.getCategory();
        this.createdDateTime=meal.getCreatedDateTime();
        this.modifiedDateTime=meal.getModifiedDateTime();
        this.images=images.stream()
                .map(MealImageResponseDto::new)
                .collect(Collectors.toList());
    }
}
