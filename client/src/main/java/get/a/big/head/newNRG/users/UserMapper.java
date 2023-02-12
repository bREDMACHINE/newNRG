package get.a.big.head.newNRG.users;

import get.a.big.head.newNRG.users.dtos.User;
import get.a.big.head.newNRG.users.dtos.UserDto;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserMapper {
    public static UserDto toUserDto(String userLogin, String userPassword) {
        return new UserDto(userLogin, userPassword);
    }

    public static User toUser(Object object, String email) {
        Map<String, String> map = (LinkedHashMap<String, String>) object;
        User user = new User();
        user.setEmail(email);
        user.setRole(map.get("role"));
        if (map.get("token") != null) {
            user.setUserId(map.get("token"));
        }
        if (map.get("status") != null) {
            user.setStatus(map.get("status"));
        }
        return user;
    }
}
