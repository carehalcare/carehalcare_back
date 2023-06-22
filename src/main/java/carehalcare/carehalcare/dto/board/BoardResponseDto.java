package carehalcare.carehalcare.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String userId;
    private String puserId;
    private String category;
    private LocalDateTime createdDateTime;

    @Builder
    public BoardResponseDto(Long id, String userId, String puserId, String category,
                            LocalDateTime createdDateTime){
        this.id =  id;
        this.userId =  userId;
        this.puserId = puserId;
        this.category = category;
        this.createdDateTime = createdDateTime;
    }

}
