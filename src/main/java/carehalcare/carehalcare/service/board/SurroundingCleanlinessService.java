package carehalcare.carehalcare.service.board;

import carehalcare.carehalcare.domain.board.surroundingcleanliness.SurroundingCleanliness;
import carehalcare.carehalcare.domain.board.surroundingcleanliness.SurroundingCleanlinessRepository;
import carehalcare.carehalcare.dto.board.surroundingcleanliness.SurroundingCleanlinessResponseDto;
import carehalcare.carehalcare.dto.board.surroundingcleanliness.SurroundingCleanlinessSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SurroundingCleanlinessService {

    private final SurroundingCleanlinessRepository surroundingCleanlinessRepository;

    /* 주변 환경 청결 기록 저장 */
    @Transactional
    public Long saveSurroundingCleanliness(SurroundingCleanlinessSaveRequestDto requestDto){
        return surroundingCleanlinessRepository.save(requestDto.toEntity()).getId();
    }

    /* 주변 환경 청결 기록 리스트 조회 */
    @Transactional(readOnly = true)
    public List<SurroundingCleanlinessResponseDto> getSurroundingCleanlinessList(
            String userId, String puserId){
        return this.surroundingCleanlinessRepository
                .findByUserIdAndPuserIdOrderByCreatedDateDesc(userId, puserId);
    }

    /* 주변 환경 청결 기록 상세 조회 */
    @Transactional(readOnly = true)
    public SurroundingCleanlinessResponseDto findById(Long id){
        SurroundingCleanliness surroundingCleanliness = surroundingCleanlinessRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new SurroundingCleanlinessResponseDto(surroundingCleanliness);
    }

    /* 주변 환경 청결 기록 삭제 */
    @Transactional
    public void deleteSurroundingCleanliness(Long id){
        SurroundingCleanliness surroundingCleanliness = surroundingCleanlinessRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        surroundingCleanlinessRepository.delete(surroundingCleanliness);
    }
}
