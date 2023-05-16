package carehalcare.carehalcare.dto.board.meal;

import carehalcare.carehalcare.domain.board.meal.Meal;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class MealResponseDto {
    private Long id;
    private String userId;
    private String puserId;
    private String content;
    private List<MealImageResponseDto> images;
    private LocalDateTime createdDateTime;


    public MealResponseDto(Meal meal){
        this.id=meal.getId();
        this.userId=meal.getUserId();
        this.puserId=meal.getPuserId();
        this.content=meal.getContent();
        this.createdDateTime=meal.getCreatedDateTime();
        this.images=meal.getImages()
                .stream()
                .map(MealImageResponseDto::new)
                .collect(Collectors.toList());
    }
}
