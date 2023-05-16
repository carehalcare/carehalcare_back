package carehalcare.carehalcare.service.board;

import carehalcare.carehalcare.domain.board.administration.Administration;
import carehalcare.carehalcare.domain.board.administration.AdministrationRepository;
import carehalcare.carehalcare.dto.board.administraion.AdministrationResponseDto;
import carehalcare.carehalcare.dto.board.administraion.AdministrationSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdministrationService {
    private final AdministrationRepository administrationRepository;

    /* 투약 기록 저장 */
    @Transactional
    public Long save(AdministrationSaveRequestDto requestDto){
        return administrationRepository.save(requestDto.toEntity())
                .getId();
    }

    /* 투약 기록 리스트 조회 */
    @Transactional(readOnly = true)
    public List<AdministrationResponseDto> getList(
            String userId, String puserId){
        return this.administrationRepository.findByUserIdAndPuserIdOrderByCreatedDateTimeDesc(userId,puserId);
    }

    /* 투약 기록 상세 조회 */
    @Transactional(readOnly = true)
    public AdministrationResponseDto findById(Long id){
        Administration entity = administrationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new AdministrationResponseDto(entity);
    }

    /* 투약 기록 삭제 */
    @Transactional
    public void deleteAdministration(Long id){
        Administration administration = administrationRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        administrationRepository.delete(administration);
    }
}
