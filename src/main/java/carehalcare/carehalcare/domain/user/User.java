package carehalcare.carehalcare.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "USER_TABLE")
@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Integer code; // 0: 간병인, 1: 보호자

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

    // update 메서드
    // public Users update(){return this;}
}
