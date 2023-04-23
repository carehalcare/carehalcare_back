package carehalcare.carehalcare.controller.board;

import carehalcare.carehalcare.dto.board.administraion.AdministrationResponseDto;
import carehalcare.carehalcare.dto.board.administraion.AdministrationSaveRequestDto;
import carehalcare.carehalcare.service.board.AdministrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="투약 기록 API")
@RequiredArgsConstructor
@RestController
public class AdministrationController {

    private final AdministrationService administrationService;

    /* 투약 기록 저장 */
    @ApiOperation(value="투약 기록 저장", notes="puserId는 보호자 아이디, userId는 간병인 아이디")
    @PostMapping("/administrations")
    public Long save(@RequestBody AdministrationSaveRequestDto requestDto){
        return administrationService.save(requestDto);
    }

    /* 투약 기록 리스트 조회 */
    @ApiOperation(value="투약 기록 리스트 조회")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "uid", value = "간병인 아이디"),
                    @ApiImplicitParam(name = "puid", value = "보호자 아이디")}
    )
    @GetMapping(value="/administrations/list/{uid}/{puid}", produces="application/json; charset=UTF-8")
    public List<AdministrationResponseDto> list(
            @PathVariable("uid") String userId,
            @PathVariable("puid") String puserId) throws Exception{
        return administrationService.getList(userId, puserId);
    }

    /* 투약 기록 상세 조회 */
    @ApiOperation(value="투약 기록 상세 조회")
    @ApiImplicitParam(name = "id", value = "게시글 아이디")
    @GetMapping(value="/administrations/{id}", produces="application/json; charset=UTF-8")
    public AdministrationResponseDto findById(
            @PathVariable("id") Long id) throws Exception{
        return administrationService.findById(id);
    }

    /* 투약 기록 삭제 */
    @ApiOperation(value="투약 기록 삭제")
    @ApiImplicitParam(name = "id", value = "게시글 아이디")
    @DeleteMapping("/administrations/{id}")
    public ResponseEntity<?> deleteAdministration(@PathVariable("id") Long id){
        administrationService.deleteAdministration(id);
        return ResponseEntity.noContent().build();
    }
}
