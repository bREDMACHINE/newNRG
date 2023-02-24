package get.a.big.head.newNRG.users.services;

import get.a.big.head.newNRG.users.models.UserDto;
import get.a.big.head.newNRG.users.models.UserFullDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {

    ResponseEntity<?> addUser(UserDto userDto);
    void deleteUser(String email);
    UserFullDto getUser(String email);
    List<UserFullDto> findAllUsers();
    UserFullDto updateUser(long userId, UserDto userDto);
    ResponseEntity<?> authenticateUser(UserDto userDto);
    void logoutUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
