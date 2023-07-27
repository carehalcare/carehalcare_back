package carehalcare.carehalcare.controller.board;

import carehalcare.carehalcare.dto.board.sleepstate.SleepStateHistResponseDto;
import carehalcare.carehalcare.dto.board.sleepstate.SleepStateResponseDto;
import carehalcare.carehalcare.dto.board.sleepstate.SleepStateSaveRequestDto;
import carehalcare.carehalcare.dto.board.sleepstate.SleepStateUpdateRequestDto;
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
@RequestMapping("/api")
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

    /* 수면 상태 기록 수정 */
    @ApiOperation(value="수면 상태 기록 수정")
    @PutMapping("/sleepstates")
    public Long updateSleepState(@RequestBody SleepStateUpdateRequestDto requestDto){
        return sleepStateService.updateSleepState(requestDto);
    }

    /* 수면 상태 기록 변경 이력 리스트 조회 */
    @ApiOperation(value="수면 상태 기록 변경 이력 리스트 조회")
    @GetMapping("/sleepstatehists/list/{id}")
    public List<SleepStateHistResponseDto> getSleepStateHists(
            @PathVariable("id") Long id) throws Exception{
        return sleepStateService.getSleepStateHists(id);
    }

    /* 수면 상태 기록 변경 이력 상세 조회 */
    @ApiOperation(value="수면 상태 기록 변경 이력 상세 조회")
    @GetMapping("/sleepstatehists/{id}/{revNum}")
    public SleepStateHistResponseDto getSleepStateHist(
            @PathVariable("id") Long id,
            @PathVariable("revNum") Integer revNum) throws Exception{
        return sleepStateService.getSleepStateHist(id, revNum);
    }
}
