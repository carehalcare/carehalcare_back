package carehalcare.carehalcare.controller.patient;

import carehalcare.carehalcare.domain.user.User;
import carehalcare.carehalcare.dto.patient.PatientSetRequestDto;
import carehalcare.carehalcare.service.patient.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags="환자(보호자) 등록 API")
@CrossOrigin(origins = "http://localhost:3000")
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
    public Long save(@RequestBody PatientSetRequestDto requestDto){
        return patientService.setPatient(requestDto);
    }

}

