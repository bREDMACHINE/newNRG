package get.a.big.head.newNRG.users.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class UserDto {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
