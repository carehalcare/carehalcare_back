package carehalcare.carehalcare.controller.board;

import carehalcare.carehalcare.dto.board.BoardResponseDto;
import carehalcare.carehalcare.service.board.BoardService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags="카테고리 전체 조회 관련 API")
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;

    /* 카테고리 전체 조회 */
    @GetMapping("/boards/{uid}/{puid}")
    public List<BoardResponseDto> getBoardList(
            @PathVariable("uid") String userId,
            @PathVariable("puid") String puserId
    ) throws Exception{
        return boardService.getBoardList(userId, puserId);
    }
}
