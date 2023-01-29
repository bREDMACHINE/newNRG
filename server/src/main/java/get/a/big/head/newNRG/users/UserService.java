package get.a.big.head.newNRG.users;

import java.util.List;

public interface UserService {

    UserDto addUser(UserDto userForm);
    void deleteUser(long userId);
    UserDto getUser(long userId);
    List<UserDto> findAllUsers();
    UserDto updateUser(long userId, UserDto userDto);
}
