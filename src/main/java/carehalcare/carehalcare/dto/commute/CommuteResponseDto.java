package carehalcare.carehalcare.dto.commute;

import carehalcare.carehalcare.domain.commute.Commute;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommuteResponseDto {
    private Long id;
    private String userId;
    private String puserId;
    private String category;
    private String date;
    private String time;
    private LocalDateTime createdDateTime;

    public CommuteResponseDto(Commute commute){
        this.id=commute.getId();
        this.userId= commute.getUserId();
        this.puserId= commute.getPuserId();
        this.category=commute.getCategory();
        this.date=commute.getDate();
        this.time=commute.getTime();
        this.createdDateTime=commute.getCreatedDateTime();
    }
}
