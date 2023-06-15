package carehalcare.carehalcare.service.user;

import carehalcare.carehalcare.domain.user.User;
import carehalcare.carehalcare.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userRepository.findOneWithAuthoritiesByUserId(userId)
                .map(this::createUser)
                .orElseThrow(() -> new UsernameNotFoundException(userId + "유저를 찾을 수 없습니다."));

    }

    // 해당하는 User의 데이터가 존재하면, userdetails.User 객체로 만들어 리턴
    private org.springframework.security.core.userdetails.User createUser(User user){
        //System.out.println("createUser: "+ user.getUsername() + user.getPassword());
        List<GrantedAuthority> grantedAuthorities
                = user.getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());

        System.out.println("grantedAuthorities: "+ grantedAuthorities);

        return new org.springframework.security.core.userdetails.User(
                user.getUserId(), user.getPassword(), grantedAuthorities
        );

    }

}
