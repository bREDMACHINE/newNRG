package get.a.big.head.newNRG.users;

import get.a.big.head.newNRG.users.models.UserDto;
import get.a.big.head.newNRG.users.models.UserFullDto;
import get.a.big.head.newNRG.users.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class UserController {

    private final UserService userService;


    @PostMapping("/authorization/registration")
    public ResponseEntity<?> registration(@RequestBody UserDto userDto) {
        log.info("Получен Post запрос к эндпоинту /authorization/registration, user={}", userDto);
        return userService.addUser(userDto);
    }

    @PostMapping("/authorization")
    public ResponseEntity<?> authorization(@RequestBody UserDto userDto) {
        log.info("Получен Post запрос к эндпоинту /authorization, user={}", userDto);
        return userService.authenticateUser(userDto);
    }

    @PostMapping("/authorization/logout")
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        log.info("Получен Post запрос к эндпоинту /authorization/logout, HttpServletRequest={}, HttpServletResponse={}",
                httpServletRequest, httpServletResponse);
        userService.logoutUser(httpServletRequest, httpServletResponse);
    }

    @PatchMapping("/authorization/{userId}")
    public UserFullDto requestForUpdateUser(@PathVariable long userId, @RequestBody UserDto userDto) {
        log.info("Получен Patch запрос к эндпоинту /users/{}", userId);
        return userService.updateUser(userId, userDto);
    }

    @GetMapping("/admin/user")
    public UserFullDto getUser(@RequestParam String email) {
        log.info("Получен Get запрос к эндпоинту /admin/user {}", email);
        return userService.getUser(email);
    }

    @GetMapping("/admin/users")
    public List<UserFullDto> findAllUsers(
            @RequestParam(required = false, defaultValue = "USER") String user,
            @RequestParam(required = false, defaultValue = "MODERATOR") String moderator,
            @RequestParam(required = false, defaultValue = "REQUESTED") String requested
            ) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("user", user);
        parameters.put("moderator", moderator);
        parameters.put("requested", requested);
        log.info("Получен Get запрос к эндпоинту /admin/users {}", parameters);
        return userService.findAllUsers(parameters);
    }

    @DeleteMapping("/admin/user")
    public void deleteUser(@RequestParam String email) {
        log.info("Получен Delete запрос к эндпоинту /admin/{}", email);
        userService.deleteUser(email);
    }
}
