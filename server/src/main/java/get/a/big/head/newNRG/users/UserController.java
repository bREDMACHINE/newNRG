package get.a.big.head.newNRG.users;

import get.a.big.head.newNRG.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid UserDto userDto) {
        userService.addUser(userDto);
        return "/starter";
    }

    @GetMapping("/login")
    public String Login() {
        return "login";
    }

    @GetMapping("/success")
    public String getSuccessPage() {
        return "success";
    }

    @PatchMapping("/{userId}")
    public UserDto updateUser(@PathVariable long userId, @RequestBody UserDto userDto) {
        log.info("Получен Patch запрос к эндпоинту /users/{}", userId);
        return userService.updateUser(userId, userDto);
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable long userId) {
        log.info("Получен Get запрос к эндпоинту /users/{}", userId);
        return userService.getUser(userId);
    }

    @GetMapping
    public List<UserDto> findAllUsers() {
        log.info("Получен Get запрос к эндпоинту /users");
        return userService.findAllUsers();
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        log.info("Получен Delete запрос к эндпоинту /users");
        userService.deleteUser(userId);
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.findAllUsers());
        return "admin";
    }

    @PostMapping("/admin")
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }
}
