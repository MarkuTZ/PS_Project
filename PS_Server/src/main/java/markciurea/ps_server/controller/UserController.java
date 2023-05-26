package markciurea.ps_server.controller;

import lombok.RequiredArgsConstructor;
import markciurea.ps_server.model.dto.userDto.UserLoginDTO;
import markciurea.ps_server.model.dto.userDto.UserShort;
import markciurea.ps_server.model.user.User;
import markciurea.ps_server.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @PostMapping("/login")
    public User login(@RequestBody UserLoginDTO body) {
        return service.login(body);
    }

    @PostMapping
    public User createUser(@RequestBody UserShort newUser) {
        return service.createUser(newUser);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return service.getUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public User deleteUserById(@PathVariable Long userId) {
        return service.deleteUserById(userId);
    }

    @PatchMapping
    public User updateUser(@RequestBody UserShort updatedUser) {
        return service.updateUser(updatedUser);
    }

}
