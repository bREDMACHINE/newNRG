package get.newNRG.users.services;

import get.newNRG.exception.BadRequestException;
import get.newNRG.exception.NotFoundException;
import get.newNRG.users.dtos.UserDto;
import get.newNRG.users.dtos.UserFullDto;
import get.newNRG.users.models.Role;
import get.newNRG.users.models.Status;
import get.newNRG.users.models.User;
import get.newNRG.users.security.JwtTokenProvider;
import get.newNRG.users.UserMapper;
import get.newNRG.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public ResponseEntity<Object> addUser(UserDto userDto) {
        Optional<User> userInDataBase = userRepository.findByEmail(userDto.getEmail());
        if (userInDataBase.isPresent()) {
            throw new BadRequestException("Указанный email уже используется");
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.USER);
        user.setStatus(Status.ACCEPTED);
        userRepository.save(user);
        Map<String, String> response = new HashMap<>();
        response.put("Role", user.getRole().name());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> authenticateUser(UserDto userDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Указан неверный пароль");
        }
        User user = userRepository.findByEmail(userDto.getEmail()).orElseThrow(() -> new NotFoundException("Пользователь не зарегистрирован"));
        Map<String, String> response = new HashMap<>();
        response.put("Token", jwtTokenProvider.createToken(user.getEmail(), user.getRole().name()));
        response.put("Role", user.getRole().name());
        response.put("Status", user.getStatus().name());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> logoutUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(httpServletRequest, httpServletResponse, null);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isAuthenticated", SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public UserFullDto updateUser(UserFullDto userFullDto) {
        User updateUser = userRepository.findByEmail(userFullDto.getEmail()).orElseThrow(() -> new NotFoundException("Указанный email не существует"));
        if (userFullDto.getStatus().equalsIgnoreCase(Status.REQUESTED.name())) {
            updateUser.setStatus(Status.REQUESTED);
        }
        return UserMapper.toUserFullDto(userRepository.save(updateUser));
    }

    @Override
    public UserFullDto resolutionUser(String resolution, String email) {
        User updateUser = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Указанный email не существует"));
        if (resolution.equalsIgnoreCase("ACCEPTED")) {
            if (updateUser.getRole().equals(Role.USER)) {
                updateUser.setRole(Role.MODERATOR);
            } else {
                updateUser.setRole(Role.ADMIN);
            }
        }
        updateUser.setStatus(Status.ACCEPTED);
        return UserMapper.toUserFullDto(userRepository.save(updateUser));
    }

    @Override
    public UserFullDto getUser(String email) {
        return UserMapper.toUserFullDto(userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Указанный email не существует")));
    }

    @Override
    public List<UserFullDto> findAllUsers(String role, String status) {
        if (role.equalsIgnoreCase("Roles") && status.equalsIgnoreCase("Statuses")) {
            return userRepository.findAll().stream()
                    .map(UserMapper::toUserFullDto)
                    .collect(Collectors.toList());
        }
        if (!role.equalsIgnoreCase("Roles") && !status.equalsIgnoreCase("Statuses")) {
            return userRepository.findAll().stream()
                    .filter(user -> user.getRole().name().equalsIgnoreCase(role)
                            && user.getStatus().name().equalsIgnoreCase(status))
                    .map(UserMapper::toUserFullDto)
                    .collect(Collectors.toList());
        }
        return userRepository.findAll().stream()
                .filter(user -> user.getRole().name().equalsIgnoreCase(role)
                        || user.getStatus().name().equalsIgnoreCase(status))
                .map(UserMapper::toUserFullDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Object> deleteUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Указанный email не существует"));
        userRepository.delete(user);
        Map<String, String> response = new HashMap<>();
        response.put("email", email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
