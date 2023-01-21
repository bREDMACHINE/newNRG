package get.a.big.head.newNRG.users;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@Slf4j
public class UserController {

    private final UserClient userClient;

    public UserController(UserClient userClient) {
        this.userClient = userClient;
    }

    public ResponseEntity<Object> addUser(@Valid @RequestBody UserDto userDto) {
        log.info("Creating user {}",  userDto);
        return userClient.addUser(userDto);
    }

    public ResponseEntity<Object> updateUser(@PathVariable long userId, @RequestBody UserDto userDto) {
        log.info("Patch user {}", userId);
        return userClient.updateUser(userId, userDto);
    }

    @GetMapping
    public ResponseEntity<Object> findAllUsers() {
        log.info("Get all users");
        return userClient.findAllUsers();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable long userId) {
        log.info("Delete user {}", userId);
        return userClient.deleteUser(userId);
    }
}
