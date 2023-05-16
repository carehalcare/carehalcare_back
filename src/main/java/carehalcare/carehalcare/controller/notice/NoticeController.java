package carehalcare.carehalcare.controller.notice;

import carehalcare.carehalcare.dto.notice.NoticeResponseDto;
import carehalcare.carehalcare.dto.notice.NoticeSaveRequestDto;
import carehalcare.carehalcare.dto.notice.NoticeUpdateRequestDto;
import carehalcare.carehalcare.service.notice.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="공지사항 API")
@RequiredArgsConstructor
@RestController
public class NoticeController {

    private final NoticeService noticeService;

    /* 공지사항 등록 */
    @ApiOperation(value="공지사항 등록")
    @PostMapping("/notices")
    public Long saveNotice(@RequestBody NoticeSaveRequestDto requestDto){
        return noticeService.saveNotice(requestDto);
    }

    /* 공지사항 리스트 조회 */
    @ApiOperation(value="공지사항 리스트 조회", notes="puid: 환자(보호자) 아이디")
    @GetMapping(value="/notices/list/{puid}")
    public List<NoticeResponseDto> list(
            @PathVariable("puid") String puserId)throws Exception{
        return noticeService.getList(puserId);
    }

    /* 공지사항 상세 조회 */
    @ApiOperation(value="공지사항 상세 조회")
    @GetMapping("/notices/{id}")
    public NoticeResponseDto findById(@PathVariable("id") Long id) throws Exception{
        return noticeService.findById(id);
    }

    /* 공지사항 삭제 */
    @ApiOperation(value="공지사항 삭제")
    @DeleteMapping("/notices/{id}")
    public ResponseEntity<?> deleteNotice(@PathVariable("id") Long id) throws Exception{
        noticeService.deleteNotice(id);
        return ResponseEntity.noContent().build();
    }

    /* 공지사항 수정 */
    @ApiOperation(value="공지사항 수정")
    @PutMapping("/notices")
    public Long updateNotice(@RequestBody NoticeUpdateRequestDto requestDto){
        return noticeService.updateNotice(requestDto);
    }
}
