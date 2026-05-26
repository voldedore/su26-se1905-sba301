package vn.edu.fpt.sba.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/artists/**")
                .authorizeHttpRequests(
                        authorize -> authorize.anyRequest().hasAuthority("SCOPE_artists.read")
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }
    // Tat ca request match chỉ được cấp phép nếu JWT có Authority SCOPE_profile | SCOPE_profile | SCOPE_email
    // VD Token (jwt) có scope profile -> Spring hiểu authority là SCOPE_profile, v.v...
    // VD Token (jwt) có scope artists.read -> Spring hiểu authority là SCOPE_artists.read, v.v...
    // .oauth2ResourceServer() -> bao hieu cho biet day la 1 resource server
    // và dùng JWT để xác thực, dùng các cấu hình mặc định
    // Resource server sẽ validate:
    //   - đọc token
    //   - signature
    //   - exp
    //   - scope
}
