package carehalcare.carehalcare.controller.user;

import carehalcare.carehalcare.domain.user.User;
import carehalcare.carehalcare.dto.user.user.LoginDto;
import carehalcare.carehalcare.dto.user.user.TokenDto;
import carehalcare.carehalcare.dto.user.user.UserResponseDto;
import carehalcare.carehalcare.dto.user.user.UserSaveRequestDto;
import carehalcare.carehalcare.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="회원가입 / 로그인 API")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    /* 회원 가입 */
    @ApiOperation(value="회원 가입")
    @PostMapping("/signup")
    public UserResponseDto signup(@RequestBody UserSaveRequestDto requestDto){
        return userService.signup(requestDto);
    }

    /* 로그인 */
    @ApiOperation(value="로그인")
    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }
}
