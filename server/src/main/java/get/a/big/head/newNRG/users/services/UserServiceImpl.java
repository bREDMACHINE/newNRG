package get.a.big.head.newNRG.users.services;

import get.a.big.head.newNRG.exception.BadRequestException;
import get.a.big.head.newNRG.exception.NotFoundException;
import get.a.big.head.newNRG.users.security.JwtTokenProvider;
import get.a.big.head.newNRG.users.UserMapper;
import get.a.big.head.newNRG.users.UserRepository;
import get.a.big.head.newNRG.users.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
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
    public UserShortDto addUser(UserDto userDto) {
        Optional<User> userInDataBase = userRepository.findByEmail(userDto.getEmail());
        if (userInDataBase.isPresent()) {
            throw new BadRequestException("Указанный email уже используется");
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        return UserMapper.toUserOutDto(userRepository.save(user));
    }

    @Override
    public UserShortDto authenticateUser(UserDto userDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Указан неверный пароль");
        }
        User user = userRepository.findByEmail(userDto.getEmail()).orElseThrow(() -> new NotFoundException("Пользователь не зарегистрирован"));
        UserShortDto userShortDto = UserMapper.toUserOutDto(user);
        userShortDto.setToken(jwtTokenProvider.createToken(user.getEmail(), user.getRole().name()));
        return userShortDto;
    }

    @Override
    public void logoutUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(httpServletRequest, httpServletResponse, null);
    }

    @Override
    public UserShortDto updateUser(long userId, UserDto userDto) {
        User updateUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Указанный userId не существует"));
        if (userDto.getEmail() != null) {
            updateUser.setEmail(userDto.getEmail());
        }
        if (userDto.getEmail() != null) {
            updateUser.setEmail(userDto.getEmail());
        }
        return UserMapper.toUserOutDto(userRepository.save(updateUser));
    }

    @Override
    public UserFullDto getUser(String userName) {
        return UserMapper.toUserFullDto(userRepository.findByEmail(userName).orElseThrow(() -> new NotFoundException("Указанный email не существует")));
    }

    @Override
    public List<UserFullDto> findAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::toUserFullDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(String userName) {
        User user = userRepository.findByEmail(userName).orElseThrow(() -> new NotFoundException("Указанный email не существует"));
        userRepository.delete(user);
    }
}
