package carehalcare.carehalcare.service.board.meal;

import carehalcare.carehalcare.domain.board.meal.*;
import carehalcare.carehalcare.dto.board.meal.MealHistResponseDto;
import carehalcare.carehalcare.dto.board.meal.MealResponseDto;
import carehalcare.carehalcare.dto.board.meal.MealSaveRequestDto;
import carehalcare.carehalcare.dto.board.meal.MealUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class MealService {
    private final MealRepository mealRepository;
    private final MealImageRepository mealImageRepository;
    //private final MealFileHandler mealFileHandler;
    private final MealAwsS3Service mealAwsS3Service;
    private final AuditReader auditReader;


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

    /* 식사 기록 수정 */
    @Transactional
    public Long updateMeal(MealUpdateRequestDto requestDto){

        Meal meal = mealRepository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+requestDto.getId()));
        return meal.updateMeal(requestDto.getContent()).getId();
    }

    /* 식사 기록 변경 이력 리스트 조회 */
    @Transactional(readOnly = true)
    public List<MealHistResponseDto> getMealHists(Long id){
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        List<Object[]> hists = auditReader.createQuery()
                .forRevisionsOfEntity(Meal.class, false, false)
                .add(AuditEntity.property("id").eq(id))
                .add(AuditEntity.revisionType().eq(RevisionType.MOD))
                .addOrder(AuditEntity.property("modifiedDateTime").desc())
                .getResultList();

        return hists.stream().map(history -> new MealHistResponseDto(
                (Meal) history[0], (DefaultRevisionEntity) history[1], meal.getImages()))
                .collect(Collectors.toList());
    }

    /* 식사 기록 변경 이력 상세 조회 */
    @Transactional(readOnly = true)
    public MealHistResponseDto getMealHist(Long id, Integer revNum){
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        Object mealHist = auditReader.createQuery()
                .forRevisionsOfEntity(Meal.class, true, false)
                .add(AuditEntity.property("id").eq(id))
                .add(AuditEntity.revisionNumber().eq(revNum))
                .getSingleResult();
        return new MealHistResponseDto((Meal) mealHist, meal.getImages());
    }
}
