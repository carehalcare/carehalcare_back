package carehalcare.carehalcare.service.user;

import carehalcare.carehalcare.domain.user.Authority;
import carehalcare.carehalcare.domain.user.User;
import carehalcare.carehalcare.domain.user.UserRepository;
import carehalcare.carehalcare.dto.user.user.LoginDto;
import carehalcare.carehalcare.dto.user.user.TokenDto;
import carehalcare.carehalcare.dto.user.user.UserResponseDto;
import carehalcare.carehalcare.dto.user.user.UserSaveRequestDto;
import carehalcare.carehalcare.jwt.JwtFilter;
import carehalcare.carehalcare.jwt.TokenProvider;
import carehalcare.carehalcare.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    /* 회원가입 */
    @Transactional
    public UserResponseDto signup(UserSaveRequestDto requestDto){
        Optional<User> user1 = userRepository.findByUserId(requestDto.getUserId());
        if (!user1.isEmpty()){
            throw new IllegalStateException("이미 가입된 사용자 ID입니다.");
        }

        Optional<User> user2 = userRepository.findByPhone(requestDto.getPhone());
        if (!user2.isEmpty()){
            throw new IllegalStateException("이미 가입된 사용자 휴대 전화 번호입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = requestDto.toEntity(passwordEncoder.encode(requestDto.getPassword()));
        user.addAuthorities(Collections.singleton(authority));

        return new UserResponseDto(userRepository.save(user));
    }

    /* 로그인 */
    @Transactional
    public TokenDto login(LoginDto loginDto){
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginDto.getUserId(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder
                                        .getObject()
                                        .authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        TokenDto tokenDto = tokenProvider.generateToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer "+ tokenDto.getAccessToken());

        return tokenDto;
    }

    /* 사용자 아이디 조회 - 회원가입 시 아이디 중복 여부 확인용 */
    @Transactional(readOnly = true)
    public UserResponseDto findByUserId(String userId){
        Optional<User> user = userRepository.findByUserId(userId);
        if (!user.isEmpty()){
            return new UserResponseDto(user.get());
        } else{
            return null;
        }
    }

}
