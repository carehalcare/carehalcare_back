package carehalcare.carehalcare.controller.patient;

import carehalcare.carehalcare.domain.user.User;
import carehalcare.carehalcare.dto.patient.PatientSetRequestDto;
import carehalcare.carehalcare.service.patient.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
public class PatientController {
    private final PatientService patientService;

    /* 환자(보호자) 아이디 조회 */
    @GetMapping("/patients")
    public User findByUserId(@RequestParam String userId) throws Exception{
        return patientService.findByUserId(userId);
    }

    /* 환자 등록 */
    @PostMapping("/patients")
    public Long save(@RequestBody PatientSetRequestDto requestDto){
        return patientService.setPatient(requestDto);
    }

}

