package get.a.big.head.newNRG.users;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        return new UserDto(user.getEmail(), null);
    }

    public static UserDetails fromUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                new ArrayList<>(user.getRole().getAuthorities())
        );
    }
}
