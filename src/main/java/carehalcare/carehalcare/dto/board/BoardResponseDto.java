package carehalcare.carehalcare.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
//@NoArgsConstructor
public class BoardResponseDto<T> {
    private T data;

    /*
    private Long id;
    private String userId;
    private String puserId;

    private String content1;
    private String content2;
    private String content3;

    private String category;
    private LocalDateTime createdDateTime;

    @Builder
    public BoardResponseDto(Long id, String userId, String puserId, String content1,
                            String content2, String content3, String category, LocalDateTime createdDateTime){
        this.id =  id;
        this.userId =  userId;
        this.puserId = puserId;
        this.content1 = content1;
        this.content2 = content2;
        this.content3 = content3;
        this.category = category;
        this.createdDateTime = createdDateTime;
    }*/
}
