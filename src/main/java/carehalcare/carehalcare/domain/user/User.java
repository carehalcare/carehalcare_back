package carehalcare.carehalcare.domain.user;

import carehalcare.carehalcare.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Table(name = "USER_TABLE")
@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_table_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    private String password;

    @Column(nullable = false)
    private String username;

    private String birthDate;

    @Column(nullable = false, unique = true)
    private String phone;

    private String sex;

    @Column(nullable = false)
    private Integer code;   // 0: 간병인, 1: 보호자

    private String puserId; //보호자 아이디
    private String cuserId; // 간병인 아이디

    private String fcmToken;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_table_id", referencedColumnName = "user_table_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    @Builder
    public User(String userId, String password, String username, String birthDate,
                String phone, String sex, Integer code){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.birthDate = birthDate;
        this.phone = phone;
        this.sex = sex;
        this.code = code;
    }

    /* 환자(보호자) 등록 */
    public void setPuserId(String puserId) {
        this.puserId = puserId;
    }

    /* 간병인 등록 */
    public void setCuserId(String cuserId){
        this.cuserId = cuserId;
    }

    /* 권한 설정 */
    public void addAuthorities(Set<Authority> authority){
        this.authorities = authority;
    }

    /* fcm 토큰 */
    public User updateFcmToken(String fcmToken){
        this.fcmToken = fcmToken;
        return this;
    }
}
