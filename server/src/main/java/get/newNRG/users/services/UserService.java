package get.newNRG.users.services;

import get.newNRG.users.dtos.UserDto;
import get.newNRG.users.dtos.UserFullDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {

    ResponseEntity<Object> addUser(UserDto userDto);
    ResponseEntity<Object> deleteUser(String email);
    UserFullDto getUser(String email);
    List<UserFullDto> findAllUsers(String role, String status);
    UserFullDto updateUser(UserFullDto userFullDto);
    ResponseEntity<Object> authenticateUser(UserDto userDto);
    ResponseEntity<Object> logoutUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
    UserFullDto resolutionUser(String resolution, String email);
}
