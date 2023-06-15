package carehalcare.carehalcare.config;

import carehalcare.carehalcare.jwt.JwtAccessDeniedHandler;
import carehalcare.carehalcare.jwt.JwtAuthenticationEntryPoint;
import carehalcare.carehalcare.jwt.JwtFilter;
import carehalcare.carehalcare.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;



@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .authorizeRequests()
                .antMatchers("/h2-console/**", "/favicon.ico").permitAll()
                //.antMatchers("/login", "/signup", "/**").permitAll()
                .antMatchers("/login", "/signup", "/users").permitAll()
                .antMatchers("/api/**").hasRole("USER")
                //.anyRequest().hasRole("USER")
                //.anyRequest().authenticated()

                .and()
                /* REST API 사용 시 csrf 예외 처리 */
                .csrf().ignoringAntMatchers("/h2-console/**", "/favicon.ico")
                //.ignoringAntMatchers("/login", "/signup", "/**")
                .ignoringAntMatchers("/login", "/signup", "/users", "/api/**")

                .and()
                .addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .headers() // h2-console 설정
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));

        return http.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }*/

    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, CustomUserDetailsService customUserDetailsService) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(this.passwordEncoder());
    }
     */
}
