package get.a.big.head.newNRG.users.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserFullDto {

    private String email;
    private String role;
    private String status;
}
