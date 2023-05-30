package carehalcare.carehalcare.dto.user.user;

import carehalcare.carehalcare.domain.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    @ApiModelProperty(name  = "사용자 id", example = "userid1")
    private String userId;

    @ApiModelProperty(name  = "사용자 pw", example = "몇 자 이상인지 프론트에서 정하고 체크")
    private String password;

    @ApiModelProperty(name  = "사용자 이름", example = "이주연")
    private String username;

    @ApiModelProperty(name  = "사용자 생년월일", example = "1998-01-15")
    private String birthDate;

    @ApiModelProperty(name  = "사용자 휴대전화번호", example = "010-0000-0000")
    private String phone;

    @ApiModelProperty(name  = "사용자 성별", example = "M")
    private String sex;

    @ApiModelProperty(name  = "사용자 구부", example = "0은 간병인, 1은 보호자(환자)")
    private Integer code;

    @Builder
    public UserSaveRequestDto(String userId, String password,
                              String username, String birthDate, String phone,
                              String sex, Integer code){
        this.userId=userId;
        this.password=password;
        this.username=username;
        this.birthDate=birthDate;
        this.phone=phone;
        this.sex=sex;
        this.code=code;
    }

    public User toEntity(String encodedpw){

        return User.builder()
                .userId(userId)
                .password(encodedpw)
                .username(username)
                .birthDate(birthDate)
                .phone(phone)
                .sex(sex)
                .code(code)
                .build();
    }
}
