package carehalcare.carehalcare.controller;

import carehalcare.carehalcare.dto.commute.CommuteResponseDto;
import carehalcare.carehalcare.dto.commute.CommuteSaveRequestDto;
import carehalcare.carehalcare.service.CommuteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="출퇴근 API")
@RequiredArgsConstructor
@RestController
public class CommuteController {
    private final CommuteService commuteService;

    /* 출퇴근 기록 저장 */
    @ApiOperation(value="출퇴근 기록 저장")
    @PostMapping("/commutes")
    public List<CommuteResponseDto> saveCommute(@RequestBody CommuteSaveRequestDto requestDto){
        return commuteService.saveCommute(requestDto);
    }

    /* 출퇴근 기록 상세 조회 */
    @ApiOperation(value="출퇴근 기록 상세 조회")
    @GetMapping("/commutes/{date}/{uid}/{pid}")
    public List<CommuteResponseDto> getCommute(@PathVariable("date") String date,
                                               @PathVariable("uid") String userId,
                                               @PathVariable("pid") String puserId)throws Exception{
        return commuteService.getCommute(date, userId, puserId);
    }
}
