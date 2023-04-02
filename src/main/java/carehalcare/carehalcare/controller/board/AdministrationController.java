package carehalcare.carehalcare.controller.board;

import carehalcare.carehalcare.dto.board.administraion.AdministrationListResponseRequestDto;
import carehalcare.carehalcare.dto.board.administraion.AdministrationResponseRequestDto;
import carehalcare.carehalcare.dto.board.administraion.AdministrationSaveRequestDto;
import carehalcare.carehalcare.service.board.AdministrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
public class AdministrationController {

    private final AdministrationService administrationService;

    /* 투약 기록 저장 */
    @PostMapping("/administrations")
    public Long save(@RequestBody AdministrationSaveRequestDto requestDto){
        return administrationService.save(requestDto);
    }

    /* 투약 기록 리스트 조회 */
    @GetMapping("/administrations/list/{uid}/{puid}")
    public List<AdministrationListResponseRequestDto> list(
            @PathVariable("uid") String userId,
            @PathVariable("puid") String puserId) throws Exception{
        return administrationService.getList(userId, puserId);
    }

    /* 투약 기록 상세 조회 */
    @GetMapping("/administrations/{id}")
    public AdministrationResponseRequestDto findById(
            @PathVariable("id") Long id) throws Exception{
        return administrationService.findById(id);
    }
}
