package get.newNRG.users;

import get.newNRG.users.dtos.UserDto;
import get.newNRG.users.dtos.UserFullDto;
import get.newNRG.users.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class UserController {

    private final UserService userService;


    @PostMapping("/authorization/registration")
    public ResponseEntity<Object> registration(@RequestBody UserDto userDto) {
        log.info("Получен Post запрос к эндпоинту /authorization/registration, user={}", userDto);
        return userService.addUser(userDto);
    }

    @PostMapping("/authorization")
    public ResponseEntity<Object> authorization(@RequestBody UserDto userDto) {
        log.info("Получен Post запрос к эндпоинту /authorization, user={}", userDto);
        return userService.authenticateUser(userDto);
    }

    @PostMapping("/authorization/logout")
    public ResponseEntity<Object> logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        log.info("Получен Post запрос к эндпоинту /authorization/logout, HttpServletRequest={}, HttpServletResponse={}",
                httpServletRequest, httpServletResponse);
        return userService.logoutUser(httpServletRequest, httpServletResponse);
    }

    @PatchMapping("/user/update")
    public UserFullDto requestForUpdateUser(@RequestBody UserFullDto userFullDto) {
        log.info("Получен Patch запрос к эндпоинту /authorization/update{}", userFullDto);
        return userService.updateUser(userFullDto);
    }

    @PatchMapping("/admin/user")
    public UserFullDto resolutionForUpdateUser(@RequestParam String resolution, @RequestParam String email) {
        log.info("Получен Patch запрос к эндпоинту /admin/user резолюция {}, почта {}", resolution, email);
        return userService.resolutionUser(resolution, email);
    }

    @GetMapping("/admin/user")
    public UserFullDto getUser(@RequestParam String email) {
        log.info("Получен Get запрос к эндпоинту /admin/user {}", email);
        return userService.getUser(email);
    }

    @GetMapping("/admin/users")
    public List<UserFullDto> findAllUsers(@RequestParam String role, @RequestParam String status) {
        log.info("Получен Get запрос к эндпоинту /admin/users role={}, status={}", role, status);
        return userService.findAllUsers(role, status);
    }

    @DeleteMapping("/admin/user")
    public ResponseEntity<Object> deleteUser(@RequestParam String email) {
        log.info("Получен Delete запрос к эндпоинту /admin/{}", email);
        return userService.deleteUser(email);
    }
}
