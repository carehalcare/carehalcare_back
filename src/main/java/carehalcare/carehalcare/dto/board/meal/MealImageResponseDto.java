package carehalcare.carehalcare.dto.board.meal;

import carehalcare.carehalcare.domain.board.meal.MealImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;


@Getter
@NoArgsConstructor
public class MealImageResponseDto {
    private Long id;
    private String originalFilename;
    private String storeFilename;
    private String filePath;
    private Long mealId;
    private String encodedString;


    public MealImageResponseDto(MealImage entity){
        this.id=entity.getId();
        this.originalFilename=entity.getOriginalFilename();
        this.storeFilename=entity.getStoreFilename();
        this.filePath=entity.getFilePath();
        this.mealId=entity.getMeal().getId();

        try{
            InputStream inputStream = new FileInputStream(this.filePath);
            byte[] bytes = IOUtils.toByteArray(inputStream);
            this.encodedString = Base64.getEncoder().encodeToString(bytes);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
