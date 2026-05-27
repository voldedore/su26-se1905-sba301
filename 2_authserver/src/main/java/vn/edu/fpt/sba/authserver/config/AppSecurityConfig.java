package vn.edu.fpt.sba.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import vn.edu.fpt.sba.authserver.repository.UserRepository;

import java.security.Principal;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
    // Khai báo 1 AuthServer (và các endpoints có liên quan).
    @Bean
    SecurityFilterChain authorizationServerFilterChain(HttpSecurity http) throws Exception {
        // Mở các endpoint mặc định theo flow của OAuth2
        // /authorize , /token , /userinfo...
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        // Bật OpenID Connect (OIDC)
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(Customizer.withDefaults());
        return http.formLogin(Customizer.withDefaults()).build();
    }

    // Nếu request nào không thuộc Auth Server thì login
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    // VD them thông tin bổ sung vào trong JWT
    // Userinfo username/email/dob..
    // ROLE
    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer(UserRepository userRepository) {
        return context -> {
            // KT neu dang la access_token
            if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
                var principal = context.getPrincipal();
                var authorities = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
                var user = userRepository.findByUsername(principal.getName());
                context.getClaims()
                        .claim("roles", authorities)
                        .claim("email", user.map(
                                user1 -> user1.getEmail()
                        ).orElse("no_email"));
            }
        };
    }

    // Dùng thử User in-memory (for testing)
//    @Bean
//    UserDetailsService userDetailsService() {
//        // {noop} ghi chú cho biêt không cần Encoder nào
//        UserDetails user = User.withUsername("admin").password("{noop}Fpt@123").roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(user);
//    }


    // Các trường password, secret, Spring sẽ tự tìm các Bean 'passwordEncoder' để thực hiện mã passwd
//    @Bean
    // PasswordEncoder -> Bcrypt
}
