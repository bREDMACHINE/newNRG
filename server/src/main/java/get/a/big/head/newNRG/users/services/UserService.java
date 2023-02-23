package get.a.big.head.newNRG.users.services;

import get.a.big.head.newNRG.users.models.UserDto;
import get.a.big.head.newNRG.users.models.UserFullDto;
import get.a.big.head.newNRG.users.models.UserShortDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {

    UserShortDto addUser(UserDto userDto);
    void deleteUser(String userName);
    UserFullDto getUser(String userName);
    List<UserFullDto> findAllUsers();
    UserShortDto updateUser(long userId, UserDto userDto);
    UserShortDto authenticateUser(UserDto userDto);
    void logoutUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
