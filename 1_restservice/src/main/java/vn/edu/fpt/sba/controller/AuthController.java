package vn.edu.fpt.sba.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.sba.util.JwtUtil;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
// Sẽ phải hứng đường dẫn vd /api/auth
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    /*
    Body:
    {
        "username": "admin",
        "password": "Fpt@123
        }
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest req) {
        // AuthManager -> login (authenticate)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password())
        );

        // Neu ok -> Sinh ra jwt va gui ve
        String token = jwtUtil.generate(req.username());
        return ResponseEntity.ok(Map.of("access_token", token, "token_type", "Bearer"));

    }


}

record LoginRequest(String username, String password) {
}
