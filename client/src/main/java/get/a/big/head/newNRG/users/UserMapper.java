package get.a.big.head.newNRG.users;

public class UserMapper {
    public static UserDto toUserDto(String userLogin, String userPassword) {
        return new UserDto(userLogin, userPassword);
    }
}
