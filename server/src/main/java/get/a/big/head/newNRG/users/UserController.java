package get.a.big.head.newNRG.users;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public UserController(UserService userService, AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/users/registration")
    public ResponseEntity<Object> registration(@RequestBody UserDto userDto) {
        log.info("Registration request user={}", userDto);
        userService.addUser(userDto);
        return new ResponseEntity<>("Registration complete", HttpStatus.OK);
    }

    @PostMapping("/users/authorization")
    public ResponseEntity<Object> authorization(@RequestBody UserDto userDto) {
        log.info("Authorization request user={}", userDto);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
            User user = userRepository.findByEmail(userDto.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
            String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", user.getEmail());
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/users/logout")
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(httpServletRequest, httpServletResponse, null);
    }

    @PatchMapping("/users/{userId}")
    public UserDto updateUser(@PathVariable long userId, @RequestBody UserDto userDto) {
        log.info("Получен Patch запрос к эндпоинту /users/{}", userId);
        return userService.updateUser(userId, userDto);
    }

    @GetMapping("/users/{userId}")
    public UserDto getUser(@PathVariable long userId) {
        log.info("Получен Get запрос к эндпоинту /users/{}", userId);
        return userService.getUser(userId);
    }

    @GetMapping("/users")
    public List<UserDto> findAllUsers() {
        log.info("Получен Get запрос к эндпоинту /users");
        return userService.findAllUsers();
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable long userId) {
        log.info("Получен Delete запрос к эндпоинту /users");
        userService.deleteUser(userId);
    }
}
