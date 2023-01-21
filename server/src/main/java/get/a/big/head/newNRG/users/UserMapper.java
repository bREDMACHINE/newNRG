package get.a.big.head.newNRG.users;

import org.springframework.security.core.userdetails.UserDetails;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getEmail());
    }

    public static User toUser(UserDto userForm) {
        return new User(userForm.getId(), userForm.getEmail(), Role.USER);
    }

    public static UserDetails fromUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getRole().getAuthorities()
        );
    }
}
