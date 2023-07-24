package carehalcare.carehalcare.controller.board;

import carehalcare.carehalcare.dto.board.meal.*;
import carehalcare.carehalcare.service.board.meal.MealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Api(tags="식사 기록 API")
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class MealController {

    private final MealService mealService;

    /* 식사 기록 저장 */
    @ApiOperation(value="식사 기록 저장")
    @PostMapping("/meals")
    public Long saveMeal(MealImageVO mealImageVO) throws IOException{
        MealSaveRequestDto requestDto
                = MealSaveRequestDto.builder()
                .userId(mealImageVO.getUserId())
                .puserId(mealImageVO.getPuserId())
                .content(mealImageVO.getContent())
                .build();

        return mealService.saveMeal(requestDto, mealImageVO.getImages());
    }

    /* 식사 기록 리스트 조회 */
    @ApiOperation(value="식사 기록 리스트 조회")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "uid", value = "간병인 아이디"),
                    @ApiImplicitParam(name = "puid", value = "보호자 아이디")}
    )
    @GetMapping("/meals/list/{uid}/{puid}")
    public List<MealResponseDto> mealList(
            @PathVariable("uid") String userId,
            @PathVariable("puid") String puserId)throws Exception{
        return mealService.getMealList(userId, puserId);
    }

    /* 식사 기록 상세 조회 */
    @ApiOperation(value="식사 기록 상세 조회")
    @ApiImplicitParam(name = "id", value = "게시글 아이디")
    @GetMapping("/meals/{id}")
    public MealResponseDto mealFindById(@PathVariable("id") Long id) throws Exception{
        return mealService.findById(id);
    }

    /* 식사 기록 삭제 */
    @ApiOperation(value="식사 기록 삭제")
    @ApiImplicitParam(name = "id", value = "게시글 아이디")
    @DeleteMapping("/meals/{id}")
    public ResponseEntity<?> deleteMeal(@PathVariable("id") Long id) throws Exception{
        mealService.deleteMeal(id);
        return ResponseEntity.noContent().build();
    }

    /* 식사 기록 수정 */
    @ApiOperation(value="식사 기록 수정")
    @PutMapping("/meals")
    public Long updateMeal(@RequestBody MealUpdateRequestDto requestDto) throws IOException{
        return mealService.updateMeal(requestDto);
    }

    /* 식사 기록 변경 이력 리스트 조회 */
    @ApiOperation(value="식사 기록 변경 이력 리스트 조회")
    @GetMapping("/mealhists/list/{id}")
    public List<MealHistResponseDto> getMealHists(
            @PathVariable("id") Long id) throws Exception{
        return mealService.getMealHists(id);
    }

    /* 식사 기록 변경 이력 상세 조회 */
    @ApiOperation(value="식사 기록 변경 이력 상세 조회")
    @GetMapping("/mealhists/{id}/{revNum}")
    public MealHistResponseDto getMealHist(
            @PathVariable("id") Long id,
            @PathVariable("revNum") Integer revNum) throws Exception{
        return mealService.getMealHist(id, revNum);
    }

}
