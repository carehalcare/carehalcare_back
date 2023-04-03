package carehalcare.carehalcare.controller.notice;

import carehalcare.carehalcare.dto.notice.NoticeListResponseDto;
import carehalcare.carehalcare.service.notice.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags="공지사항 API")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
public class NoticeController {

    private final NoticeService noticeService;

    /* 공지사항 리스트 조회 */
    @ApiOperation(value="공지사항 리스트 조회", notes="puid: 환자(보호자) 아이디")
    @GetMapping("/notices/list/{puid}")
    public List<NoticeListResponseDto> list(
            @PathVariable("puid") String puserId)throws Exception{
        return noticeService.getList(puserId);
    }

}
