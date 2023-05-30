package carehalcare.carehalcare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;



@Configuration
//@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/h2-console/**", "/favicon.ico").permitAll()
                .antMatchers("/login", "/signup").permitAll()
                //.anyRequest().authenticated();

                .and()
                /* REST API 사용 시 csrf 예외 처리 */
                .csrf().ignoringAntMatchers("/h2-console/**", "/favicon.ico")
                .ignoringAntMatchers("/login", "/signup")


                .and() // h2-console 설정
                .headers()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
