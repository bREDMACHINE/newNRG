package get.a.big.head.newNRG.users;

import get.a.big.head.newNRG.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class UserServiceUpdateImpl implements UserServiceUpdate {

    private final UserRepository userRepository;

    @Override
    public UserDto updateUser(long userId, UserDto userDto) {
        User updateUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Указанный userId не существует"));
        if (userDto.getName() != null) {
            updateUser.setName(userDto.getName());
        }
        if (userDto.getEmail() != null) {
            updateUser.setEmail(userDto.getEmail());
        }
        return UserMapper.toUserDto(userRepository.save(updateUser));
    }
}
