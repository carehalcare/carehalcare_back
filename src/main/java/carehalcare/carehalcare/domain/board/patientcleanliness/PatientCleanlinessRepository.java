package carehalcare.carehalcare.domain.board.patientcleanliness;


import carehalcare.carehalcare.dto.board.patientcleanliness.PatientCleanlinessResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PatientCleanlinessRepository extends JpaRepository<PatientCleanliness, Long> {
    List<PatientCleanlinessResponseDto> findByUserIdAndPuserIdOrderByCreatedDateDesc(String userId, String puserId);
}
