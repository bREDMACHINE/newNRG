package get.newNRG.users;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import get.newNRG.users.models.User;
import get.newNRG.users.dtos.UserFullDto;

import java.io.StringReader;
import java.util.*;
import java.util.stream.Collectors;

public class UserMapper {

    public static User toUser(Object object, String email) {
        JsonObject jsonObject = JsonParser.parseReader(new StringReader(object.toString()))
                .getAsJsonObject();
        return User.builder()
                .email(email)
                .role(jsonObject.get("Role").getAsString())
                .userId(jsonObject.get("Token").getAsString())
                .status(jsonObject.get("Status").getAsString())
                .build();
    }

    public static User toUser(Object object) {
        return toUser(new Gson().fromJson(object.toString(), UserFullDto.class));
    }

    public static User toUser(UserFullDto userFullDto) {
        return User.builder()
                .email(userFullDto.getEmail())
                .role(userFullDto.getRole())
                .status(userFullDto.getStatus())
                .build();
    }

    public static List<User> toUsers(Object object) {
        List<UserFullDto> users = new Gson().fromJson(object.toString(), new TypeToken<List<UserFullDto>>(){}.getType());
        return users.stream().map(UserMapper::toUser).collect(Collectors.toList());
    }

    public static UserFullDto toUserFullDto(User user) {
        return new UserFullDto(user.getEmail(), user.getRole(), user.getStatus());
    }
}
