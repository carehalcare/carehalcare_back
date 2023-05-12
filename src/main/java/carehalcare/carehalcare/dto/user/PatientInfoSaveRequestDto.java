package carehalcare.carehalcare.dto.user;

import carehalcare.carehalcare.domain.user.PatientInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PatientInfoSaveRequestDto {
    @ApiModelProperty(name  = "보호자 id", example = "userid1")
    private String userId;

    @ApiModelProperty(name  = "환자 이름", example = "이주연")
    private String pname;

    @ApiModelProperty(name  = "환자 성별", example = "M")
    private String psex;

    @ApiModelProperty(name  = "환자 생년월일", example = "1938-01-15")
    private String pbirthDate;

    @ApiModelProperty(name  = "환자 질환", example = "치매 초기")
    private String disease;

    @ApiModelProperty(name  = "환자 담당 병원", example = "순천향병원")
    private String hospital;

    @ApiModelProperty(name  = "환자 복용약", example = "갈란타민, 메만틴")
    private String medicine;

    @ApiModelProperty(name  = "환자 특이사항", example = "잠자리에 들 때 주변 작은 소음에도 민감합니다.")
    private String remark;

    @Builder
    public PatientInfoSaveRequestDto(String userId, String pname, String psex, String pbirthDate,
                                     String disease, String hospital, String medicine, String remark){
        this.userId=userId;
        this.pname=pname;
        this.psex=psex;
        this.pbirthDate=pbirthDate;
        this.disease=disease;
        this.hospital=hospital;
        this.medicine=medicine;
        this.remark=remark;
    }

    public PatientInfo toEntity(){
        return PatientInfo.builder()
                .userId(userId)
                .pname(pname)
                .psex(psex)
                .pbirthDate(pbirthDate)
                .disease(disease)
                .hospital(hospital)
                .medicine(medicine)
                .remark(remark)
                .build();
    }
}
