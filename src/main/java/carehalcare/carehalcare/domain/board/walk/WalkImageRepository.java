package carehalcare.carehalcare.domain.board.walk;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalkImageRepository extends JpaRepository<WalkImage, Long> {
    List<WalkImage> findAllByWalk(Walk walk);
}
