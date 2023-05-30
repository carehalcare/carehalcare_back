package carehalcare.carehalcare.controller.user;

import carehalcare.carehalcare.domain.user.User;
import carehalcare.carehalcare.dto.user.user.UserSaveRequestDto;
import carehalcare.carehalcare.service.user.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="회원가입 / 로그인 API")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public User signup(@RequestBody UserSaveRequestDto requestDto){
        return userService.signup(requestDto);
    }
}
