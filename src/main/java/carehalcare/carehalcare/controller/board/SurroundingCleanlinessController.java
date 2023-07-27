package carehalcare.carehalcare.controller.board;

import carehalcare.carehalcare.dto.board.surroundingcleanliness.SurroundingCleanlinessHistResponseDto;
import carehalcare.carehalcare.dto.board.surroundingcleanliness.SurroundingCleanlinessResponseDto;
import carehalcare.carehalcare.dto.board.surroundingcleanliness.SurroundingCleanlinessSaveRequestDto;
import carehalcare.carehalcare.dto.board.surroundingcleanliness.SurroundingCleanlinessUpdateRequestDto;
import carehalcare.carehalcare.service.board.SurroundingCleanlinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="주변 환경 청결 기록 API")
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class SurroundingCleanlinessController {

    private final SurroundingCleanlinessService surroundingCleanlinessService;

    /* 주변 환경 청결 기록 저장 */
    @ApiOperation(value="주변 환경 청결 기록 저장", notes="puserId는 보호자 아이디, userId는 간병인 아이디")
    @PostMapping("/scleanliness")
    public Long saveSurroundingCleanliness(@RequestBody SurroundingCleanlinessSaveRequestDto requestDto){
        return surroundingCleanlinessService.saveSurroundingCleanliness(requestDto);
    }

    /* 주변 환경 청결 기록 리스트 조회 */
    @ApiOperation(value="주변 환경 청결 기록 리스트 조회")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "uid", value = "간병인 아이디"),
                    @ApiImplicitParam(name = "puid", value = "보호자 아이디")}
    )
    @GetMapping("/scleanliness/list/{uid}/{puid}")
    public List<SurroundingCleanlinessResponseDto> getSurroundingCleanlinessList(
            @PathVariable("uid") String userId,
            @PathVariable("puid") String puserId) throws Exception{
        return this.surroundingCleanlinessService.getSurroundingCleanlinessList(userId, puserId);
    }

    /* 주변 환경 청결 기록 상세 조회 */
    @ApiOperation(value="주변 환경 청결 기록 상세 조회")
    @ApiImplicitParam(name = "id", value = "게시글 아이디")
    @GetMapping("/scleanliness/{id}")
    public SurroundingCleanlinessResponseDto findById(
            @PathVariable("id") Long id) throws Exception{
        return surroundingCleanlinessService.findById(id);
    }

    /* 주변 환경 청결 기록 삭제 */
    @ApiOperation(value="주변 환경 청결 기록 삭제")
    @ApiImplicitParam(name = "id", value = "게시글 아이디")
    @DeleteMapping("/scleanliness/{id}")
    public ResponseEntity<?> deleteSurroundingCleanliness(@PathVariable("id") Long id){
        surroundingCleanlinessService.deleteSurroundingCleanliness(id);
        return ResponseEntity.noContent().build();
    }

    /* 주변 환경 청결 기록 수정 */
    @ApiOperation(value="활동 기록 수정")
    @PutMapping("/scleanliness")
    public Long updateSCleanliness(@RequestBody SurroundingCleanlinessUpdateRequestDto requestDto){
        return surroundingCleanlinessService.updateSCleanliness(requestDto);
    }

    /* 주변 환경 청결 기록 변경 이력 리스트 조회 */
    @ApiOperation(value="활동 기록 변경 이력 리스트 조회")
    @GetMapping("/scleanlinesshists/list/{id}")
    public List<SurroundingCleanlinessHistResponseDto> getSCleanlinessHists(
            @PathVariable("id") Long id) throws Exception{
        return surroundingCleanlinessService.getSCleanlinessHists(id);
    }

    /* 주변 환경 청결 기록 변경 이력 상세 조회 */
    @ApiOperation(value="활동 기록 변경 이력 상세 조회")
    @GetMapping("/scleanlinesshists/{id}/{revNum}")
    public SurroundingCleanlinessHistResponseDto getSCleanlinessHist(
            @PathVariable("id") Long id,
            @PathVariable("revNum") Integer revNum) throws Exception{
        return surroundingCleanlinessService.getSCleanlinessHist(id, revNum);
    }

}
