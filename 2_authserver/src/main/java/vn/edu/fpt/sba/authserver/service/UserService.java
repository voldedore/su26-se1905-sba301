package vn.edu.fpt.sba.authserver.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.edu.fpt.sba.authserver.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        var authorities = user.getRoles().stream().map(
                role -> new SimpleGrantedAuthority("ROLE_" + role.getName())
        ).toList();
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .disabled(user.getDisabled())
                .build();
    }
}
