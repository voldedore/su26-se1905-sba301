package vn.edu.fpt.sba.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Boolean isDisabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(Integer id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.isDisabled = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
