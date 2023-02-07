package get.a.big.head.newNRG.users.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserShortDto {

    private String email;
    private String token;
    private String role;
}
