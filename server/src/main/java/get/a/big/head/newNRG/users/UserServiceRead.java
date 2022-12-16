package get.a.big.head.newNRG.users;

import java.util.List;

public interface UserServiceRead {

    UserDto getUser(long userId);

    List<UserDto> findAllUsers();
}
