package carehalcare.carehalcare.dto.user.mypage;

import carehalcare.carehalcare.domain.user.User;
import lombok.Getter;

@Getter
public class UserNameRequestDto {
    private String username;

    public UserNameRequestDto(User user){
        this.username = user.getUsername();
    }
}
