package carehalcare.carehalcare.domain.board.meal;

import carehalcare.carehalcare.dto.board.meal.MealResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<MealResponseDto> findByUserIdAndPuserIdOrderByCreatedDateDesc(String userId, String puserId);
}
