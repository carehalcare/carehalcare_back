package carehalcare.carehalcare.domain.user;

import carehalcare.carehalcare.domain.BaseTimeEntity;
import carehalcare.carehalcare.dto.user.PatientInfoUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class PatientInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    private String pname;

    private String psex;

    private String pbirthDate;

    private String disease;

    private String hospital;

    private String medicine;

    @Column(columnDefinition = "TEXT")
    private String remark;

    @Builder
    public PatientInfo(String userId, String pname, String psex, String pbirthDate,
                String disease, String hospital, String medicine, String remark){
        this.userId = userId;
        this.pname = pname;
        this.psex = psex;
        this.pbirthDate = pbirthDate;
        this.disease = disease;
        this.hospital = hospital;
        this.medicine = medicine;
        this.remark = remark;
    }

    public PatientInfo updatePatientInfo(PatientInfoUpdateRequestDto requestDto){
        this.pname = requestDto.getPname();
        this.psex = requestDto.getPsex();
        this.pbirthDate = requestDto.getPbirthDate();
        this.disease = requestDto.getDisease();
        this.hospital = requestDto.getHospital();
        this.medicine = requestDto.getMedicine();
        this.remark = requestDto.getRemark();
        return this;
    }
}
