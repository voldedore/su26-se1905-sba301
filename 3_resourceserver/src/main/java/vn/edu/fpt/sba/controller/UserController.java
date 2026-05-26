package vn.edu.fpt.sba.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.sba.entities.User;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // http://domain/api/users/79

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return new User(id, "John", "john@fpt.com");
    }

    // 1) Lấy danh sách users
    // API: GET /api/users
    // return 1 list cac user
    @GetMapping
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Joghn", "j@fpt.com"));
        users.add(new User(2, "Joghn2", "j2@fpt.com"));
        users.add(new User(3, "Joghn2", "j3@fpt.com"));
        users.add(new User(4, "Joghn4", "j4@fpt.com"));
        return users;
    }

    // 2) Tạo mới 1 user
    // POST /api/users
    // @RequestBody User
    // Return code 201 -> @ResponseStatus
    // sout -> user đã nhận
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody User user) {
        System.out.println("User input: ");
        System.out.println(user);
    }
}
