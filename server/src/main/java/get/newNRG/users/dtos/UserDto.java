package get.newNRG.users.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private String email;
    private String password;
}
