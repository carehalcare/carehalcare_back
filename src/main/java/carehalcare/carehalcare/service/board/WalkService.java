package carehalcare.carehalcare.service.board;

import carehalcare.carehalcare.domain.board.walk.*;
import carehalcare.carehalcare.dto.board.walk.WalkResponseDto;
import carehalcare.carehalcare.dto.board.walk.WalkSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WalkService {
    private final WalkRepository walkRepository;
    private final WalkImageRepository walkImageRepository;
    private final WalkFileHandler walkFileHandler;

    /* 산책 기록 저장 */
    @Transactional
    public Long saveWalk(WalkSaveRequestDto requestDto, List<MultipartFile> images) throws IOException{
        List<WalkImage> walkImages = walkFileHandler.storeFiles(images);
        Walk walk = requestDto.toEntity();

        for(WalkImage image:walkImages){
            walk.addWalkImage(walkImageRepository.save(image));
            image.setWalk(walk);
        }
        return walkRepository.save(walk).getId();
    }

    /* 산책 기록 리스트 조회 */
    @Transactional(readOnly = true)
    public List<WalkResponseDto> getWalkList(
            String userId, String puserId){
        return this.walkRepository.findByUserIdAndPuserIdOrderByCreatedDateTimeDesc(userId, puserId);
    }

    /* 산책 기록 상세 조회 */
    @Transactional(readOnly = true)
    public WalkResponseDto findById(Long id){
        Walk walk = walkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new WalkResponseDto(walk);
    }

    /* 산책 기록 삭제 */
    @Transactional
    public void deleteWalk(Long id){
        Walk walk = walkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        walkRepository.delete(walk);
    }
}
