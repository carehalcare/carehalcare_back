package carehalcare.carehalcare.dto.board.walk;

import carehalcare.carehalcare.domain.board.walk.Walk;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class WalkResponseDto {
    private Long id;
    private String userId;
    private String puserId;
    private List<WalkImageResponseDto> images;
    private LocalDateTime createdDate;

    public WalkResponseDto(Walk walk){
        this.id=walk.getId();
        this.userId=walk.getUserId();
        this.puserId=walk.getPuserId();
        this.createdDate=walk.getCreatedDate();
        this.images=walk.getWalkImage()
                .stream()
                .map(WalkImageResponseDto::new)
                .collect(Collectors.toList());
    }
}
