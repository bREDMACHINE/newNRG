package get.a.big.head.newNRG.users;

import get.a.big.head.newNRG.users.models.Status;
import get.a.big.head.newNRG.users.models.User;
import get.a.big.head.newNRG.users.models.UserFullDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

public class UserMapper {

    public static UserDetails fromUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getStatus().equals(Status.ACCEPTED),
                user.getStatus().equals(Status.ACCEPTED),
                user.getStatus().equals(Status.ACCEPTED),
                user.getStatus().equals(Status.ACCEPTED),
                new ArrayList<>(user.getRole().getAuthorities())
        );
    }

    public static UserFullDto toUserFullDto(User user) {
        UserFullDto userFullDto = new UserFullDto();
        userFullDto.setEmail(user.getEmail());
        userFullDto.setRole(user.getRole().name());
        userFullDto.setStatus(user.getStatus().name());
        return userFullDto;
    }
}
