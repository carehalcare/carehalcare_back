package carehalcare.carehalcare.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class PatientInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    private String pname;

    private String psex;

    private String pBirthDate;

    private String disease;

    private String hospital;

    private String medicine;

    private String remark;

    @Builder
    public PatientInfo(String userId, String pname, String psex, String pBirthDate,
                String disease, String hospital, String medicine, String remark){
        this.userId = userId;
        this.pname = pname;
        this.psex = psex;
        this.pBirthDate = pBirthDate;
        this.disease = disease;
        this.hospital = hospital;
        this.medicine = medicine;
        this.remark = remark;
    }

}
