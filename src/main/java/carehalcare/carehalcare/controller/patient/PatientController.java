package carehalcare.carehalcare.controller.patient;

import carehalcare.carehalcare.domain.user.User;
import carehalcare.carehalcare.dto.patient.PatientInfoResponseDto;
import carehalcare.carehalcare.dto.patient.PatientSetRequestDto;
import carehalcare.carehalcare.service.patient.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags="환자(보호자) 등록 / 정보 조회 API")
@RequiredArgsConstructor
@RestController
public class PatientController {
    private final PatientService patientService;

    /* 환자(보호자) 아이디 조회 */
    @ApiOperation(value="환자(보호자) 아이디 조회", notes="userId는 검색할 보호자 아이디")
    @GetMapping(value="/patients", produces="application/json; charset=UTF-8")
    public User findByUserId(@RequestParam String userId) throws Exception{
        return patientService.findByUserId(userId);
    }

    /* 환자 등록 */
    @ApiOperation(value="환자(보호자) 등록", notes="userId: 간병인 아이디, puserId: 보호자 아이디")
    @PostMapping("/patients")
    public Long save(@RequestBody PatientSetRequestDto requestDto){
        return patientService.setPatient(requestDto);
    }

    /* 환자 정보 조회 */
    @ApiOperation(value="환자 정보 조회", notes="puid: 보호자 아이디")
    @GetMapping(value="/patients/info/{puid}", produces="application/json; charset=UTF-8")
    public PatientInfoResponseDto patientInfo(@PathVariable("puid") String puserId)
            throws Exception{
        return patientService.getPatientInfo(puserId);
    }

}

