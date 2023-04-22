package get.newNRG.users;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import get.newNRG.users.models.User;
import get.newNRG.users.dtos.UserFullDto;
import org.springframework.http.HttpHeaders;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class UserMapper {

    public static User toUser(HttpHeaders headers, String email) {
        return User.builder()
                .email(email)
                .role(headers.getFirst("Role"))
                .userId(headers.getFirst("Token"))
                .status(headers.getFirst("Status"))
                .build();
    }

    public static User toUser(Object object) {
        Gson gson = new Gson();
        return toUser(gson.fromJson(object.toString(), UserFullDto.class));
    }

    public static User toUser(UserFullDto userFullDto) {
        return User.builder()
                .email(userFullDto.getEmail())
                .role(userFullDto.getRole())
                .status(userFullDto.getStatus())
                .build();
    }

    public static List<User> toUsers(Object object) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<UserFullDto>>(){}.getType();
        List<UserFullDto> users = gson.fromJson(object.toString(), type);
        return users.stream().map(UserMapper::toUser).collect(Collectors.toList());
    }

    public static UserFullDto toUserFullDto(User user) {
        return new UserFullDto(user.getEmail(), user.getRole(), user.getStatus());
    }
}
