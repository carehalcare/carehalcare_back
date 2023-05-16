package carehalcare.carehalcare.service.board;

import carehalcare.carehalcare.domain.board.bowelmovement.BowelMovement;
import carehalcare.carehalcare.domain.board.bowelmovement.BowelMovementRepository;
import carehalcare.carehalcare.dto.board.bowelmovement.BowelMovementResponseDto;
import carehalcare.carehalcare.dto.board.bowelmovement.BowelMovementSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BowelMovementService {
    private final BowelMovementRepository bowelMovementRepository;

    /* 배변 기록 저장 */
    @Transactional
    public Long saveBowelMovement(BowelMovementSaveRequestDto requestDto){
        return bowelMovementRepository.save(requestDto.toEntity()).getId();
    }

    /* 배변 기록 리스트 조회 */
    @Transactional(readOnly = true)
    public List<BowelMovementResponseDto> getBowelMovementList(
            String userId, String puserId){
        return this.bowelMovementRepository
                .findByUserIdAndPuserIdOrderByCreatedDateTimeDesc(userId, puserId);
    }

    /* 배변 기록 상세 조회 */
    @Transactional(readOnly = true)
    public BowelMovementResponseDto findById(Long id){
        BowelMovement bowelMovement = bowelMovementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new BowelMovementResponseDto(bowelMovement);
    }

    /* 배변 기록 삭제 */
    @Transactional
    public void deleteBowelMovement(Long id){
        BowelMovement bowelMovement = bowelMovementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        bowelMovementRepository.delete(bowelMovement);
    }
}
