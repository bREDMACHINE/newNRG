package get.a.big.head.newNRG.users;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getEmail());
    }

    public static User toUser(UserDto userForm) {
        return new User(userForm.getId(), userForm.getEmail(), userForm.getEmail());
    }
}
