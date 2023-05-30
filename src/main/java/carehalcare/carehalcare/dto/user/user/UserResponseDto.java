package carehalcare.carehalcare.dto.user.user;

import carehalcare.carehalcare.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private Long id;
    private String userId;
    private String password;
    private String username;
    private String birthDate;
    private String phone;
    private String sex;
    private Integer code;
    private String puserId;
    private String cuserId;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;

    public UserResponseDto(User user){
        this.id= user.getId();
        this.userId=user.getUserId();
        this.password=user.getPassword();
        this.username=user.getUsername();
        this.birthDate=user.getBirthDate();
        this.phone=user.getPhone();
        this.sex=user.getSex();
        this.code=user.getCode();
        this.puserId=user.getPuserId();
        this.cuserId=user.getCuserId();
        this.createdDateTime=user.getCreatedDateTime();
        this.modifiedDateTime=user.getModifiedDateTime();
    }
}
