package carehalcare.carehalcare.controller.board;

import carehalcare.carehalcare.dto.board.patientcleanliness.PatientCleanlinessResponseDto;
import carehalcare.carehalcare.dto.board.patientcleanliness.PatientCleanlinessSaveRequestDto;
import carehalcare.carehalcare.service.board.PatientCleanlinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="환자 청결 기록 API")
@RequiredArgsConstructor
@RestController
public class PatientCleanlinessController {

    private final PatientCleanlinessService patientCleanlinessService;

    /* 환자 청결 기록 저장 */
    @ApiOperation(value="환자 청결 기록 저장", notes="puserId는 보호자 아이디, userId는 간병인 아이디")
    @PostMapping("/pcleanliness")
    public Long savePatientCleanliness(@RequestBody PatientCleanlinessSaveRequestDto requestDto){
        return patientCleanlinessService.savePatientCleanliness(requestDto);
    }

    /* 환자 청결 기록 리스트 조회 */
    @ApiOperation(value="환자 청결 기록 리스트 조회")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "uid", value = "간병인 아이디"),
                    @ApiImplicitParam(name = "puid", value = "보호자 아이디")}
    )
    @GetMapping("/pcleanliness/list/{uid}/{puid}")
    public List<PatientCleanlinessResponseDto> getPatientCleanlinessList(
            @PathVariable("uid") String userId,
            @PathVariable("puid") String puserId) throws Exception{
        return this.patientCleanlinessService.getPatientCleanlinessList(userId, puserId);
    }

    /* 환자 청결 기록 상세 조회 */
    @ApiOperation(value="환자 청결 기록 상세 조회")
    @ApiImplicitParam(name = "id", value = "게시글 아이디")
    @GetMapping("/pcleanliness/{id}")
    public PatientCleanlinessResponseDto findById(
            @PathVariable("id") Long id) throws Exception{
        return patientCleanlinessService.findById(id);
    }

    /* 환자 청결 기록 삭제 */
    @ApiOperation(value="환자 청결 기록 삭제")
    @ApiImplicitParam(name = "id", value = "게시글 아이디")
    @DeleteMapping("/pcleanliness/{id}")
    public ResponseEntity<?> deletePatientCleanliness(@PathVariable("id") Long id){
        patientCleanlinessService.deletePatientCleanliness(id);
        return ResponseEntity.noContent().build();
    }
}
