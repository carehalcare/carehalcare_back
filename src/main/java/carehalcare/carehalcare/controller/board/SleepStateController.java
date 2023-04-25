package carehalcare.carehalcare.controller.board;

import carehalcare.carehalcare.dto.board.sleepstate.SleepStateResponseDto;
import carehalcare.carehalcare.dto.board.sleepstate.SleepStateSaveRequestDto;
import carehalcare.carehalcare.service.board.SleepStateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="수면 상태 기록 API")
@RequiredArgsConstructor
@RestController
public class SleepStateController {

    private final SleepStateService sleepStateService;

    /* 수면 상태 기록 저장 */
    @ApiOperation(value="수면 상태 기록 저장", notes="puserId는 보호자 아이디, userId는 간병인 아이디")
    @PostMapping("/sleepstates")
    public Long saveSleepState(@RequestBody SleepStateSaveRequestDto requsetDto){
        return sleepStateService.saveSleepState(requsetDto);
    }

    /* 수면 상태 기록 리스트 조회 */
    @ApiOperation(value="수면 상태 기록 리스트 조회")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "uid", value = "간병인 아이디"),
                    @ApiImplicitParam(name = "puid", value = "보호자 아이디")}
    )
    @GetMapping("/sleepstates/list/{uid}/{puid}")
    public List<SleepStateResponseDto> getSleepStateList(
            @PathVariable("uid") String userId,
            @PathVariable("puid") String puserId) throws  Exception{
        return this.sleepStateService.getSleepStateList(userId, puserId);
    }

    /* 수면 상태 기록 상세 조회 */
    @ApiOperation(value="수면 상태 기록 상세 조회")
    @ApiImplicitParam(name = "id", value = "게시글 아이디")
    @GetMapping("/sleepstates/{id}")
    public SleepStateResponseDto findById(
            @PathVariable("id") Long id) throws Exception{
        return sleepStateService.findById(id);
    }

    /* 수면 상태 기록 삭제 */
    @ApiOperation(value="수면 상태 기록 삭제")
    @ApiImplicitParam(name = "id", value = "게시글 아이디")
    @DeleteMapping("/sleepstates/{id}")
    public ResponseEntity<?> deleteSleepState(@PathVariable("id") Long id){
        sleepStateService.deleteSleepState(id);
        return ResponseEntity.noContent().build();
    }
}
