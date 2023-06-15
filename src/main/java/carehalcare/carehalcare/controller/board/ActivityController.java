package carehalcare.carehalcare.controller.board;

import carehalcare.carehalcare.dto.board.activity.ActivityResponseDto;
import carehalcare.carehalcare.dto.board.activity.ActivitySaveRequestDto;
import carehalcare.carehalcare.service.board.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="활동 기록 API")
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class ActivityController {

    private final ActivityService activityService;

    /* 활동 기록 저장 */
    @ApiOperation(value="활동 기록 저장", notes="puserId는 보호자 아이디, userId는 간병인 아이디")
    @PostMapping("/activities")
    public Long saveActivity(@RequestBody ActivitySaveRequestDto requestDto){
        return activityService.saveActivity(requestDto);
    }

    /* 활동 기록 리스트 조회 */
    @ApiOperation(value="활동 기록 리스트 조회")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "uid", value = "간병인 아이디"),
                    @ApiImplicitParam(name = "puid", value = "보호자 아이디")}
    )
    @GetMapping("/activities/list/{uid}/{puid}")
    public List<ActivityResponseDto> getActivityList(
            @PathVariable("uid") String userId,
            @PathVariable("puid") String puserId) throws Exception{
        return this.activityService.getActivityList(userId, puserId);
    }

    /* 활동 기록 상세 조회 */
    @ApiOperation(value="활동 기록 상세 조회")
    @ApiImplicitParam(name = "id", value = "게시글 아이디")
    @GetMapping("/activities/{id}")
    public ActivityResponseDto findById(
            @PathVariable("id") Long id) throws Exception{
        return activityService.findById(id);
    }

    /* 활동 기록 삭제 */
    @ApiOperation(value="활동 기록 삭제")
    @ApiImplicitParam(name = "id", value = "게시글 아이디")
    @DeleteMapping("/activities/{id}")
    public ResponseEntity<?> deleteActivity(@PathVariable("id") Long id){
        activityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }
}
