package get.a.big.head.newNRG.users;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class UserServiceCreateImpl implements UserServiceCreate {

    private final UserRepository userRepository;

    @Override
    public UserDto addUser(UserDto userDto) {
        return UserMapper.toUserDto(userRepository.save(UserMapper.toUser(userDto)));
    }
}
