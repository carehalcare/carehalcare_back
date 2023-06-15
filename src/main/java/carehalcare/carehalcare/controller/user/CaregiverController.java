package carehalcare.carehalcare.controller.user;

import carehalcare.carehalcare.domain.user.User;
import carehalcare.carehalcare.dto.user.CaregiverSetRequestDto;
import carehalcare.carehalcare.service.user.CaregiverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags="간병인 등록 / 정보 조회 API")
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class CaregiverController {
    private final CaregiverService caregiverService;

    /* 간병인 아이디 조회 */
    @ApiOperation(value="간병인 아이디 조회", notes="userId는 검색할 간병인 아이디")
    @GetMapping("/caregivers")
    public User findByUserId(@RequestParam String userId) throws Exception{
        return caregiverService.findByUserId(userId);
    }

    /* 간병인 등록 */
    @ApiOperation(value="간병인 등록", notes="userId: 보호자 아이디, cuserId: 간병인 아이디")
    @PostMapping("/caregivers")
    public Long setCaregiver(@RequestBody CaregiverSetRequestDto requestDto){
        return caregiverService.setCaregiver(requestDto);
    }

}
