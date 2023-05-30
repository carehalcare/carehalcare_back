package carehalcare.carehalcare.service.user;

import carehalcare.carehalcare.domain.user.User;
import carehalcare.carehalcare.domain.user.UserRepository;
import carehalcare.carehalcare.dto.user.user.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /* 회원가입 */
    @Transactional
    public User signup(UserSaveRequestDto requestDto){
        Optional<User> user1 = userRepository.findByUserId(requestDto.getUserId());
        if (!user1.isEmpty()){
            throw new IllegalStateException("이미 가입된 사용자 ID입니다.");
        }

        Optional<User> user2 = userRepository.findByPhone(requestDto.getPhone());
        if (!user2.isEmpty()){
            throw new IllegalStateException("이미 가입된 사용자 휴대 전화 번호입니다.");
        }

        return userRepository.save(requestDto.toEntity(
                passwordEncoder.encode(requestDto.getPassword())));
    }

}
