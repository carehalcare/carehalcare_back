package carehalcare.carehalcare.dto.user;

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
    private String pbirthDate;
    private String disease;
    private String hospital;
    private String medicine;
    private String remark;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;

    public PatientInfoResponseDto(PatientInfo entity){
        this.id=entity.getId();
        this.userId=entity.getUserId();
        this.pname=entity.getPname();
        this.psex=entity.getPsex();
        this.pbirthDate=entity.getPbirthDate();
        this.disease=entity.getDisease();
        this.hospital=entity.getHospital();
        this.medicine=entity.getMedicine();
        this.remark=entity.getRemark();
        this.createdDateTime=entity.getCreatedDateTime();
        this.modifiedDateTime=entity.getModifiedDateTime();
    }
}
