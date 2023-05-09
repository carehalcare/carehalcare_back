package carehalcare.carehalcare.controller.user;

import carehalcare.carehalcare.domain.user.User;
import carehalcare.carehalcare.dto.user.PatientInfoResponseDto;
import carehalcare.carehalcare.dto.user.PatientSetRequestDto;
import carehalcare.carehalcare.service.user.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags="[간병인용] 환자(보호자) 등록 / 정보 조회 API")
@RequiredArgsConstructor
@RestController
public class PatientController {
    private final PatientService patientService;

    /* 환자(보호자) 아이디 조회 */
    @ApiOperation(value="환자(보호자) 아이디 조회", notes="userId는 검색할 보호자 아이디")
    @GetMapping("/patients")
    public User findByUserId(@RequestParam String userId) throws Exception{
        return patientService.findByUserId(userId);
    }

    /* 환자 등록 */
    @ApiOperation(value="환자(보호자) 등록", notes="userId: 간병인 아이디, puserId: 보호자 아이디")
    @PostMapping("/patients")
    public Long setPatient(@RequestBody PatientSetRequestDto requestDto){
        return patientService.setPatient(requestDto);
    }

    /* 환자 정보 조회 */
    @ApiOperation(value="환자 정보 조회", notes="puid: 보호자 아이디")
    @GetMapping(value="/patients/info/{puid}")
    public PatientInfoResponseDto getpatientInfo(@PathVariable("puid") String puserId)
            throws Exception{
        return patientService.getPatientInfo(puserId);
    }

}

