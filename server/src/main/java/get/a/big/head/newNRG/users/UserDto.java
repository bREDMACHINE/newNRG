package get.a.big.head.newNRG.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private long id;
    private String email;
    @Size(min=5, message = "Не меньше 5 знаков")
    private String password;
    private Role role;

    public UserDto(Long id, String email, String email1) {
    }
}
