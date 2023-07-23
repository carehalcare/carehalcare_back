package carehalcare.carehalcare.service.board;

import carehalcare.carehalcare.domain.board.activity.Activity;
import carehalcare.carehalcare.domain.board.activity.ActivityRepository;
import carehalcare.carehalcare.dto.board.activity.ActivityHistResponseDto;
import carehalcare.carehalcare.dto.board.activity.ActivityResponseDto;
import carehalcare.carehalcare.dto.board.activity.ActivitySaveRequestDto;
import carehalcare.carehalcare.dto.board.activity.ActivityUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final AuditReader auditReader;

    /* 활동 기록 저장 */
    @Transactional
    public Long saveActivity(ActivitySaveRequestDto requestDto){
        return activityRepository.save(requestDto.toEntity()).getId();
    }

    /* 활동 기록 리스트 조회 */
    @Transactional(readOnly = true)
    public List<ActivityResponseDto> getActivityList(
            String userId, String puserId){
        return this.activityRepository
                .findByUserIdAndPuserIdOrderByCreatedDateTimeDesc(userId, puserId);
    }

    /* 활동 기록 상세 조회 */
    @Transactional(readOnly = true)
    public ActivityResponseDto findById(Long id){
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new ActivityResponseDto(activity);
    }

    /* 활동 기록 삭제 */
    @Transactional
    public void deleteActivity(Long id){
        Activity activity = activityRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        activityRepository.delete(activity);
    }

    /* 활동 기록 수정 */
    @Transactional
    public Long updateActivity(ActivityUpdateRequestDto requestDto){
        Activity activity = activityRepository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+requestDto.getId()));
        return activity.updateActivity(
                requestDto.getRehabilitation(),
                requestDto.getWalkingAssistance(),
                requestDto.getPosition()).getId();
    }

    /* 활동 기록 변경 이력 리스트 조회 */
    @Transactional(readOnly = true)
    public List<ActivityHistResponseDto> getActivityHists(Long id){
        //결과는 Object []로 나오고, entity, revision entity, revision type이 들어 있음.
        List<Object[]> hists = auditReader.createQuery()
                .forRevisionsOfEntity(Activity.class, false, false)
                .add(AuditEntity.property("id").eq(id))
                .add(AuditEntity.revisionType().eq(RevisionType.MOD))
                .addOrder(AuditEntity.property("modifiedDateTime").desc())
                .getResultList();

        /*for(Object[] i : hists){
            System.out.println(i[0]);System.out.println(i[1]);System.out.println(i[2]);
        }*/

        return hists.stream().map(history -> new ActivityHistResponseDto(
                        (Activity) history[0], (DefaultRevisionEntity) history[1]))
                    .collect(Collectors.toList());
    }

    /* 활동 기록 변경 이력 상세 조회 */
    @Transactional(readOnly = true)
    public ActivityHistResponseDto getActivityHist(Long id, Integer revNum){
        Object activityHist = auditReader.createQuery()
                .forRevisionsOfEntity(Activity.class, true, false)
                .add(AuditEntity.property("id").eq(id))
                .add(AuditEntity.revisionNumber().eq(revNum))
                .getSingleResult();

        return new ActivityHistResponseDto((Activity) activityHist);
    }

}
