package carehalcare.carehalcare.controller.board;

import carehalcare.carehalcare.dto.board.walk.WalkImageVO;
import carehalcare.carehalcare.dto.board.walk.WalkResponseDto;
import carehalcare.carehalcare.dto.board.walk.WalkSaveRequestDto;
import carehalcare.carehalcare.service.board.walk.WalkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Api(tags="산책 기록 API")
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class WalkController {

    private final WalkService walkService;

    /* 산책 기록 저장 */
    @ApiOperation(value="산책 기록 저장")
    @PostMapping("/walks")
    public Long saveWalk(WalkImageVO walkImageVO) throws IOException {
        WalkSaveRequestDto requestDto
                = WalkSaveRequestDto.builder()
                .userId(walkImageVO.getUserId())
                .puserId(walkImageVO.getPuserId())
                .build();
        return walkService.saveWalk(requestDto, walkImageVO.getImages());
    }

    /* 산책 기록 리스트 조회 */
    @ApiOperation(value="산책 기록 리스트 조회")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "uid", value = "간병인 아이디"),
                    @ApiImplicitParam(name = "puid", value = "보호자 아이디")}
    )
    @GetMapping("/walks/list/{uid}/{puid}")
    public List<WalkResponseDto> walkList(
            @PathVariable("uid") String userId,
            @PathVariable("puid") String puserId)throws Exception{
        return walkService.getWalkList(userId, puserId);
    }

    /* 산책 기록 상세 조회 */
    @ApiOperation(value="산책 기록 상세 조회")
    @ApiImplicitParam(name = "id", value = "게시글 아이디")
    @GetMapping("/walks/{id}")
    public WalkResponseDto walkFindById(@PathVariable("id") Long id) throws Exception{
        return walkService.findById(id);
    }

    /* 산책 기록 삭제 */
    @ApiOperation(value="산책 기록 삭제")
    @ApiImplicitParam(name = "id", value = "게시글 아이디")
    @DeleteMapping("/walks/{id}")
    public ResponseEntity<?> deleteWalk(@PathVariable("id") Long id) throws Exception{
        walkService.deleteWalk(id);
        return ResponseEntity.noContent().build();
    }
}
