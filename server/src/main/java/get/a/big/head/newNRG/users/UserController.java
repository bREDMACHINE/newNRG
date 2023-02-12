package get.a.big.head.newNRG.users;

import get.a.big.head.newNRG.users.models.UserDto;
import get.a.big.head.newNRG.users.models.UserFullDto;
import get.a.big.head.newNRG.users.models.UserShortDto;
import get.a.big.head.newNRG.users.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserShortDto registration(@RequestBody UserDto userDto) {
        log.info("Получен Post запрос к эндпоинту /authorization/registration, user={}", userDto);
        return userService.addUser(userDto);
    }

    @PostMapping("/authorization")
    public UserShortDto authorization(@RequestBody UserDto userDto) {
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
    public UserShortDto requestForUpdateUser(@PathVariable long userId, @RequestBody UserDto userDto) {
        log.info("Получен Patch запрос к эндпоинту /users/{}", userId);
        return userService.updateUser(userId, userDto);
    }

    @GetMapping("/admin/{userId}")
    public UserFullDto getUser(@PathVariable long userId) {
        log.info("Получен Get запрос к эндпоинту /admin/{}", userId);
        return userService.getUser(userId);
    }

    @GetMapping("/admin/users")
    public List<UserFullDto> findAllUsers() {
        log.info("Получен Get запрос к эндпоинту /admin/users");
        return userService.findAllUsers();
    }

    @DeleteMapping("/admin/{userId}")
    public void deleteUser(@PathVariable long userId) {
        log.info("Получен Delete запрос к эндпоинту /admin/{}", userId);
        userService.deleteUser(userId);
    }
}
