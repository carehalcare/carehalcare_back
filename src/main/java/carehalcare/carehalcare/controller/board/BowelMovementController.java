package carehalcare.carehalcare.controller.board;

import carehalcare.carehalcare.dto.board.bowelmovement.BowelMovementHistResponseDto;
import carehalcare.carehalcare.dto.board.bowelmovement.BowelMovementResponseDto;
import carehalcare.carehalcare.dto.board.bowelmovement.BowelMovementSaveRequestDto;
import carehalcare.carehalcare.dto.board.bowelmovement.BowelMovementUpdateRequestDto;
import carehalcare.carehalcare.service.board.BowelMovementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="배변 기록 API")
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class BowelMovementController {

    private final BowelMovementService bowelMovementService;

    /* 배변 기록 저장 */
    @ApiOperation(value="배변 기록 저장", notes="puserId는 보호자 아이디, userId는 간병인 아이디")
    @PostMapping("/bowelmovements")
    public Long saveBowelMovement(@RequestBody BowelMovementSaveRequestDto requestDto){
        return bowelMovementService.saveBowelMovement(requestDto);
    }

    /* 배변 기록 리스트 조회 */
    @ApiOperation(value="배변 기록 리스트 조회")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "uid", value = "간병인 아이디"),
                    @ApiImplicitParam(name = "puid", value = "보호자 아이디")}
    )
    @GetMapping("/bowelmovements/list/{uid}/{puid}")
    public List<BowelMovementResponseDto> getBowelMovementList(
            @PathVariable("uid") String userId,
            @PathVariable("puid") String puserId) throws Exception{
        return this.bowelMovementService.getBowelMovementList(userId, puserId);
    }

    /* 배변 기록 상세 조회 */
    @ApiOperation(value="배변 기록 상세 조회")
    @ApiImplicitParam(name = "id", value = "게시글 아이디")
    @GetMapping("/bowelmovements/{id}")
    public BowelMovementResponseDto findById(
            @PathVariable("id") Long id) throws Exception{
        return bowelMovementService.findById(id);
    }

    /* 배변 기록 삭제 */
    @ApiOperation(value="배변 기록 삭제")
    @ApiImplicitParam(name = "id", value = "게시글 아이디")
    @DeleteMapping("/bowelmovements/{id}")
    public ResponseEntity<?> deleteBowelMovement(
            @PathVariable("id") Long id){
        bowelMovementService.deleteBowelMovement(id);
        return ResponseEntity.noContent().build();
    }

    /* 배변 기록 수정 */
    @ApiOperation(value="배변 기록 수정")
    @PutMapping("/bowelmovements")
    public Long updateBowelMovement(@RequestBody BowelMovementUpdateRequestDto requestDto){
        return bowelMovementService.updateBowelMovement(requestDto);
    }

    /* 배변 기록 변경 이력 리스트 조회 */
    @ApiOperation(value="배변 기록 변경 이력 리스트 조회")
    @GetMapping("/bowelmovementhists/list/{id}")
    public List<BowelMovementHistResponseDto> getBowelMovementHists(
            @PathVariable("id") Long id) throws Exception{
        return bowelMovementService.getBowelMovementHists(id);
    }

    /* 배변 기록 변경 이력 상세 조회 */
    @ApiOperation(value="배변 기록 변경 이력 상세 조회")
    @GetMapping("/bowelmovementhists/{id}/{revNum}")
    public BowelMovementHistResponseDto getBowelMovementHist(
            @PathVariable("id") Long id,
            @PathVariable("revNum") Integer revNum) throws Exception{
        return bowelMovementService.getBowelMovementHist(id, revNum);
    }

}
