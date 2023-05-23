package carehalcare.carehalcare.domain.board.meal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealImageRepository extends JpaRepository<MealImage, Long> {
    List<MealImage> findAllByMeal(Meal meal);
}
