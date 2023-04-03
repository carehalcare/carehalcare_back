package carehalcare.carehalcare.dto.patient;

import carehalcare.carehalcare.domain.board.administration.Administration;
import carehalcare.carehalcare.domain.user.PatientInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PatientInfoResponseDto {
    private Long id;
    private String userId;
    private String pname;
    private String psex;
    private String pBirthDate;
    private String disease;
    private String hospital;
    private String medicine;
    private String remark;
    private LocalDateTime createdDate;

    public PatientInfoResponseDto(PatientInfo entity){
        this.id=entity.getId();
        this.userId=entity.getUserId();
        this.pname=entity.getPname();
        this.psex=entity.getPsex();
        this.pBirthDate=entity.getPBirthDate();
        this.disease=entity.getDisease();
        this.hospital=entity.getHospital();
        this.medicine=entity.getMedicine();
        this.remark=entity.getRemark();
        this.createdDate=entity.getCreatedDate();
    }
}
