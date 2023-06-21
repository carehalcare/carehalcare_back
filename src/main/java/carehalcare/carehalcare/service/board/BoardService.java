package carehalcare.carehalcare.service.board;

import carehalcare.carehalcare.domain.board.walk.Walk;
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

import java.util.ArrayList;
import java.util.List;

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
        List<AdministrationResponseDto> administrationList
                = administrationService.getList(userId, puserId);
        List<BowelMovementResponseDto> bowelMovementList
                = bowelMovementService.getBowelMovementList(userId, puserId);
        List<MealResponseDto> mealList
                = mealService.getMealList(userId, puserId);
        List<PatientCleanlinessResponseDto> patientCleanlinessList
                = patientCleanlinessService.getPatientCleanlinessList(userId, puserId);
        List<SleepStateResponseDto> sleepStateList
                = sleepStateService.getSleepStateList(userId, puserId);
        List<SurroundingCleanlinessResponseDto> surroundingCleanlinessList
                = surroundingCleanlinessService.getSurroundingCleanlinessList(userId, puserId);
        List<WalkResponseDto> walkList
                = walkService.getWalkList(userId, puserId);


        boardList.add(new BoardResponseDto(activityList));
        boardList.add(new BoardResponseDto(administrationList));
        boardList.add(new BoardResponseDto(bowelMovementList));
        boardList.add(new BoardResponseDto(mealList));
        boardList.add(new BoardResponseDto(patientCleanlinessList));
        boardList.add(new BoardResponseDto(sleepStateList));
        boardList.add(new BoardResponseDto(surroundingCleanlinessList));
        boardList.add(new BoardResponseDto(walkList));


        return boardList;
    }
}


/*for(AdministrationResponseDto administration : administrationList){
            BoardResponseDto dto = BoardResponseDto.builder()
                    .id(administration.getId())
                    .userId(administration.getUserId())
                    .puserId(administration.getPuserId())
                    .content1(administration.getTime())
                    .content2(administration.getMealStatus())
                    .content3(administration.getMedicine())
                    .category(administration.getCategory())
                    .createdDateTime(administration.getCreatedDateTime())
                    .build();
            boardList.add(dto);
        }*/
