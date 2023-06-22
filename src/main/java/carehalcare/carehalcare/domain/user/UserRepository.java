package carehalcare.carehalcare.domain.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
    Optional<User> findByPhone(String phone);
    //Optional<User> findByUserIdAndPassword(String userId, String password);

    // Eager 조회로 authorities 정보를 같이 가져오게 됨
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByUserId(String userId);

    Optional<User> findByPuserId(String userId);
}
