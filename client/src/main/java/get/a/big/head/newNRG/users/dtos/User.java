package get.a.big.head.newNRG.users.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    private String email;
    private String userId;
    private String role;
    private String status;
}
