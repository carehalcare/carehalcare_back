package carehalcare.carehalcare.dto.board.meal;

import carehalcare.carehalcare.domain.board.meal.MealImage;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MealImageResponseDto {
    private Long id;
    private String originalFilename;
    private String storeFilename;
    private String filePath;
    private Long mealId;


    public MealImageResponseDto(MealImage entity){
        this.id=entity.getId();
        this.originalFilename=entity.getOriginalFilename();
        this.storeFilename=entity.getStoreFilename();
        this.filePath=entity.getFilePath();
        this.mealId=entity.getMeal().getId();
    }

}
