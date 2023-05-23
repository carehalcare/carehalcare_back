package carehalcare.carehalcare.service.board.meal;

import carehalcare.carehalcare.domain.board.meal.*;
import carehalcare.carehalcare.dto.board.meal.MealResponseDto;
import carehalcare.carehalcare.dto.board.meal.MealSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@Service
public class MealService {
    private final MealRepository mealRepository;
    private final MealImageRepository mealImageRepository;
    private final MealFileHandler mealFileHandler;
    private final MealAwsS3Service mealAwsS3Service;

    /* 식사 기록 저장 */
    @Transactional
    public Long saveMeal(MealSaveRequestDto requestDto, List<MultipartFile> images) throws IOException{
        //List<MealImage> mealImages = mealFileHandler.storeFiles(images);
        List<MealImage> mealImages = mealAwsS3Service.uploadFile(images);
        Meal meal = requestDto.toEntity();

        for(MealImage image:mealImages){
            meal.addImages(mealImageRepository.save(image));
            image.setMeal(meal);
        }
        return mealRepository.save(meal).getId();
    }

    /* 식사 기록 리스트 조회 */
    @Transactional(readOnly = true)
    public List<MealResponseDto> getMealList(
            String userId, String puserId){
        return this.mealRepository.findByUserIdAndPuserIdOrderByCreatedDateTimeDesc(userId, puserId);
    }

    /* 식사 기록 상세 조회 */
    @Transactional(readOnly = true)
    public MealResponseDto findById(Long id){
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new MealResponseDto(meal);
    }

    /* 식사 기록 삭제 */
    @Transactional
    public void deleteMeal(Long id){
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        List<MealImage> mealImages = mealImageRepository.findAllByMeal(meal);

        for(MealImage image:mealImages){
            mealAwsS3Service.deleteFile(image.getStoreFilename());
        }
        mealRepository.delete(meal);
    }
}
