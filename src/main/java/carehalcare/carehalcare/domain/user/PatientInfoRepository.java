package carehalcare.carehalcare.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientInfoRepository extends JpaRepository<PatientInfo, Long> {
    Optional<PatientInfo> findByUserId(String userId);
}
