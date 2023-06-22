package carehalcare.carehalcare.service.board;

import carehalcare.carehalcare.dto.board.BoardResponseDto;
import carehalcare.carehalcare.dto.board.activity.ActivityResponseDto;
import carehalcare.carehalcare.dto.board.administraion.AdministrationResponseDto;
import carehalcare.carehalcare.dto.board.bowelmovement.BowelMovementResponseDto;
import carehalcare.carehalcare.dto.board.meal.MealResponseDto;
import carehalcare.carehalcare.dto.board.patientcleanliness.PatientCleanlinessResponseDto;
import carehalcare.carehalcare.dto.board.sleepstate.SleepStateResponseDto;
import carehalcare.carehalcare.dto.board.surroundingcleanliness.SurroundingCleanlinessResponseDto;
import carehalcare.carehalcare.dto.board.walk.WalkResponseDto;
import carehalcare.carehalcare.service.board.meal.MealService;
import carehalcare.carehalcare.service.board.walk.WalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final ActivityService activityService;
    private final AdministrationService administrationService;
    private final BowelMovementService bowelMovementService;
    private final MealService mealService;
    private final PatientCleanlinessService patientCleanlinessService;
    private final SleepStateService sleepStateService;
    private final SurroundingCleanlinessService surroundingCleanlinessService;
    private final WalkService walkService;

    /* 카테고리 전체 조회 */
    @Transactional(readOnly = true)
    public List<BoardResponseDto> getBoardList(
            String userId, String puserId){

        List<BoardResponseDto> boardList = new ArrayList<>();

        List<ActivityResponseDto> activityList
                = activityService.getActivityList(userId, puserId);
        for(ActivityResponseDto activity : activityList) {
            BoardResponseDto dto = BoardResponseDto.builder()
                    .id(activity.getId())
                    .userId(activity.getUserId())
                    .puserId(activity.getPuserId())
                    .category(activity.getCategory())
                    .createdDateTime(activity.getCreatedDateTime())
                    .build();
            boardList.add(dto);
        }

        List<AdministrationResponseDto> administrationList
                = administrationService.getList(userId, puserId);
        for(AdministrationResponseDto administration : administrationList) {
            BoardResponseDto dto = BoardResponseDto.builder()
                    .id(administration.getId())
                    .userId(administration.getUserId())
                    .puserId(administration.getPuserId())
                    .category(administration.getCategory())
                    .createdDateTime(administration.getCreatedDateTime())
                    .build();
            boardList.add(dto);
        }

        List<BowelMovementResponseDto> bowelMovementList
                = bowelMovementService.getBowelMovementList(userId, puserId);
        for(BowelMovementResponseDto bowelMovement : bowelMovementList) {
            BoardResponseDto dto = BoardResponseDto.builder()
                    .id(bowelMovement.getId())
                    .userId(bowelMovement.getUserId())
                    .puserId(bowelMovement.getPuserId())
                    .category(bowelMovement.getCategory())
                    .createdDateTime(bowelMovement.getCreatedDateTime())
                    .build();
            boardList.add(dto);
        }

        List<MealResponseDto> mealList
                = mealService.getMealList(userId, puserId);
        for(MealResponseDto meal : mealList) {
            BoardResponseDto dto = BoardResponseDto.builder()
                    .id(meal.getId())
                    .userId(meal.getUserId())
                    .puserId(meal.getPuserId())
                    .category(meal.getCategory())
                    .createdDateTime(meal.getCreatedDateTime())
                    .build();
            boardList.add(dto);
        }

        List<PatientCleanlinessResponseDto> patientCleanlinessList
                = patientCleanlinessService.getPatientCleanlinessList(userId, puserId);
        for(PatientCleanlinessResponseDto patientCleanliness : patientCleanlinessList) {
            BoardResponseDto dto = BoardResponseDto.builder()
                    .id(patientCleanliness.getId())
                    .userId(patientCleanliness.getUserId())
                    .puserId(patientCleanliness.getPuserId())
                    .category(patientCleanliness.getCategory())
                    .createdDateTime(patientCleanliness.getCreatedDateTime())
                    .build();
            boardList.add(dto);
        }

        List<SleepStateResponseDto> sleepStateList
                = sleepStateService.getSleepStateList(userId, puserId);
        for(SleepStateResponseDto sleepState : sleepStateList) {
            BoardResponseDto dto = BoardResponseDto.builder()
                    .id(sleepState.getId())
                    .userId(sleepState.getUserId())
                    .puserId(sleepState.getPuserId())
                    .category(sleepState.getCategory())
                    .createdDateTime(sleepState.getCreatedDateTime())
                    .build();
            boardList.add(dto);
        }

        List<SurroundingCleanlinessResponseDto> surroundingCleanlinessList
                = surroundingCleanlinessService.getSurroundingCleanlinessList(userId, puserId);
        for(SurroundingCleanlinessResponseDto surroundingCleanliness : surroundingCleanlinessList) {
            BoardResponseDto dto = BoardResponseDto.builder()
                    .id(surroundingCleanliness.getId())
                    .userId(surroundingCleanliness.getUserId())
                    .puserId(surroundingCleanliness.getPuserId())
                    .category(surroundingCleanliness.getCategory())
                    .createdDateTime(surroundingCleanliness.getCreatedDateTime())
                    .build();
            boardList.add(dto);
        }

        List<WalkResponseDto> walkList
                = walkService.getWalkList(userId, puserId);
        for(WalkResponseDto walk : walkList) {
            BoardResponseDto dto = BoardResponseDto.builder()
                    .id(walk.getId())
                    .userId(walk.getUserId())
                    .puserId(walk.getPuserId())
                    .category(walk.getCategory())
                    .createdDateTime(walk.getCreatedDateTime())
                    .build();
            boardList.add(dto);
        }

        Collections.sort(boardList, new SortByDateTime().reversed());
        return boardList;
    }
}


class SortByDateTime implements Comparator<BoardResponseDto> {
    @Override
    public int compare(BoardResponseDto a, BoardResponseDto b) {
        return a.getCreatedDateTime().compareTo(b.getCreatedDateTime());
    }
}

