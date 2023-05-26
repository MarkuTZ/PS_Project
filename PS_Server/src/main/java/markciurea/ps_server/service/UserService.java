package markciurea.ps_server.service;

import lombok.RequiredArgsConstructor;
import markciurea.ps_server.config.CustomError;
import markciurea.ps_server.model.dto.userDto.UserLoginDTO;
import markciurea.ps_server.model.dto.userDto.UserShort;
import markciurea.ps_server.model.user.ActivityCoordinator;
import markciurea.ps_server.model.user.Administrator;
import markciurea.ps_server.model.user.Employee;
import markciurea.ps_server.model.user.User;
import markciurea.ps_server.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User login(UserLoginDTO userLoginDTO) {
        User user = repository.getUserByEmail(userLoginDTO.getUsername());
        if (user == null) {
            throw new CustomError(HttpStatus.NOT_FOUND, "User not found");
        }
        if (!user.getPassword().equals(userLoginDTO.getPassword())) {
            throw new CustomError(HttpStatus.UNAUTHORIZED, "Wrong credentials");
        }
        return user;
    }

    public User getUser(String email) {
        return repository.getUserByEmail(email);
    }

    public User createUser(UserShort newUser) {
        User toSave = null;
        switch (newUser.getRole()) {
            case ADMINISTRATOR -> toSave = new Administrator(newUser);
            case EMPLOYEE -> toSave = new Employee(newUser);
            case ACTIVITY_COORDINATOR -> toSave = new ActivityCoordinator(newUser);
        }
        return repository.save(toSave);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User deleteUserById(Long userId) {
        User user = repository.findById(userId).orElse(null);
        if (user == null) {
            throw new CustomError(HttpStatus.NOT_FOUND, "User not found!");
        }
        repository.delete(user);
        return user;
    }

    public User getUserById(Long userId) {
        return repository.findById(userId).orElse(null);
    }

    public User updateUser(UserShort updatedUser) {
        User user = repository.findById(updatedUser.getId()).orElse(null);
        if (user == null) {
            return null;
        }
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setPhoneNr(updatedUser.getPhoneNr());
        switch (updatedUser.getRole()) {
            case ADMINISTRATOR -> {
                return repository.save((Administrator) user);
            }
            case EMPLOYEE -> {
                return repository.save((Employee) user);
            }
            case ACTIVITY_COORDINATOR -> {
                return repository.save((ActivityCoordinator) user);
            }
        }
        return null;
    }
}
