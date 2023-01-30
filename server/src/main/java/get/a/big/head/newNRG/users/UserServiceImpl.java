package get.a.big.head.newNRG.users;

import get.a.big.head.newNRG.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        return UserMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(long userId, UserDto userDto) {
        User updateUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Указанный userId не существует"));
        if (userDto.getEmail() != null) {
            updateUser.setEmail(userDto.getEmail());
        }
        if (userDto.getEmail() != null) {
            updateUser.setEmail(userDto.getEmail());
        }
        return UserMapper.toUserDto(userRepository.save(updateUser));
    }

    @Override
    public UserDto getUser(long userId) {
        return UserMapper.toUserDto(userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Указанный userId не существует")));
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Указанный userId не существует"));
        userRepository.delete(user);
    }
}
